package com.cometproject.server.network.messages.incoming.user.citizenship;

import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class OpenTalentTrackMessageEvent implements Event {

    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        if(client == null)
            return;

        //client.send(new TalentTrackMessageComposer(client.getPlayer().getAchievements()));
    }
}
