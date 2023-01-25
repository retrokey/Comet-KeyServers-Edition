package com.cometproject.server.game.rooms.objects.items.types.floor.wired.actions.custom;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.base.WiredActionItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.events.WiredItemEvent;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.outgoing.room.items.RemoveFloorItemMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.SendShadowMessageComposer;

public class WiredActionGiveShadow extends WiredActionItem {

    public WiredActionGiveShadow(RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    @Override
    public boolean requiresPlayer() {
        return true;
    }

    @Override
    public int getInterface() {
        return 7;
    }

    @Override
    public void onEventComplete(WiredItemEvent event) {
        if (!(event.entity instanceof PlayerEntity)) {
            return;
        }

        PlayerEntity playerEntity = ((PlayerEntity) event.entity);

        if (playerEntity.getPlayer() == null || playerEntity.getPlayer().getSession() == null) {
            return;
        }

        if (this.getWiredData() == null || this.getWiredData().getText() == null) {
            return;
        }

        String status = this.getWiredData().getText();

        switch (status){
            case "1":
                playerEntity.getPlayer().setShadow(2);
                playerEntity.getPlayer().getSession().send(new SendShadowMessageComposer(playerEntity.getPlayerId(), playerEntity.getPosition()));
                break;
            case "0":
                playerEntity.getPlayer().setShadow(0);
                playerEntity.getPlayer().getSession().send(new RemoveFloorItemMessageComposer(playerEntity.getPlayerId() + 2147418112, playerEntity.getPlayerId()));
                break;
        }
    }
}
