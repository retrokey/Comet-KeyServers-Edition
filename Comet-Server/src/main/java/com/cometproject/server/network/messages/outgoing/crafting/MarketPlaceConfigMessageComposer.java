package com.cometproject.server.network.messages.outgoing.crafting;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.messages.MessageComposer;
import com.cometproject.server.protocol.headers.Composers;

public class MarketPlaceConfigMessageComposer extends MessageComposer {

    public MarketPlaceConfigMessageComposer(){
    }

    @Override
    public short getId() {
        return Composers.MarketPlaceConfigMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeBoolean(true);
        msg.writeInt(1);
        msg.writeInt(10);
        msg.writeInt(5);
        msg.writeInt(1);
        msg.writeInt(1000000);
        msg.writeInt(48);
        msg.writeInt(7);
    }
}
