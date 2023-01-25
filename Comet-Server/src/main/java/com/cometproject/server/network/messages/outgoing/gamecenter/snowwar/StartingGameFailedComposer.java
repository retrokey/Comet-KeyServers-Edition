package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.messages.MessageComposer;

public class StartingGameFailedComposer extends MessageComposer {
    private final int errorCode;

    public StartingGameFailedComposer(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(this.errorCode);
    }

    @Override
    public short getId() {
        return 0;
    }
}