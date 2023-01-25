package com.cometproject.server.network.messages.incoming.room.bots;

import com.cometproject.server.game.rooms.objects.entities.types.BotEntity;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.user.inventory.BotInventoryMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;
import com.cometproject.server.storage.queries.bots.RoomBotDao;


public class RemoveBotMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        BotEntity entity = client.getPlayer().getEntity().getRoom().getEntities().getEntityByBotId(msg.readInt());

        if (entity == null) {
            return;
        }

        if (client.getPlayer().getId() != entity.getData().getOwnerId() && !client.getPlayer().getPermissions().getRank().roomFullControl()) {
            return;
        }

        if (entity.getBotId() > 0) {
            client.getPlayer().getBots().addBot(entity.getData());

            RoomBotDao.setRoomId(0, entity.getBotId());
            client.send(new BotInventoryMessageComposer(client.getPlayer().getBots().getBots()));
        }

        entity.leaveRoom();
    }
}
