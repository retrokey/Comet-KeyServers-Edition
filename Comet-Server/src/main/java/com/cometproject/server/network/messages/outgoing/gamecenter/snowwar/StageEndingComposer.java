package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class StageEndingComposer extends MessageComposer {
    @Override
    public void compose(IComposer msg) {
        msg.writeInt(0);
    }

    @Override
    public short getId() {
        return Composers.SnowStormOnStageEndingComposer;
    }
}