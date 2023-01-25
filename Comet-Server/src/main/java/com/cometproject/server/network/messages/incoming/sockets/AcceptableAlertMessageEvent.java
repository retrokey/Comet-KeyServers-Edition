package com.cometproject.server.network.messages.incoming.sockets;

import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class AcceptableAlertMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        if(client.getPlayer().getEntity() == null)
            return;

        final int type = msg.readInt();

        switch (type){
            case 2:
                break;
        }
    }
}
