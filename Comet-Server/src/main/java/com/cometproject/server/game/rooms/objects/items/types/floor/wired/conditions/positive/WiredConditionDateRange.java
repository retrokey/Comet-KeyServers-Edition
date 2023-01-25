package com.cometproject.server.game.rooms.objects.items.types.floor.wired.conditions.positive;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.base.WiredConditionItem;
import com.cometproject.server.game.rooms.types.Room;

import java.util.Date;

public class WiredConditionDateRange extends WiredConditionItem {
    public WiredConditionDateRange(RoomItemData itemData, Room room) {
        super(itemData, room);


    }

    @Override
    public int getInterface() {
        return 24;
    }

    @Override
    public boolean evaluate(RoomEntity entity, Object data) {
        int DateStart = this.getWiredData().getParams().get(0);
        int DateEnd = this.getWiredData().getParams().get(1);
        long DateCurrent = Comet.getTime();

        if(DateStart > DateCurrent || DateEnd < DateCurrent) {
            return false;
        } else
        return true;
    }
}
