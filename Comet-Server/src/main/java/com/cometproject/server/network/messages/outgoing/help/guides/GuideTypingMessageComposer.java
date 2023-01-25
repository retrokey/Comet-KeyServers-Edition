package com.cometproject.server.network.messages.outgoing.help.guides;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.guides.GuideManager;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class GuideTypingMessageComposer extends MessageComposer {

    private final boolean typing;

    public GuideTypingMessageComposer(final boolean t) {
        this.typing = t;
    }

    @Override
    public short getId() {
        return Composers.GuideSessionPartnerIsTypingMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeBoolean(this.typing);
    }
}
