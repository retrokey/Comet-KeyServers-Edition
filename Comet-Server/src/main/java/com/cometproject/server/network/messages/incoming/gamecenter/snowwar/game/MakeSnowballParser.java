package com.cometproject.server.network.messages.incoming.gamecenter.snowwar.game;

import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class MakeSnowballParser implements Event {
    public void handle(Session client, MessageEvent msg) throws Exception {
        msg.readInt();
        msg.readInt();
        client.snowWarPlayerData.makeSnowBall();
    }
}