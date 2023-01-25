package com.cometproject.server.network.messages.outgoing.room.engine;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class EnforceRoomZoomMessageComposer extends MessageComposer {
    private final int level;

    public EnforceRoomZoomMessageComposer(int level) {
        this.level = level;
    }

    @Override
    public short getId() {
        return Composers.EnforceRoomZoomMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(level);
    }
}
