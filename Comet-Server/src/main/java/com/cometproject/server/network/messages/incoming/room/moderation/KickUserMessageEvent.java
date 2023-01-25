package com.cometproject.server.network.messages.incoming.room.moderation;

import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.api.game.quests.QuestType;
import com.cometproject.api.game.rooms.settings.RoomKickState;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.rooms.RoomManager;
import com.cometproject.server.game.rooms.objects.entities.effects.PlayerEffect;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.ActionMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.ApplyEffectMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;


public class KickUserMessageEvent implements Event {
    public void handle(Session client, MessageEvent msg) {
        int playerId = msg.readInt();

        if (client.getPlayer().getEntity() == null || client.getPlayer().getEntity().getRoom() == null) {
            return;
        }

        Room room = client.getPlayer().getEntity().getRoom();
        PlayerEntity playerEntity = room.getEntities().getEntityByPlayerId(playerId);

        if (playerEntity == null) {
            return;
        }

        if(client.getPlayer().getEntity().getStatusType() == 1) {
            final long currentTimeMs = System.currentTimeMillis();
            final long timeSinceLastUpdate = currentTimeMs - client.getPlayer().getLastPhotoTaken();

            if (client.getPlayer().getEntity() == null || client.getPlayer().getEntity().getRoom() == null || timeSinceLastUpdate < (client.getPlayer().getData().getRank() > 6 ? 0 : 5000)) {
                client.send(new NotificationMessageComposer("time_error", Locale.getOrDefault("action.time.error.message", "Debes esperar 5 segundos entre cada acción.")));
                return;
            }

            Position actorPosition = client.getPlayer().getEntity().getPosition();

            if(actorPosition.distanceTo(playerEntity.getPosition()) > 2) {
                client.send(new NotificationMessageComposer("action_distance_error", Locale.getOrDefault("action.time.error.message", "Debes estar delante de la persona para realizar la acción.")));
                return;
            }

            client.getPlayer().getEntity().applyEffect(new PlayerEffect(152, 2));
            playerEntity.applyEffect(new PlayerEffect(133, 5));

            room.getEntities().broadcastMessage(new TalkMessageComposer(-1,
                    Locale.getOrDefault("action.bite.recieved","<b>%user%</b> acaba de morder a  <b>%target%</b>, ¡qué " + (client.getPlayer().getEntity().getGender().equals("M") ? "atrevido " : "atrevida") + "!").replace("%user%", client.getPlayer().getEntity().getUsername()).replace("%target%", playerEntity.getUsername()),
                    ChatEmotion.SHOCKED, 24));

            client.getPlayer().getEntity().setStatusType(0);
            client.getPlayer().setLastPhotoTaken(System.currentTimeMillis());

            // EASTER
            //if(playerEntity.getUsername().equals("Custom") || client.getPlayer().getEntity().getUsername().equals("Custom"))
            client.getPlayer().getQuests().progressQuest(QuestType.WEEN_BITE_PLAYER, 1);

            //client.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_130, 1);
            return;
        }

        if (!client.getPlayer().getEntity().getRoom().getRights().hasRights(client.getPlayer().getId())
                && !client.getPlayer().getPermissions().getRank().roomFullControl() && room.getData().getKickState() != RoomKickState.EVERYONE) {
            return;
        }


        if (room.getData().getOwnerId() == playerEntity.getPlayerId() || playerEntity.getPlayer().getData().getRank() > client.getPlayer().getData().getRank() || playerEntity.getPlayer().getData().getRank() == client.getPlayer().getData().getRank()) {
            return;
        }

        playerEntity.kick();
    }
}
