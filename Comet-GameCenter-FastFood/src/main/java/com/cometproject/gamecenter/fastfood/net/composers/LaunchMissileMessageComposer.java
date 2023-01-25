package com.cometproject.gamecenter.fastfood.net.composers;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.messages.MessageComposer;

public class LaunchMissileMessageComposer extends MessageComposer {
    private final int playerId;
    private final int targetId;

    public LaunchMissileMessageComposer(int playerId, int targetId) {
        this.playerId = playerId;
        this.targetId = targetId;
    }

    @Override
    public short getId() {
        return 10;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(this.playerId);
        msg.writeInt(this.targetId);
    }
}
