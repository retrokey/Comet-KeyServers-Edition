package com.cometproject.server.network.messages.incoming.room.action;

import com.cometproject.api.game.quests.QuestType;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;


public class GiveHandItemMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        int userId = msg.readInt();

        if (client.getPlayer().getEntity() == null || client.getPlayer().getEntity().getRoom() == null) {
            return;
        }

        if (!client.getPlayer().getEntity().isVisible()) {
            return;
        }

        PlayerEntity providerEntity = client.getPlayer().getEntity();
        PlayerEntity receivingEntity = client.getPlayer().getEntity().getRoom().getEntities().getEntityByPlayerId(userId);

        if (receivingEntity == null) {
            return;
        }

        receivingEntity.lookTo(providerEntity.getPosition().getX(), providerEntity.getPosition().getY());
        receivingEntity.carryItem(providerEntity.getHandItem());

        // SAN VALENTÍN
        if(providerEntity.getHandItem() == 1000){
            providerEntity.getPlayer().getQuests().progressQuest(QuestType.VAL20_3, 1);
        }

        providerEntity.carryItem(0);
    }
}
