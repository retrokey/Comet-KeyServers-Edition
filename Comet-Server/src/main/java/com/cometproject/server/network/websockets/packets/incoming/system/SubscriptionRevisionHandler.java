package com.cometproject.server.network.websockets.packets.incoming.system;

import com.cometproject.api.game.furniture.types.FurnitureDefinition;
import com.cometproject.api.game.players.data.components.inventory.PlayerItem;
import com.cometproject.server.composers.catalog.UnseenItemsMessageComposer;
import com.cometproject.server.game.catalog.CatalogManager;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.players.components.types.inventory.InventoryItem;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.user.inventory.UpdateInventoryMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.network.websockets.interfaces.ShopOffer;
import com.cometproject.server.network.websockets.packets.incoming.AbstractWebSocketHandler;
import com.cometproject.server.storage.queries.player.SubscriptionDao;
import com.cometproject.storage.api.StorageContext;
import com.cometproject.storage.api.data.Data;
import com.google.common.collect.Sets;
import io.netty.channel.ChannelHandlerContext;

public class SubscriptionRevisionHandler extends AbstractWebSocketHandler<SubscriptionRevisionHandler.ASMData> {

    public SubscriptionRevisionHandler() {
        super(ASMData.class);
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ASMData authenticationData) {
        if (authenticationData.pid.isEmpty() || !isNumeric(authenticationData.pid)|| !isNumeric(authenticationData.oid))
            return;

        int playerId = Integer.parseInt(authenticationData.pid);
        int offerId = Integer.parseInt(authenticationData.oid);

        Session s = NetworkManager.getInstance().getSessions().getByPlayerId(playerId);

        if (s == null)
            return;

        if (s.getPlayer().antiSpam("subscription_check", 0.5))
            return;

        int claimableOffer = SubscriptionDao.getClaimableOffer(playerId);

        if (claimableOffer == 0 || offerId != claimableOffer)
            return;

        ShopOffer offer = CatalogManager.getInstance().getWebsiteOffer(claimableOffer);

        if (offer != null) {
            if(offer.getDiamonds() > 0){
                s.getPlayer().getData().increaseVipPoints(offer.getDiamonds());
            }

            if(offer.getPixels() > 0){
                s.getPlayer().getData().increaseActivityPoints(offer.getPixels());
            }

            if(offer.getDays() > 0){
                s.getPlayer().getSubscription().add(offer.getDays());
                s.send(s.getPlayer().getSubscription().deliver());
                s.send(s.getPlayer().getSubscription().confirm());
                s.send(s.getPlayer().getSubscription().update());

                s.getPlayer().getInventory().addBadge("VIP_SUB", true);
                s.getPlayer().getData().setTag("VIP");
            }

            if(offer.getItemId() > 0){
                FurnitureDefinition itemDefinition = ItemManager.getInstance().getDefinition(offer.getItemId());

                for(int i = 0; i < offer.getAmount(); i++) {
                    if (itemDefinition != null) {
                        final Data<Long> newItem = Data.createEmpty();
                        StorageContext.getCurrentContext().getRoomItemRepository().createItem(s.getPlayer().getData().getId(), offer.getItemId(), "0", newItem::set);

                        PlayerItem playerItem = new InventoryItem(newItem.get(), offer.getItemId(), "0");

                        s.getPlayer().getInventory().addItem(playerItem);
                        s.send(new UnseenItemsMessageComposer(Sets.newHashSet(playerItem), ItemManager.getInstance()));
                    }
                }

                s.send(new UpdateInventoryMessageComposer());
            }

            s.getPlayer().sendBubble("offer_" + offerId, "Acabas de recibir el paquete: " + offer.getName() + ".");
            s.getPlayer().getData().save();
            s.getPlayer().sendBalance();
            SubscriptionDao.updateClaimableOffers(playerId, offerId);
        }
    }


    class ASMData {
        private String pid;
        private String oid;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
