package com.cometproject.server.composers.catalog;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class PurchaseUnavailableErrorMessageComposer extends MessageComposer {

    public final static int ILEGAL = 0;
    public final static int REQUIERE_CLUB = 1;

    private final int code;

    public PurchaseUnavailableErrorMessageComposer(int code){
        this.code = code;
    }

    @Override
    public short getId() {
        return Composers.PurchaseUnavailableErrorMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(code);
    }
}
