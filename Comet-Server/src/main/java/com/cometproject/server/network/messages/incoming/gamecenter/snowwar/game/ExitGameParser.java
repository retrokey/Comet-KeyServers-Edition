package com.cometproject.server.network.messages.incoming.gamecenter.snowwar.game;

import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class ExitGameParser implements Event {
    public void handle(Session client, MessageEvent msg) throws Exception {
        msg.readBoolean();
        client.snowWarPlayerData.userLeft();
    }
}