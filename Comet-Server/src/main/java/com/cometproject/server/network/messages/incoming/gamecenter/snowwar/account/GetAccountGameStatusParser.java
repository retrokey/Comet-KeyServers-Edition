package com.cometproject.server.network.messages.incoming.gamecenter.snowwar.account;

import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.AccountGameStatusComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class GetAccountGameStatusParser implements Event {
    public void handle(Session client, MessageEvent msg) throws Exception {
        client.send(new AccountGameStatusComposer(msg.readInt()));
    }
}