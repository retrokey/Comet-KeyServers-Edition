package com.cometproject.server.network.messages.incoming.help.guides;

import com.cometproject.server.game.guides.GuideManager;
import com.cometproject.server.game.guides.types.HelpRequest;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.help.guides.GuideSessionDettachedMessageComposer;
import com.cometproject.server.network.messages.outgoing.help.guides.GuideSessionEndedMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;


public class GuideCancelHelpRequestMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        final HelpRequest helpRequest = client.getPlayer().getHelpRequest();

        if(helpRequest != null)
            helpRequest.setIsStop();
    }
}
