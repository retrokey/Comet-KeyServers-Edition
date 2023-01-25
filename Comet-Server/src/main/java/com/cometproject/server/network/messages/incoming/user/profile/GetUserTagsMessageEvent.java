package com.cometproject.server.network.messages.incoming.user.profile;

import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.user.profile.UserTagsMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;
import com.cometproject.server.storage.queries.player.PlayerDao;


public class GetUserTagsMessageEvent implements Event {
    public void handle(Session client, MessageEvent msg) {
        int userId = msg.readInt();

        if (NetworkManager.getInstance().getSessions().getByPlayerId(userId) == null)
            return;

        if (client.getPlayer().getEntity() == null)
            return;


        if (!client.getPlayer().getEntity().isVisible()) {
            return;
        }

        PlayerEntity playerEntity = client.getPlayer().getEntity().getRoom().getEntities().getEntityByPlayerId(userId);

        if (playerEntity != null) {
            if (playerEntity.getPlayer() == null || playerEntity.getPlayer().getInventory() == null) return;

            client.send(new UserTagsMessageComposer(userId, PlayerDao.getTagsByPlayerId(userId)));
        }
    }
}