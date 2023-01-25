package com.cometproject.server.network.messages.outgoing.landing.calendar;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.landing.calendar.CalendarDay;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class CalendarPrizesMessageComposer extends MessageComposer {
    private CalendarDay c;

    public CalendarPrizesMessageComposer(CalendarDay c) {
        this.c = c;
    }

    @Override
    public short getId() {
        return Composers.CalendarPrizesMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeBoolean(true); // enabled
        msg.writeString(c.getProduct()); // productdata
        msg.writeString(c.getImage());
        msg.writeString(c.getItem());
    }
}
