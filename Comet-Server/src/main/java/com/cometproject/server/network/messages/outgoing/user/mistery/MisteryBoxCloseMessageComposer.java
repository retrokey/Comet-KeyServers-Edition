package com.cometproject.server.network.messages.outgoing.user.mistery;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class MisteryBoxCloseMessageComposer extends MessageComposer {

    @Override
    public short getId() {
        return Composers.MisteryBoxCloseMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
    }
}
