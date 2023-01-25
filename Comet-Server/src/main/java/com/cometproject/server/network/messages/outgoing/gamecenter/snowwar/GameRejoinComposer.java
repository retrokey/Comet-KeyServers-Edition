package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.messages.MessageComposer;

public class GameRejoinComposer extends MessageComposer {
    private final int roomId;

    public GameRejoinComposer(int roomId) {
        this.roomId = roomId;
    }
    @Override
    public void compose(IComposer msg) {
        msg.writeInt(this.roomId);
    }
    @Override
    public short getId() {
        return 0;
    }
}