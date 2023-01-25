package com.cometproject.server.network.messages.incoming.user.wardrobe;

import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.user.wardrobe.WardrobeMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;


public class WardrobeMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        client.send(new WardrobeMessageComposer(client.getPlayer().getSettings().getWardrobe()));
    }
}
