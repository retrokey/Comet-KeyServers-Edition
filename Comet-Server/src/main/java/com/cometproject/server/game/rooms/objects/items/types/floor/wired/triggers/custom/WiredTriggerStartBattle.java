package com.cometproject.server.game.rooms.objects.items.types.floor.wired.triggers.custom;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.base.WiredTriggerItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.events.WiredItemEvent;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.components.games.GameType;


public class WiredTriggerStartBattle extends WiredTriggerItem {
    private static final int PARAM_TICK_LENGTH = 0;

    private final WiredItemEvent event;

    public WiredTriggerStartBattle(RoomItemData roomItemData, Room room) {
        super(roomItemData, room);

        this.getWiredData().getParams().putIfAbsent(PARAM_TICK_LENGTH, 5);

        this.event = new WiredItemEvent(null, null);

        event.setTotalTicks(this.getTickCount());
        this.queueEvent(event);
    }

    @Override
    public boolean suppliesPlayer() {
        return false;
    }

    @Override
    public void onEventComplete(WiredItemEvent event) {
        this.evaluate(null, null);

        if (this.getRoom().getGame().getInstance() == null) {
            this.getRoom().getGame().createNew(GameType.SURVIVAL);
            this.getRoom().getGame().getInstance().startTimer(600);
        }
    }

    @Override
    public void onDataChange() {
        this.event.setTotalTicks(this.getTickCount());
    }

    @Override
    public int getInterface() {
        return 6;
    }

    private int getTickCount() {
        int tickLength = this.getWiredData().getParams().get(PARAM_TICK_LENGTH);

        if (tickLength <= 0) {
            tickLength = 2;
        }

        return tickLength;
    }
}
