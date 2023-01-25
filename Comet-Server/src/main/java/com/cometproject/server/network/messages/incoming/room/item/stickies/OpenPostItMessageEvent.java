package com.cometproject.server.network.messages.incoming.room.item.stickies;

import com.cometproject.server.game.rooms.objects.items.RoomItemWall;
import com.cometproject.server.game.rooms.objects.items.types.wall.PostItWallItem;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.room.items.postit.PostItMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;


public class OpenPostItMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        int itemId = msg.readInt();

        if (client.getPlayer().getEntity() == null || client.getPlayer().getEntity().getRoom() == null) {
            return;
        }

        Room room = client.getPlayer().getEntity().getRoom();

        RoomItemWall wallItem = room.getItems().getWallItem(itemId);

        if (wallItem == null || !(wallItem instanceof PostItWallItem)) return;

        client.send(new PostItMessageComposer(itemId, ((PostItWallItem) wallItem).getColour() + " " + ((PostItWallItem) wallItem).getMessage()));
    }
}
