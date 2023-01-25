package com.cometproject.server.network.messages.incoming.catalog;

import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.api.networking.messages.IMessageComposer;
import com.cometproject.server.config.Locale;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.notification.AlertMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.FireworkDataChargesMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.purse.UpdateActivityPointsMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class PurchaseFireworksMessageEvent
implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        int spriteId = msg.readInt();
        int type = msg.readInt();
        int pixelCost = 5;
        int fireworkIncrement = 10;
        if (client.getPlayer().getData().getActivityPoints() >= pixelCost) {
            if (client.getPlayer().getData().getActivityPoints() < pixelCost) {
                client.send(new AlertMessageComposer(Locale.get("catalog.error.notenough")));
                return;
            }
            client.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_75, fireworkIncrement);
            client.getPlayer().getData().decreaseActivityPoints(pixelCost);
            client.send(new UpdateActivityPointsMessageComposer(client.getPlayer().getData().getActivityPoints(), -pixelCost, 0));
            client.getPlayer().composeCurrenciesBalance();
            client.getPlayer().getData().save();
            client.getPlayer().getStats().incrementFireworks(fireworkIncrement);
            client.getPlayer().getStats().saveFireworks();
            client.getPlayer().getSession().send(new FireworkDataChargesMessageComposer(spriteId, client.getPlayer().getStats().getFireworks()));
        }
    }
}

