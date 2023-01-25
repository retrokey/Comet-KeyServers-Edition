package com.cometproject.server.network.messages.incoming.landing;

import com.cometproject.api.config.CometSettings;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.landing.ConcurrentUsersCompetitionMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class ConcurrentUsersCompetitionStatusMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        if(client == null || client.getPlayer() == null)
            return;

        if(client.getPlayer().antiSpam(getClass().getName(), 3.0))
            return;

        int current = NetworkManager.getInstance().getSessions().getUsersOnlineCount();
        int goal = CometSettings.communityGoal;

        if (current < goal) {
            int type = 1;
            client.send(new ConcurrentUsersCompetitionMessageComposer(type, current, goal));
        }
        else if (!client.getPlayer().getSettings().verifyGoal()) {
            int type = 2;
            client.send(new ConcurrentUsersCompetitionMessageComposer(type, current, goal));
        }
        else if (client.getPlayer().getSettings().verifyGoal()) {
            int type = 3;
            client.send(new ConcurrentUsersCompetitionMessageComposer(type, current, goal));
        } else {
            int type = 0;
            client.send(new ConcurrentUsersCompetitionMessageComposer(type, current, goal));
        }

    }
}