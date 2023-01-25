package com.cometproject.server.network.messages.incoming.room.action;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.navigator.NavigatorManager;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.types.components.games.RoomGame;
import com.cometproject.server.game.rooms.types.components.games.survival.SurvivalGame;
import com.cometproject.server.game.rooms.types.components.games.survival.types.SurvivalPlayer;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.profile.UserBadgesMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;


public class UserBadgesMessageEvent implements Event {
    public void handle(Session client, MessageEvent msg) {
        int userId = msg.readInt();

        if(client.getPlayer().getEntity() == null)
            return;

        if (client.getPlayer() == null || client.getPlayer().getInventory() == null) {
            return;
        }

        if (client.getPlayer().getId() == userId) {
            client.send(new UserBadgesMessageComposer(client.getPlayer().getId(), client.getPlayer().getInventory().equippedBadges()));
            return;
        }

        if (client.getPlayer().getEntity() == null)
            return;


        if (!client.getPlayer().getEntity().isVisible()) {
            return;
        }

        PlayerEntity p = client.getPlayer().getEntity().getRoom().getEntities().getEntityByPlayerId(userId);

        if (p != null) {
            if (p.getPlayer() == null || p.getPlayer().getInventory() == null) return;

            client.send(new UserBadgesMessageComposer(p.getPlayerId(), p.getPlayer().getInventory().equippedBadges()));
        }
    }
}