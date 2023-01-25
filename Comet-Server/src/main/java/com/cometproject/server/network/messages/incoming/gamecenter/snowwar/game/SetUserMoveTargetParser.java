package com.cometproject.server.network.messages.incoming.gamecenter.snowwar.game;

import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class SetUserMoveTargetParser implements Event {
    public void handle(Session client, MessageEvent msg) throws Exception {
        if (client.snowWarPlayerData.currentSnowWar == null) {
            return;
        }

        int x = msg.readInt();
        int y = msg.readInt();

        msg.readInt();
        msg.readInt();

        if (client.snowWarPlayerData.humanObject.canWalkTo(x, y))
            client.snowWarPlayerData.playerMove(x, y);
    }
}