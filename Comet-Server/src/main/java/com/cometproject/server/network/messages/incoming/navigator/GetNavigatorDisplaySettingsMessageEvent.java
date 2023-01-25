package com.cometproject.server.network.messages.incoming.navigator;

import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.navigator.updated.NavigatorPreferencesMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;


public class GetNavigatorDisplaySettingsMessageEvent implements Event {
    public void handle(Session client, MessageEvent msg) {
        //client.send(new NavigatorPreferencesMessageComposer(client.getPlayer().getSettings()));
    }
}
