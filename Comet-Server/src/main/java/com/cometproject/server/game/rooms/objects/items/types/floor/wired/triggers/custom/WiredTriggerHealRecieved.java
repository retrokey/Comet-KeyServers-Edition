package com.cometproject.server.game.rooms.objects.items.types.floor.wired.triggers.custom;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.base.WiredTriggerItem;
import com.cometproject.server.game.rooms.types.Room;

public class WiredTriggerHealRecieved extends WiredTriggerItem {

    public WiredTriggerHealRecieved(RoomItemData roomItemData, Room room) {
        super(roomItemData, room);
    }

    public static boolean executeTriggers(PlayerEntity playerEntity) {
        boolean wasExecuted = false;

        for (RoomItemFloor wiredItem : getTriggers(playerEntity.getRoom(), WiredTriggerHealRecieved.class)) {
            WiredTriggerHealRecieved trigger = ((WiredTriggerHealRecieved) wiredItem);

            wasExecuted = trigger.evaluate(playerEntity, trigger);
        }

        return wasExecuted;
    }

    @Override
    public boolean suppliesPlayer() {
        return true;
    }

    @Override
    public int getInterface() {
        return 9;
    }
}
