package com.cometproject.server.game.rooms.objects.items.types.floor.survival;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.Room;

public class SurvivalExitFloorItem extends RoomItemFloor {
    public SurvivalExitFloorItem(RoomItemData itemData, Room room) {
        super(itemData, room);

        this.getItemData().setData("0");
    }
}
