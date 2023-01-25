package com.cometproject.server.game.rooms.objects.items.types.floor;

import com.cometproject.api.game.furniture.types.FurnitureDefinition;
import com.cometproject.api.game.players.IPlayer;
import com.cometproject.api.game.players.data.components.inventory.PlayerItem;
import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.composers.catalog.UnseenItemsMessageComposer;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.players.components.types.inventory.InventoryItem;
import com.cometproject.server.game.players.types.Player;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.outgoing.user.inventory.UpdateInventoryMessageComposer;
import com.cometproject.server.utilities.RandomUtil;
import com.cometproject.storage.api.StorageContext;
import com.cometproject.storage.api.data.Data;
import com.google.common.collect.Sets;
import org.apache.commons.lang.NumberUtils;

public class OreFloorItem extends RoomItemFloor {

    public OreFloorItem(RoomItemData itemData, Room room) {
        super(itemData, room);

        if (!NumberUtils.isNumber(this.getItemData().getData()))
            this.getItemData().setData("0");
    }

    @Override
    public boolean onInteract(RoomEntity entity, int state, boolean isWiredTrigger) {
        if (isWiredTrigger || !(entity instanceof PlayerEntity)) {
            return false;
        }

        final Player player = ((PlayerEntity) entity).getPlayer();

        int hits = Integer.parseInt(this.getItemData().getData());
        int maxHits = 3;

        if (hits < maxHits) {
            hits++;
        } else {
            // we're open!
            this.getItemData().setData(hits);
            this.sendUpdate();

            int result = RandomUtil.getRandomInt(1, 100);

            int rewardBig = Integer.parseInt(Locale.getOrDefault("ore_mine_1", "100"));
            int rewardMedium = Integer.parseInt(Locale.getOrDefault("ore_mine_2", "101"));
            int rewardSmall = Integer.parseInt(Locale.getOrDefault("ore_mine_3", "102"));
            int rewardNone = Integer.parseInt(Locale.getOrDefault("ore_mine_4", "103"));
            int rewardTier = 0;

            if (result > 0 && result <= 5) {
                rewardTier = 5;
                giveReward(rewardBig, ((PlayerEntity) entity).getPlayer());
            }

            if (result >= 6 && result <= 35) {
                rewardTier = 30;
                giveReward(rewardMedium, ((PlayerEntity) entity).getPlayer());
            }

            if (result > 35 && result < 85) {
                rewardTier = 50;
                giveReward(rewardSmall, ((PlayerEntity) entity).getPlayer());
            }

            if (result > 85) {
                rewardTier = 15;
                giveReward(rewardNone, ((PlayerEntity) entity).getPlayer());
            }

            ((PlayerEntity) entity).getPlayer().sendBubble("newuser", "Has ganado una " + Locale.getOrDefault("ore_name_" + rewardTier, "mena") + " la cual tiene un " + rewardTier + "% de posibilidad de drop.");
            entity.getRoom().getItems().removeItem(this, player.getSession(), false, true);
        }

        this.getItemData().setData(hits);
        this.sendUpdate();

        return true;
    }

    private void giveReward(int rewardId, IPlayer player) {
        String extraData = "0";

        FurnitureDefinition itemDefinition = ItemManager.getInstance().getDefinition(rewardId);


        if (itemDefinition != null) {
            final Data<Long> newItem = Data.createEmpty();
            StorageContext.getCurrentContext().getRoomItemRepository().createItem(player.getData().getId(), rewardId, extraData, newItem::set);

            PlayerItem playerItem = new InventoryItem(newItem.get(), rewardId, extraData);

            player.getInventory().addItem(playerItem);

            player.getSession().send(new UpdateInventoryMessageComposer());
            player.getSession().send(new UnseenItemsMessageComposer(Sets.newHashSet(playerItem), ItemManager.getInstance()));
        }
    }
}
