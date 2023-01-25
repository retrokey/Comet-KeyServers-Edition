package com.cometproject.server.network.messages.incoming.help.guides;

import com.cometproject.server.game.guides.types.HelpRequest;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.help.guides.GuideSessionCoreMessageComposer;
import com.cometproject.server.network.messages.outgoing.help.guides.GuideTypingMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class GuideUserTypingMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        final HelpRequest helpRequest = client.getPlayer().getHelpRequest();
        boolean typing = msg.readBoolean();

        if(helpRequest != null)
        {
            if(helpRequest.getGuideSession() == null || helpRequest.getPlayerSession() == null)
                return;

            if(helpRequest.getGuideSession() != client)
            {
                helpRequest.getGuideSession().send(new GuideTypingMessageComposer(typing));
            }
            else if(helpRequest.getPlayerSession() != client)
            {
                helpRequest.getPlayerSession().send(new GuideTypingMessageComposer(typing));
            }
        }
    }
}
