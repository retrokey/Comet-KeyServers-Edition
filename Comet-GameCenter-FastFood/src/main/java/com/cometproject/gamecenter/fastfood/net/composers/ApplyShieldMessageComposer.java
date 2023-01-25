package com.cometproject.gamecenter.fastfood.net.composers;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.messages.MessageComposer;

public class ApplyShieldMessageComposer extends MessageComposer {
    private final int playerId;
    private final int objectId;
    private final boolean flush;

    public ApplyShieldMessageComposer(int playerId, int objectId, boolean flush) {
        this.playerId = playerId;
        this.objectId = objectId;
        this.flush = flush;
    }

    @Override
    public short getId() {
        return 12;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(this.objectId);
        msg.writeInt(this.playerId);
        msg.writeBoolean(this.flush);
    }
}
