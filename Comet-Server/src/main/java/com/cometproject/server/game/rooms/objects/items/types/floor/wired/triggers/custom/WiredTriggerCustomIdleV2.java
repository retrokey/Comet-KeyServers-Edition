package com.cometproject.server.game.rooms.objects.items.types.floor.wired.triggers.custom;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.base.WiredTriggerItem;
import com.cometproject.server.game.rooms.types.Room;


public class WiredTriggerCustomIdleV2 extends WiredTriggerItem {

    private static final int PARAM_TICK_LENGTH = 0;

    public WiredTriggerCustomIdleV2(RoomItemData roomItemData, Room room) {
        super(roomItemData, room);
    }

    public static boolean executeTriggers(PlayerEntity playerEntity, int time) {
        boolean wasExecuted = false;

        for (WiredTriggerCustomIdleV2 wiredItem : getTriggers(playerEntity.getRoom(), WiredTriggerCustomIdleV2.class)) {
            WiredTriggerCustomIdleV2 trigger = wiredItem;

            if (time > trigger.getTime() || playerEntity.isIdle()) {
                if (trigger.evaluate(playerEntity, trigger)) {
                    wasExecuted = true;
                }
            }
        }

        return wasExecuted;
    }
    public int getTime() {
        return (this.getWiredData().getParams().get(PARAM_TICK_LENGTH)) * 10;
    }

    @Override
    public boolean suppliesPlayer() {
        return true;
    }

    @Override
    public int getInterface() {
        return 12;
    }
}
