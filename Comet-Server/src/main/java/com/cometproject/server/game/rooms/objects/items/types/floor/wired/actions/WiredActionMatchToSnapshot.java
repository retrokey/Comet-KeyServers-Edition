package com.cometproject.server.game.rooms.objects.items.types.floor.wired.actions;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.objects.items.types.floor.DiceFloorItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.GateFloorItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.games.AbstractGameTimerFloorItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.WiredItemSnapshot;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.base.WiredActionItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.events.WiredItemEvent;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.highscore.HighscoreClassicFloorItem;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.outgoing.room.items.SlideObjectBundleMessageComposer;


public class WiredActionMatchToSnapshot extends WiredActionItem {
    private static final int PARAM_MATCH_STATE = 0;
    private static final int PARAM_MATCH_ROTATION = 1;
    private static final int PARAM_MATCH_POSITION = 2;

    public WiredActionMatchToSnapshot(RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    @Override
    public boolean requiresPlayer() {
        return false;
    }

    @Override
    public int getInterface() {
        return 3;
    }

    @Override
    public void onEventComplete(WiredItemEvent event) {
        if (this.getWiredData().getSnapshots().size() == 0) {
            return;
        }

        final boolean matchState = this.getWiredData().getParams().get(PARAM_MATCH_STATE) == 1;
        final boolean matchRotation = this.getWiredData().getParams().get(PARAM_MATCH_ROTATION) == 1;
        final boolean matchPosition = this.getWiredData().getParams().get(PARAM_MATCH_POSITION) == 1;

        for (long itemId : this.getWiredData().getSelectedIds()) {
            boolean rotationChanged = false;
            boolean stateChanged = false;

            RoomItemFloor floorItem = this.getRoom().getItems().getFloorItem(itemId);
            if (floorItem == null) continue;

            WiredItemSnapshot itemSnapshot = this.getWiredData().getSnapshots().get(itemId);
            if (itemSnapshot == null) continue;

            if(matchState && floorItem instanceof AbstractGameTimerFloorItem){
                ((AbstractGameTimerFloorItem) floorItem).setLastTime(itemSnapshot.getExtraData());
                floorItem.getItemData().setData(itemSnapshot.getExtraData());
                getRoom().getGame().stop();
            }

            if (matchState && !(floorItem instanceof DiceFloorItem || floorItem instanceof HighscoreClassicFloorItem)) {
                String currentExtradata = floorItem.getItemData().getData();
                String newExtradata = itemSnapshot.getExtraData();

                if(!currentExtradata.equals(newExtradata)) {
                    floorItem.getItemData().setData(newExtradata);
                    stateChanged = true;
                }
            }

            if(matchRotation) {
                int currentRotation = floorItem.getRotation();
                int newRotation = itemSnapshot.getRotation();

                if(currentRotation != newRotation) {
                    floorItem.setRotation(newRotation);
                    rotationChanged = true;
                }
            }

            if(stateChanged || rotationChanged) {
                floorItem.sendUpdate();

                if(floorItem instanceof GateFloorItem){
                    Position currentPosition = floorItem.getPosition();
                    this.getRoom().getMapping().getTile(currentPosition).reload();
                }
            }

            if(matchPosition) {
                Position currentPosition = floorItem.getPosition();
                Position newPosition = new Position(itemSnapshot.getX(), itemSnapshot.getY(), itemSnapshot.getZ());

                if(!currentPosition.equals(newPosition)){
                    this.getRoom().getEntities().broadcastMessage(new SlideObjectBundleMessageComposer(currentPosition.copy(), newPosition.copy(), -1, this.getVirtualId(), floorItem.getVirtualId()));
                    floorItem.setPosition(newPosition);

                    this.getRoom().getMapping().getTile(currentPosition).reload();
                    this.getRoom().getMapping().getTile(newPosition).reload();
                }
            }

            floorItem.save();
        }
    }
}
