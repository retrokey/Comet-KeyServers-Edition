package com.cometproject.server.network.messages.outgoing.user.purse;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

import java.util.Map;


public class CurrenciesMessageComposer extends MessageComposer {
    private final Map<Integer, Integer> currencies;

    public CurrenciesMessageComposer(final Map<Integer, Integer> currencies) {
        this.currencies = currencies;
    }

    @Override
    public short getId() {
        return Composers.ActivityPointsMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(currencies.size());

        for (Map.Entry<Integer, Integer> currency : currencies.entrySet()) {
            msg.writeInt(currency.getKey());
            msg.writeInt(currency.getValue());
        }
    }
}
