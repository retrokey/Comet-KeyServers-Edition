package com.cometproject.server.game.rooms.objects.items.types.floor.wired.actions;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.base.WiredActionItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.events.WiredItemEvent;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;


public class WiredActionShowMessage extends WiredActionItem {

    protected boolean isWhisperBubble = false;


    public WiredActionShowMessage(RoomItemData itemData, Room room) {
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

        String finalText = this.getWiredData().getText();
        finalText = finalText.replace("%username%", playerEntity.getPlayer().getData().getUsername());
        finalText = finalText.replace("%roomname%", getRoom().getData().getName());
        finalText = finalText.replace("%usersonline%", Integer.toString(Comet.getStats().getPlayers()));
        finalText = finalText.replace("%roomcount%", Integer.toString(getRoom().getEntities().getPlayerEntities().size()));

        playerEntity.getPlayer().getSession().send(new WhisperMessageComposer(playerEntity.getId(), finalText, isWhisperBubble ? 0 : 34));
    }
}
