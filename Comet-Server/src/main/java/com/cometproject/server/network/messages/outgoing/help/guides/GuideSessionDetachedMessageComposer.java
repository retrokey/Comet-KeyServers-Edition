package com.cometproject.server.network.messages.outgoing.help.guides;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.guides.types.HelpRequest;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class GuideSessionDetachedMessageComposer extends MessageComposer {

    public GuideSessionDetachedMessageComposer() {

    }

    @Override
    public short getId() {
        return Composers.GuideSessionDetachedMessageComposer;
    }

    @Override
    public void compose(IComposer msg)
    {

    }
}