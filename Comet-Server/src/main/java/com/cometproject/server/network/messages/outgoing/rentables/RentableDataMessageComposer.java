package com.cometproject.server.network.messages.outgoing.rentables;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class RentableDataMessageComposer extends MessageComposer {
    private boolean rented;
    private int data;
    private String username;
    private int userId;
    private int price;

    public RentableDataMessageComposer(boolean rented, int data, int userId, String username, int price) {
        this.rented = rented;
        this.data = data;
        this.userId = userId;
        this.username = username;
        this.price = price;
    }

    public RentableDataMessageComposer(boolean rented, int data, int price) {
        this.rented = rented;
        this.data = data;
        this.price = price;
    }

    @Override
    public short getId() {
        return Composers.RentableDataMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeBoolean(this.rented);
        msg.writeInt(this.data);
        msg.writeInt(this.userId);
        msg.writeString(this.username);
        msg.writeInt(9999999); // Time of expire.
        msg.writeInt(this.price);
    }
}
