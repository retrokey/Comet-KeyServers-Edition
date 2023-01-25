package com.cometproject.server.game.rooms.objects.items.types.floor;

import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFactory;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.outgoing.room.items.FireworkDataChargesMessageComposer;

public class FireworkFloorItem extends RoomItemFloor {

    public FireworkFloorItem(RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    private boolean inUse = false;

    @Override
    public boolean onInteract(RoomEntity entity, int requestData, boolean isWiredTrigger) {
        if (isWiredTrigger)
            return false;

        double distance = entity.getPosition().distanceTo(this.getPosition());

        if (distance > 2) {
            entity.moveTo(this.getPosition().squareInFront(this.getRotation()));
            return false;
        }

        PlayerEntity pEntity = (PlayerEntity) entity;

        if(requestData == 1 || pEntity.getPlayer().getStats().getFireworks() == 0){
            pEntity.getPlayer().getSession().send(new FireworkDataChargesMessageComposer(this.getVirtualId(), pEntity.getPlayer().getStats().getFireworks()));
            return false;
        }

        if(this.inUse)
            return false;

        this.inUse = true;

        pEntity.getPlayer().getStats().decrementFireworks(1);
        pEntity.getPlayer().getStats().saveFireworks();
        this.getItemData().setData(2);
        this.sendUpdate();

        this.setTicks(RoomItemFactory.getProcessTime(5));
        return true;
    }

    @Override
    public void onTickComplete() {
        this.getItemData().setData(1);
        this.sendUpdate();
        this.inUse = false;
    }
}
