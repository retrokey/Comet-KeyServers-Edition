package com.cometproject.server.network.messages.incoming.gamecenter;

import com.cometproject.server.composers.gamecenter.GameStatusMessageComposer;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class GetGameStatusMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        final int gameId = msg.readInt();
        final int status = msg.readInt();

        client.send(new GameStatusMessageComposer(gameId, status));

        //client.send(new GameCenterAchievementsConfigurationComposer(gameId, client.getPlayer().getAchievements()));
        //client.send(new FriendsLeaderboardComposer(gameId, client.getPlayer().getData().getId()));
    }
}
