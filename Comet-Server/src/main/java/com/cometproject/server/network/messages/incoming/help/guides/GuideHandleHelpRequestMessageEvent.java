package com.cometproject.server.network.messages.incoming.help.guides;

import com.cometproject.server.game.guides.types.HelpRequest;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.help.guides.*;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class GuideHandleHelpRequestMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        boolean valid = msg.readBoolean();
        final HelpRequest helpRequest = client.getPlayer().getHelpRequest();

        if (helpRequest == null) {
            client.send(new GuideSessionErrorMessageComposer(0));
            client.getPlayer().setHelpRequest(null);
            return;
        }

        if (valid) {
            helpRequest.setIsBEGIN();
            helpRequest.setGuide(client.getPlayer().getId());
            helpRequest.getPlayerSession().send(new GuideSessionCoreMessageComposer(helpRequest));
            client.send(new GuideSessionCoreMessageComposer(helpRequest));
        } else {
            helpRequest.decline(client.getPlayer().getId());
            helpRequest.setGuide(0);
            client.send(new GuideSessionDetachedMessageComposer());
        }
    }
}
