package com.cometproject.server.network.messages.outgoing.notification;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;


public class GraphicAlertMessageComposer extends MessageComposer {
    private final String link;

    public GraphicAlertMessageComposer(final String link) {
        this.link = link;
    }

    @Override
    public short getId() {
        return Composers.GraphicAlertMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeString("${image.library.url}" + this.link + ".png");
    }
}
