package com.cometproject.server.network.messages.incoming.gamecenter.snowwar.game;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class ThrowSnowballAtPositionParser implements Event {
    public void handle(Session client, MessageEvent msg) throws Exception {
        int destinationX = msg.readInt();
        int destinationY = msg.readInt();
        int type = msg.readInt();
        client.snowWarPlayerData.throwSnowballAtPosition(destinationX, destinationY, type);
    }
}