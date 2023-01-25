package com.cometproject.server.network.messages.incoming.user.interactions;

import com.cometproject.server.config.Locale;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class AddEntityToGroupWhisperMessageEvent implements Event {
    public void handle(Session client, MessageEvent msg) throws Exception {
        String playerName = msg.readString();

        final long currentTimeMs = System.currentTimeMillis();
        final long timeSinceLastUpdate = currentTimeMs - client.getPlayer().getLastPhotoTaken();

        if (client.getPlayer().getEntity() == null || client.getPlayer().getEntity().getRoom() == null || timeSinceLastUpdate < (client.getPlayer().getData().getRank() > 6 ? 0 : 5000)) {
            client.send(new NotificationMessageComposer("time_error", Locale.getOrDefault("action.time.error.message", "Debes esperar 5 segundos entre cada acción.")));
            return;
        }

        boolean groupWhisperResult = client.getPlayer().handleGroupWhisper(playerName);
        String actionType = groupWhisperResult ? "añadido" : "retirado";
        String pronounType = groupWhisperResult ? "al" : "del";

        client.getPlayer().sendBubble("usr/look/" + playerName, "Has " + actionType + " a " + playerName + " " + pronounType + " chat de grupo.");
        client.getPlayer().setLastPhotoTaken(System.currentTimeMillis());
    }
}

