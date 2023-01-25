package com.cometproject.server.network.messages.incoming.help.guides;

import com.cometproject.server.game.guides.GuideManager;
import com.cometproject.server.game.guides.types.HelpRequest;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.help.guides.*;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

import static com.cometproject.server.protocol.headers.Composers.GuideSessionDetachedMessageComposer;

public class GuideCloseHelpRequestMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        final HelpRequest helpRequest = client.getPlayer().getHelpRequest();

        // Final alerts for each user.
        client.send(new GuideSessionEndedMessageComposer(1));
        helpRequest.getOtherElement(client, helpRequest).send(new GuideSessionEndedMessageComposer(1));

        /*

        if(client == helpRequest.getGuideSession())
            client.send(new GuideToolsMessageComposer(true));

        // Recaching all data.
        GuideManager.getInstance().flushGuide(helpRequest.guideId);
        helpRequest.getGuideSession().getPlayer().setHelpRequest(null);
         */
    }
}
