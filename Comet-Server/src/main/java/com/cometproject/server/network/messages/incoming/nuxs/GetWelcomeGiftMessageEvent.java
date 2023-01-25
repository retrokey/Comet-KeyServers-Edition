package com.cometproject.server.network.messages.incoming.nuxs;

import com.cometproject.api.config.CometSettings;
import com.cometproject.server.config.Locale;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.nuxs.NuxGiftEmailViewMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class GetWelcomeGiftMessageEvent implements Event {

    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        String email = msg.readString();
        int amount = Integer.parseInt(email);
        String m = CometSettings.bankSystemMinimumRequired + "";

        if(amount < CometSettings.bankSystemMinimumRequired) {
            client.send(new NotificationMessageComposer(Locale.getOrDefault("bank.transfer.image", "bank_transfer"), Locale.getOrDefault("bank.transfer.minimum", "La cantidad mínima para apostar en el casino es de 6 Koins, consíguelos participando en eventos o en el calendario de premios.")));
            client.send(new NuxGiftEmailViewMessageComposer(m, 0, true, false, true));
            return;
        }

        client.send(new NuxGiftEmailViewMessageComposer("RWE_WELCOME_GIFT", amount,true, true, false));

    }
}