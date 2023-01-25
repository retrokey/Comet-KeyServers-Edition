package com.cometproject.server.game.rooms.objects.items.types.floor;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.effects.PlayerEffect;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.messages.outgoing.notification.MassEventMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.utilities.RandomUtil;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class RoulletteMachineFloorItem extends RoomItemFloor {
    private PlayerEntity playerEntity = null;
    private boolean isOnBet = false;
    private int betColor = 0;
    public RoulletteMachineFloorItem(RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    @Override
    public boolean onInteract(RoomEntity entity, int requestData, boolean isWiredTrigger) {
        if(entity instanceof PlayerEntity){
            if(Arrays.asList(this.getRoom().getData().getTags()).contains("69")){
                if(((PlayerEntity) entity).getPlayer().getData().itemOnBet != this){
                    ((PlayerEntity)entity).getPlayer().getData().itemOnBet = this;
                    ((PlayerEntity)entity).getPlayer().getData().hasPaidBet = false;
                    ((PlayerEntity) entity).getPlayer().getSession().send(new MassEventMessageComposer("casino/rouletteFloor"));

                    return false;
                }
            }
        }

        return false;
    }

    @Override
    public void onPlaced() {
    }

    @Override
    public void onTickComplete() {
    }
}