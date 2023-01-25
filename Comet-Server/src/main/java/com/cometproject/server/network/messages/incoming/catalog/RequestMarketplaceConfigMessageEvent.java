package com.cometproject.server.network.messages.incoming.catalog;

import com.cometproject.api.networking.messages.IMessageComposer;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.crafting.MarketPlaceConfigMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class RequestMarketplaceConfigMessageEvent
implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) {
        client.send(new MarketPlaceConfigMessageComposer());
    }
}

