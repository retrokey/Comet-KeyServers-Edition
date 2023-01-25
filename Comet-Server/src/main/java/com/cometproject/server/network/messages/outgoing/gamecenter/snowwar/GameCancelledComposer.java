package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.messages.MessageComposer;

public class GameCancelledComposer extends MessageComposer {
    @Override
    public void compose(IComposer msg) {
    }

    @Override
    public short getId() {
        return 0;
    }
}