package com.cometproject.server.network.messages.outgoing.notification;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;


public class MassEventMessageComposer extends MessageComposer {
    private final String link;

    public MassEventMessageComposer(final String link) {
        this.link = link;
    }

    @Override
    public short getId() {
        return Composers.MassEventMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeString(this.link);
    }
}
