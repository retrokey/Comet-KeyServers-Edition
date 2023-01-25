package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class StartCounterComposer extends MessageComposer {
    private final int time;

    public StartCounterComposer(int time) {
        this.time = time;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(this.time);
    }

    @Override
    public short getId() {
        return Composers.SnowStormStartLobbyCounterComposer;
    }
}