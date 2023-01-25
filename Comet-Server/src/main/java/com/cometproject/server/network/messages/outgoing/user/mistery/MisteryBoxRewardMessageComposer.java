package com.cometproject.server.network.messages.outgoing.user.mistery;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class MisteryBoxRewardMessageComposer extends MessageComposer {

    public MisteryBoxRewardMessageComposer() {
    }

    @Override
    public short getId() {
        return Composers.MisteryBoxRewardMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeString("s"); // item type S / I
        msg.writeInt(2323); // item spriteId
    }
}
