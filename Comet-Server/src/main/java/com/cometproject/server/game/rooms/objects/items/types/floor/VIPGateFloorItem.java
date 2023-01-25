package com.cometproject.server.game.rooms.objects.items.types.floor;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.composers.group.GroupEditErrorMessageComposer;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFactory;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.Room;


public class VIPGateFloorItem extends RoomItemFloor {
    public boolean isOpen = false;

    public VIPGateFloorItem(RoomItemData roomItemData, Room room) {
        super(roomItemData, room);

        this.isOpen = false;
    }

    @Override
    public void onEntityStepOn(RoomEntity entity) {
        this.isOpen = true;
        this.getItemData().setData(this.isOpen ? 1 : 0);
        this.sendUpdate();
    }

    @Override
    public void onEntityStepOff(RoomEntity entity) {
        this.setTicks(RoomItemFactory.getProcessTime(1.0));
    }

    @Override
    public void onTickComplete() {
        if (this.getTile().getEntities().size() != 0) {
            return;
        }

        this.isOpen = false;
        this.getItemData().setData(this.isOpen ? 1 : 0);
        this.sendUpdate();
    }

    @Override
    public boolean isMovementCancelled(RoomEntity entity) {
        if (!(entity instanceof PlayerEntity)) {
            return true;
        }

        final PlayerEntity playerEntity = (PlayerEntity) entity;

        boolean canWalk = (!playerEntity.getPlayer().getSubscription().isValid());

        if(canWalk){
            playerEntity.getPlayer().getSession().send(new GroupEditErrorMessageComposer(2));
        }

        return canWalk;
    }
}
