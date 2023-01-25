package com.cometproject.server.network.messages.outgoing.room.avatar;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;


public class StopAvatarEffectMessageComposer extends MessageComposer {
    private final int effectId;

    public StopAvatarEffectMessageComposer(final int effectId) {
        this.effectId = effectId;
    }

    @Override
    public short getId() {
        return Composers.StopAvatarEffectMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(effectId);
    }
}
