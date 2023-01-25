package com.cometproject.server.network.messages.incoming.room.pets;

import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.game.rooms.objects.entities.types.PetEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.mapping.RoomTile;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.room.pets.ScratchPetNotificationMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;


public class ScratchPetMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        int petId = msg.readInt();

        if (client.getPlayer().getEntity() == null || client.getPlayer().getEntity().getRoom() == null) return;

        Room room = client.getPlayer().getEntity().getRoom();
        PetEntity petEntity = room.getEntities().getEntityByPetId(petId);
        PlayerEntity playerEntity = client.getPlayer().getEntity();

        if (petEntity == null) return;

        if (!playerEntity.getPosition().touching(petEntity.getPosition())) {
            Position position = petEntity.getPosition().squareInFront(petEntity.getBodyRotation());

            RoomTile tile = room.getMapping().getTile(position.getX(), position.getY());

            if (tile == null) {
                position = petEntity.getPosition().squareBehind(petEntity.getBodyRotation());
                tile = room.getMapping().getTile(position.getX(), position.getY());
            }

            if (tile != null) {
                petEntity.getPetAI().waitForInteraction();
                petEntity.cancelWalk();

                if (!tile.isReachable(playerEntity)) {
                    scratch(client, petEntity);
                } else {
                    playerEntity.moveTo(position);
                    tile.scheduleEvent(playerEntity.getId(), (e) -> scratch(((PlayerEntity) e).getPlayer().getSession(), petEntity));
                }
            } else {
                return;
            }

            return;
        }

        this.scratch(client, petEntity);
    }

    private void scratch(Session client, PetEntity petEntity) {
        if (client.getPlayer() == null || client.getPlayer().getEntity() == null) {
            return;
        }

        if(client.getPlayer().getStats().getScratches() == 0) {
            return;
        }

        client.getPlayer().getStats().setScratches(client.getPlayer().getStats().getScratches() - 1);

        client.getPlayer().getEntity().lookTo(petEntity.getPosition().getX(), petEntity.getPosition().getY());
        client.getPlayer().getEntity().markNeedsUpdate();

        client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new ScratchPetNotificationMessageComposer(petEntity));

        client.getPlayer().getEntity().carryItem(999999999, 5);
        petEntity.getPetAI().onScratched();

        client.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_37, 1);

        if(NetworkManager.getInstance().getSessions().getByPlayerId(petEntity.getData().getOwnerId()) != null){
            Session owner = NetworkManager.getInstance().getSessions().getByPlayerId(petEntity.getData().getOwnerId());

            if(client.getPlayer().getData().getId() != owner.getPlayer().getData().getId())
            owner.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_38, 1);
        }
    }
}
