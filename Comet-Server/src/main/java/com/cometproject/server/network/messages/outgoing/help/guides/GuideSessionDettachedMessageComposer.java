package com.cometproject.server.network.messages.outgoing.help.guides;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class GuideSessionDettachedMessageComposer extends MessageComposer {

    @Override
    public short getId() {
        return Composers.GuideSessionDetachedMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
    }
}
