package com.cometproject.server.network.messages.outgoing.room.alerts;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class ConfirmableAlertMessageComposer extends MessageComposer {
    private final String username;
    private final int type;
    private final boolean isWiredTrigger;

    public ConfirmableAlertMessageComposer(final String username, final int type, final boolean isWiredTrigger) {
        this.username = username;
        this.type = type;
        this.isWiredTrigger = isWiredTrigger;
    }

    @Override
    public short getId() {
        return Composers.ConfirmableAlertMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeString(this.username);
        msg.writeInt(this.type);
        msg.writeBoolean(this.isWiredTrigger);
    }
}
