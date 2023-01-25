package com.cometproject.server.network.messages.incoming.room.action;

import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.notification.MassEventMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.engine.HotelViewMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.RemoveFloorItemMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;


public class ExitRoomMessageEvent implements Event {
    public void handle(Session client, MessageEvent msg) {
        if (client.getPlayer() == null || client.getPlayer().getEntity() == null || client.getPlayer().getEntity().getRoom() == null) {
            client.send(new HotelViewMessageComposer());
            return;
        }

        if(client.getPlayer().getEntity() != null && client.getPlayer().getEntity().isTransformed()) {
            client.getPlayer().getEntity().removeAttribute("item");
            client.getPlayer().getEntity().setTransformed(false);
            client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new RemoveFloorItemMessageComposer(2147418112 - client.getPlayer().getEntity().getId(), 0, 0));
        }

        client.getPlayer().getEntity().leaveRoom(false, false, true);
        client.send(new MassEventMessageComposer("bawtool/hide"));
    }
}
