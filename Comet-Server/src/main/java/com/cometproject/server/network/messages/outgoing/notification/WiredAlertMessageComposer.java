package com.cometproject.server.network.messages.outgoing.notification;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;


public class WiredAlertMessageComposer extends MessageComposer {
    private final String title;
    private final String alert;

    public WiredAlertMessageComposer(final String title, final String alert) {
        this.title = title;
        this.alert = alert;
    }

    @Override
    public short getId() {
        return Composers.WiredAlertMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeString(this.title);
        msg.writeString(this.alert);
    }
}
