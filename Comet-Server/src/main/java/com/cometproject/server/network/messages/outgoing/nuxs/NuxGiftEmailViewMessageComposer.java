package com.cometproject.server.network.messages.outgoing.nuxs;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.catalog.CatalogManager;
import com.cometproject.server.game.nuxs.NuxGift;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class NuxGiftEmailViewMessageComposer extends MessageComposer {
    private String data;
    private static int amount = 0;
    private boolean status;
    private boolean unkown1;
    private boolean unkown2;

    public NuxGiftEmailViewMessageComposer(String data, int amount, boolean status, boolean unkown1, boolean unkown2) {
        this.data = data;
        this.amount = amount;
        this.status = status;
        this.unkown1 = unkown1;
        this.unkown2 = unkown2;
    }

    @Override
    public short getId() {
        return Composers.NuxGiftEmailViewMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {

        msg.writeString(data); // email
        msg.writeBoolean(unkown1); // unk 1
        msg.writeBoolean(unkown2); // unk 1
        msg.writeInt(amount);
        msg.writeBoolean(status); // Open / Closed
    }
}