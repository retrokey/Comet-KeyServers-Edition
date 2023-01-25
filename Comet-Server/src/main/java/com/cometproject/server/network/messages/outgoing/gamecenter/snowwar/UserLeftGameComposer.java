package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class UserLeftGameComposer extends MessageComposer {
    private final int playerId;

    public UserLeftGameComposer(int playerId) {
        this.playerId = playerId;
    }

    public void compose(IComposer msg) {
        msg.writeInt(this.playerId);
    }

    public short getId() {
        return Composers.SnowStormQueuePlayerRemovedComposer;
    }
}