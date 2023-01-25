package com.cometproject.server.network.messages.incoming.landing;

import com.cometproject.api.game.furniture.types.FurnitureDefinition;
import com.cometproject.api.game.players.IPlayer;
import com.cometproject.api.game.players.data.components.inventory.PlayerItem;
import com.cometproject.api.game.rooms.entities.PlayerRoomEntity;
import com.cometproject.server.composers.catalog.UnseenItemsMessageComposer;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.landing.LandingManager;
import com.cometproject.server.game.players.components.types.inventory.InventoryItem;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.landing.calendar.CalendarPrizesMessageComposer;
import com.cometproject.server.network.messages.outgoing.landing.calendar.CampaignCalendarDataMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.inventory.UpdateInventoryMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.purse.UpdateActivityPointsMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;
import com.cometproject.server.storage.queries.landing.LandingDao;
import com.cometproject.storage.api.StorageContext;
import com.cometproject.storage.api.data.Data;
import com.google.common.collect.Sets;


public class CheckCalendarDayMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        final String campaign = msg.readString();
        final int day = msg.readInt();

        if(client.getPlayer().getStats().getLevel() <= 1){
            client.getPlayer().sendBubble("calendar", "Necesitas ser mínimo nivel 2 para acceder a los regalos del Calendario.\n¡Sube de nivel completando recompensas y misiones! Cada 1500 puntos de experiencia subes de nivel.");
            return;
        }

        if (!campaign.equals("xmas14"))
            return;

        if (day < 0 || day > LandingManager.getInstance().getTotalDays() - 1 || day < LandingManager.getInstance().getUnlockDays() || day > LandingManager.getInstance().getUnlockDays())
            return;

        if (client.getPlayer().getGifts()[day])
            return;

        client.getPlayer().getGifts()[day] = true;

        client.send(new CalendarPrizesMessageComposer(LandingManager.getInstance().getCampaignDay(day + 1)));
        client.send(new CampaignCalendarDataMessageComposer(client.getPlayer().getGifts()));

        String reward[] = LandingManager.getInstance().getGiftByDay(day + 1).split(":");
        String type = reward[0];
        String value = reward[1];
        boolean refreshCreditBalance = false;
        boolean refreshCurrenciesBalance = false;
        int rewardType = 0;

        switch (type.toUpperCase()) {
            case "ACTIVITY_POINTS":
                client.getPlayer().getData().increaseActivityPoints(Integer.parseInt(value));
                refreshCurrenciesBalance = true;
                break;

            case "ACHIEVEMENT_POINTS":
                client.getPlayer().getData().increaseAchievementPoints(Integer.parseInt(value));
                client.getPlayer().getSession().send(new NotificationMessageComposer("voucher", Locale.get("game.received.achievementPoints").replace("%points%", (Integer.parseInt(value)) + "")));
                client.getPlayer().poof();
                break;

            case "VIP_POINTS":
                client.getPlayer().getData().increaseVipPoints(Integer.parseInt(value));
                refreshCurrenciesBalance = true;
                rewardType = 105;
                break;

            case "SEASONAL_POINTS":
                client.getPlayer().getData().increaseSeasonalPoints(Integer.parseInt(value));
                refreshCurrenciesBalance = true;
                rewardType = 103;
                break;

            case "BADGE":
                client.getPlayer().getInventory().addBadge(value, true);
                break;

            case "ITEM":
                String extraData = "0";

                int itemId = (Integer.parseInt(value));

                FurnitureDefinition itemDefinition = ItemManager.getInstance().getDefinition(itemId);

                IPlayer e = client.getPlayer();

                if (itemDefinition != null) {
                    final Data<Long> newItem = Data.createEmpty();
                    StorageContext.getCurrentContext().getRoomItemRepository().createItem(e.getData().getId(), itemId, extraData, newItem::set);

                    PlayerItem playerItem = new InventoryItem(newItem.get(), itemId, extraData);

                    e.getSession().getPlayer().getInventory().addItem(playerItem);

                    e.getSession().send(new UpdateInventoryMessageComposer());
                    e.getSession().send(new UnseenItemsMessageComposer(Sets.newHashSet(playerItem), ItemManager.getInstance()));
                }
                break;

            case "CREDITS":
                client.getPlayer().getData().increaseCredits(Integer.parseInt(value));
                refreshCreditBalance = true;
                break;
        }

        if (refreshCreditBalance) {
            client.getPlayer().getSession().send(client.getPlayer().composeCreditBalance());
        } else if (refreshCurrenciesBalance) {
            client.getPlayer().getSession().send(client.getPlayer().composeCurrenciesBalance());
            client.getPlayer().getSession().send(new UpdateActivityPointsMessageComposer(client.getPlayer().getData().getSeasonalPoints(), Integer.parseInt(value), rewardType));
        }

        client.getPlayer().getData().save();
        client.getPlayer().flush();

        LandingDao.saveCalendarDay(client.getPlayer().getData().getId(), day + 1);
    }
}
