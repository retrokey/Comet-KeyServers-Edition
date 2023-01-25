package com.cometproject.server.network.messages.incoming.landing;

import com.cometproject.api.config.CometSettings;
import com.cometproject.server.config.Locale;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class BuyLotteryMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        if(client.getPlayer().antiSpam(getClass().getName(), 0.5))
            return;

        if(client.getPlayer().getData().getCredits() < CometSettings.lotteryPool){
            client.getPlayer().sendBubble("lottery", Locale.getOrDefault("lottery.enough", "No tienes suficientes Koins para participar en la lotería."));
            return;
        }

        client.getPlayer().sendBubble("lottery", Locale.getOrDefault("lottery.bought", "Acabas de comprar un ticket para la lotería, espera a que termine la ronda para conocer el resultado."));


        client.getPlayer().getData().decreaseCredits(CometSettings.lotteryPool);
        client.getPlayer().sendBalance();
        client.getPlayer().getData().save();
        NetworkManager.getInstance().getSessions().addLottery(client.getPlayer().getId());
    }
}
