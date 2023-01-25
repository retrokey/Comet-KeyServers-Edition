package com.cometproject.server.game.rooms.objects.items.types.floor;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.Room;

public class VoteCounterFloorItem extends RoomItemFloor {
    public VoteCounterFloorItem (RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    @Override
    public boolean onInteract(RoomEntity entity, int requestData, boolean isWiredTrigger) {
        if (isWiredTrigger) {
            return false;
        }

        PlayerEntity p = ((PlayerEntity) entity);

        if(p.hasRights()){
            this.getItemData().setData("0");
            this.sendUpdate();
            this.saveData();
            return true;
        }

        return false;
    }

    @Override
    public void onPlaced() {
        this.getItemData().setData("0");
        this.sendUpdate();
        this.saveData();
    }

    public void handleVotation(int playerId, int score){
        int result = Integer.parseInt(this.getItemData().getData()) + score;

        this.getItemData().setData(result + "");
        this.getItemData().setData(Math.min(result, 999) + "");
        this.saveData();

        super.sendUpdate();
    }


   /*@Override
    public void composeItemData(IComposer msg) {
        msg.writeString(this.getItemData().getData());
        msg.writeInt(Integer.parseInt(this.getItemData().getData()));
    }*/
}
