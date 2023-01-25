package com.cometproject.server.network.messages.incoming.user.club;

import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.user.club.ClubDataMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.club.SubscriptionCenterInfoMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;


public class GetHCCenterInformationEvent implements Event {
    public void handle(Session client, MessageEvent msg) {
        if (client == null) {
            return;
        }

        int requestType = msg.readInt();

        client.send(new ClubDataMessageComposer(client.getPlayer().getSubscription(), requestType));
        client.send(new SubscriptionCenterInfoMessageComposer(client.getPlayer().getSubscription()));

    }
}
