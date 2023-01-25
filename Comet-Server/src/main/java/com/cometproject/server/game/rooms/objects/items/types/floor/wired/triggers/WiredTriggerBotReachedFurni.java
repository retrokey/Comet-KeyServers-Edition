package com.cometproject.server.game.rooms.objects.items.types.floor.wired.triggers;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.base.WiredTriggerItem;
import com.cometproject.server.game.rooms.types.Room;

public class WiredTriggerBotReachedFurni extends WiredTriggerItem {

    public WiredTriggerBotReachedFurni(RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    public static boolean executeTriggers(RoomEntity entity, RoomItemFloor floorItem, String username) {
        boolean wasExecuted = false;

        for (RoomItemFloor floorItemm : getTriggers(entity.getRoom(), WiredTriggerBotReachedFurni.class)) {
            WiredTriggerBotReachedFurni trigger = ((WiredTriggerBotReachedFurni) floorItemm);

            if (trigger.getWiredData().getText().isEmpty() || trigger.getWiredData().getText().equals(username)) {
                if (trigger.getWiredData().getSelectedIds().contains(floorItem.getId())) {
                    trigger.evaluate(entity, null);
                }
            }

        }

        return wasExecuted;
    }

    @Override
    public int getInterface() {
        return 13;
    }

    @Override
    public boolean suppliesPlayer() {
        return true;
    }
}
