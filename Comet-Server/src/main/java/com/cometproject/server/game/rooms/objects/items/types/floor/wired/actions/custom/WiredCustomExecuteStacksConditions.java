package com.cometproject.server.game.rooms.objects.items.types.floor.wired.actions.custom;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.actions.WiredActionRandomEffect;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.actions.WiredActionShowMessage;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.addons.WiredAddonUnseenEffect;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.base.WiredActionItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.base.WiredConditionItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.base.WiredTriggerItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.events.WiredItemEvent;
import com.cometproject.server.game.rooms.types.Room;
import com.google.common.collect.Lists;

import java.util.List;

public class WiredCustomExecuteStacksConditions extends WiredActionItem {

    public WiredCustomExecuteStacksConditions(RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    @Override
    public void onEventComplete(WiredItemEvent event) {
        List<Position> tilesToExecute = Lists.newArrayList();
        List<RoomItemFloor> itemFloors = Lists.newArrayList();

        int nbEffect = 0;
        int nbEffectMsg = 0;

        for (long itemId : this.getWiredData().getSelectedIds()) {
            RoomItemFloor floorItem = this.getRoom().getItems().getFloorItem(itemId);
            if (floorItem == null) continue;
            for (Position positions : floorItem.getPositions()) {
                if (tilesToExecute.contains(positions)) continue;
                tilesToExecute.add(positions);
            }
        }

        for (Position tileToUpdate : tilesToExecute) {
            for (RoomItemFloor roomItemFloor : this.getRoom().getMapping().getTile(tileToUpdate).getItems()) {
                if (nbEffect > 1000) break;

                if (!(roomItemFloor instanceof WiredActionItem) && !(roomItemFloor instanceof WiredConditionItem) && !(roomItemFloor instanceof WiredAddonUnseenEffect) && !(roomItemFloor instanceof WiredActionRandomEffect)) continue;

                if (roomItemFloor instanceof WiredActionShowMessage || roomItemFloor instanceof WiredCustomShowMessageRoom) {
                    if (nbEffectMsg >= 10) continue;
                    ++nbEffectMsg;
                }

                if (!(roomItemFloor instanceof WiredCustomForwardRoom)) {
                    itemFloors.add(roomItemFloor);
                }

                ++nbEffect;
            }

            if (itemFloors.size() <= 0) continue;

            WiredTriggerItem.startExecute(event.entity, event.data, itemFloors, true);
            itemFloors.clear();
        }

        tilesToExecute.clear();
    }


    @Override
    public boolean requiresPlayer() {
        return false;
    }

    @Override
    public int getInterface() {
        return 18;
    }
}