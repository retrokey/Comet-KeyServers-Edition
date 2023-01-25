package com.cometproject.server.game.rooms.objects.items.types.floor.wired.conditions.positive.custom;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.base.WiredConditionItem;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.components.games.GameTeam;

public class WiredConditionTriggererOnFurniGreen extends WiredConditionItem {
    public WiredConditionTriggererOnFurniGreen(RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    @Override
    public boolean evaluate(RoomEntity entity, Object data) {
        PlayerEntity playerEntity = (PlayerEntity)entity;

        if (entity == null) {
            return false;
        }

        boolean isOnFurni = false;

        block0: for (long itemId : this.getWiredData().getSelectedIds()) {
            for (RoomItemFloor itemOnEntity : entity.getRoom().getItems().getItemsOnSquare(entity.getPosition().getX(), entity.getPosition().getY())) {
                if (itemOnEntity.getId() != itemId || !playerEntity.getGameTeam().equals(GameTeam.GREEN)) continue;
                isOnFurni = true;
                continue block0;
            }
        }

        return this.isNegative != isOnFurni;
    }

    @Override
    public int getInterface() {
        return 8;
    }
}

