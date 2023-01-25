package com.cometproject.server.network.messages.outgoing.help.guides;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.guides.GuideManager;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class GuideSessionEndedMessageComposer extends MessageComposer {

    private final int code;

    public GuideSessionEndedMessageComposer(final int c) {
        this.code = c;
    }

    @Override
    public short getId() {
        return Composers.GuideSessionEndedMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(this.code);
    }
}
