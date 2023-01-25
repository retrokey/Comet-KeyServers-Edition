package com.cometproject.server.network.messages.outgoing.room.items;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class FireworkDataChargesMessageComposer extends MessageComposer {
    private final int virtualId;
    private final int fireworks;

    public FireworkDataChargesMessageComposer(int virtualId, int fireworks) {
        this.virtualId = virtualId;
        this.fireworks = fireworks;
    }

    @Override
    public short getId() {
        return Composers.FireworkDataChargesMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(this.virtualId);
        msg.writeInt(this.fireworks);
        msg.writeInt(0);// amount
        msg.writeInt(5); // multiple currencies
        msg.writeInt(0); // activity points
        msg.writeInt(10); // charge recieved
    }
}