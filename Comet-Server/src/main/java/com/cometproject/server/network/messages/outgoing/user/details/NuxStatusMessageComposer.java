package com.cometproject.server.network.messages.outgoing.user.details;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;


public class NuxStatusMessageComposer extends MessageComposer {
    private int status;

    public NuxStatusMessageComposer(int status) {
        this.status = status;
    }

    @Override
    public short getId() {
        return Composers.MassEventMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(status);
    }
}
