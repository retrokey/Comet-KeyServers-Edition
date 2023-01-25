package com.cometproject.server.composers.catalog.pets;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class CatalogModeComposer extends MessageComposer {
    private final int mode;

    public CatalogModeComposer(int mode) {
        this.mode = mode;
    }

    @Override
    public short getId() {
        return Composers.CatalogModeMessageComposer;
    }

    @Override
    public void compose(final IComposer msg) {
        msg.writeInt(mode);
    }
}
