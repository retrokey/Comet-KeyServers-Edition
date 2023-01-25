package com.cometproject.server.network.messages.outgoing.rentables;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class RentOfferMessageComposer extends MessageComposer {
    private boolean rented;
    private String item;
    private boolean buyout;
    private int priceCredits;
    private int priceExtra;
    private int extraType;

    public RentOfferMessageComposer(boolean rented, String item, boolean buyout, int priceCredits, int pricExtra, int extraType) {
        this.rented = rented;
        this.item = item;
        this.buyout = buyout;
        this.priceCredits = priceCredits;
        this.priceExtra = pricExtra;
        this.extraType = extraType;
    }

    @Override
    public short getId() {
        return Composers.RentOfferMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeBoolean(this.rented);
        msg.writeString(this.item);
        msg.writeBoolean(this.buyout);
        msg.writeInt(this.priceCredits);
        msg.writeInt(this.priceExtra);
        msg.writeInt(this.extraType);
    }
}
