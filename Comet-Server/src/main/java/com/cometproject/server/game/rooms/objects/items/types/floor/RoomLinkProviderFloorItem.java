package com.cometproject.server.game.rooms.objects.items.types.floor;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.outgoing.room.items.GetGuestRoomResultMessageComposer;

import java.util.ArrayList;
import java.util.List;

public class RoomLinkProviderFloorItem extends RoomItemFloor {
    public RoomLinkProviderFloorItem(RoomItemData itemData, Room room) {
        super(itemData, room);
    }
    @Override
    public void onPlaced(){
        this.getItemData().setData("state0internalLink0");
        this.saveData();
    }

    @Override
    public void onEntityStepOn(RoomEntity entity) {
        if(!(entity instanceof PlayerEntity)) return;

        PlayerEntity playerEntity = ((PlayerEntity) entity);
        playerEntity.getPlayer().getSession().send(new GetGuestRoomResultMessageComposer(new Integer(this.getExtradataInfo().get(1))));

    }

    @Override
    public void composeItemData(IComposer msg) {
        msg.writeInt(0);
        msg.writeInt(1);
        msg.writeInt(1);
        msg.writeString("internalLink");
        msg.writeString(this.getItemData().getData().split("internalLink")[1].replace("\t", ""));
    }

    @Override
    public boolean onInteract(RoomEntity entity, int requestData, boolean isWiredTrigger) {
        if(!(entity instanceof PlayerEntity))
            return false;

        PlayerEntity playerEntity = ((PlayerEntity) entity);
        playerEntity.getPlayer().getSession().send(new GetGuestRoomResultMessageComposer(new Integer(this.getExtradataInfo().get(1))));

        return false;
    }

    private List<String> getExtradataInfo() {
        List<String> values = new ArrayList<>();

        for(String value : this.getItemData().getData().replace("state", "").split("internalLink")) {
            values.add(value.trim());
        }

        return values;
    }
}