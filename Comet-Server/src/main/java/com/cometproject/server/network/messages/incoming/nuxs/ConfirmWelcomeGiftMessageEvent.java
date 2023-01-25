package com.cometproject.server.network.messages.incoming.nuxs;

import com.cometproject.server.config.Locale;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class ConfirmWelcomeGiftMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        int amount = msg.readInt();
        try {
            if (amount < 6 || client.getPlayer().getData().getBlackMoney() < amount) {
                client.getPlayer().sendBubble("inters", Locale.getOrDefault("command.setbet.minimum", "El mínimo de apuesta son 6 de Fichas, si dispones de ellos vuelve a hacer click, para conseguir Koins debes ganar algun evento o conseguirlos en el calendario."));
                return;
            } else if (amount > 100) {
                client.getPlayer().sendBubble("inters", Locale.getOrDefault("command.setbet.limit", "No puedes apostar más de 50 de Fichas, se te ha asignado una apuesta de 50."));
                amount = 100;
            }

            client.getPlayer().getEntity().setBetAmount(amount);
            client.getPlayer().sendBubble("inters", Locale.getOrDefault("command.setbet.set", "Has colocado tus apuestas en %s Fichas.").replace("%s", amount + ""));
        } catch (Exception e) {
            client.getPlayer().sendBubble("inters", Locale.getOrDefault("command.setbet.invalid", "Por favor, introduce únicamente valores numéricos"));
        }
    }
}