package com.cometproject.server.network.messages.incoming.gamecenter.snowwar.game;

import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.GameDirectoryStatusComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class CheckGameDirectoryStatusParser implements Event {
    public void handle(Session client, MessageEvent msg) throws Exception {
        client.send(new GameDirectoryStatusComposer(0, 0, client.getPlayer().getId()));
    }
}