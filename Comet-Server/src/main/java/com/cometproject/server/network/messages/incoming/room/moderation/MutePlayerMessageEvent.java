package com.cometproject.server.network.messages.incoming.room.moderation;

import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.rooms.objects.entities.effects.PlayerEffect;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.game.utilities.ConfirmableAlertType;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.notification.MassEventMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.alerts.ConfirmableAlertMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.network.websockets.WebSocketSessionManager;
import com.cometproject.server.network.websockets.packets.outgoing.alerts.InteractivNotificationWebPacket;
import com.cometproject.server.protocol.messages.MessageEvent;

public class MutePlayerMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        int playerId = msg.readInt();
        int unk = msg.readInt();
        int lengthMinutes = msg.readInt();

        if (client.getPlayer().getEntity() == null || client.getPlayer().getEntity().getRoom() == null) {
            return;
        }

       final Room room = client.getPlayer().getEntity().getRoom();
        PlayerEntity playerEntity = room.getEntities().getEntityByPlayerId(playerId);

        if (playerEntity == null) {
            return;
        }

        if(client.getPlayer().getEntity().getStatusType() == 2) {
            final long currentTimeMs = System.currentTimeMillis();
            final long timeSinceLastUpdate = currentTimeMs - client.getPlayer().getLastDuelSuggestion();

            if (client.getPlayer().getEntity() == null || client.getPlayer().getEntity().getRoom() == null || timeSinceLastUpdate < (client.getPlayer().getData().getRank() > 6 ? 0 : 10000)) {
                client.send(new NotificationMessageComposer("time_error", Locale.getOrDefault("action.time.error.message", "Debes esperar 5 segundos entre cada acción.")));
                return;
            }

            Position actorPosition = client.getPlayer().getEntity().getPosition();

            if (actorPosition.distanceTo(playerEntity.getPosition()) > 2) {
                client.send(new NotificationMessageComposer("action_distance_error", Locale.getOrDefault("action.time.error.message", "Debes estar delante de la persona para realizar la acción.")));
                return;
            }

            String username = client.getPlayer().getEntity().getUsername();
            //WebSocketSessionManager.getInstance().sendMessage(playerEntity.getPlayer().getSession().getWsChannel(), new InteractivNotificationWebPacket("sendNotification", "ask_duel", username, "Duelo Revólver", username + " te ha retado a un duelo a muerte con revólver.", "acceptDuel"));
            playerEntity.getPlayer().getSession().send(new MassEventMessageComposer("habblet/open/varNotif?type=ask_duel&username=" + username + "&category=Duelo Revólver&text=" + username + " te ha retado a un duelo a muerte con revólver.&incoming=acceptDuel"));

            client.getPlayer().getEntity().setStatusType(0);
            client.getPlayer().setLastDuelSuggestion(System.currentTimeMillis());
            return;
        }

        if(client.getPlayer().getEntity().getStatusType() == 3) {
            final long currentTimeMs = System.currentTimeMillis();
            final long timeSinceLastUpdate = currentTimeMs - client.getPlayer().getLastPhotoTaken();

            if (client.getPlayer().getEntity() == null || client.getPlayer().getEntity().getRoom() == null || timeSinceLastUpdate < (client.getPlayer().getData().getRank() > 6 ? 0 : 10000)) {
                client.send(new NotificationMessageComposer("time_error", Locale.getOrDefault("action.time.error.message", "Debes esperar 10 segundos entre cada acción.")));
                return;
            }

            String message;
            String username = client.getPlayer().getEntity().getUsername();
            int bubbleId;

            message = "<b><font color='#C62B2B'>%user%</font></b> ha retado a <b><font color='#1C88E8'>%victim%</font></b> a una partida de Piedra, Papel o Tijera.";
            bubbleId = 1;

            client.getPlayer().getEntity().applyEffect(new PlayerEffect(95, 4));
            playerEntity.applyEffect(new PlayerEffect(96, 4));

            room.getEntities().broadcastMessage(new TalkMessageComposer(-1 ,message.replace("%user%", username).replace("%victim%", playerEntity.getUsername()),
                    ChatEmotion.NONE, bubbleId));

            client.getPlayer().setRPSRival(playerEntity.getUsername());

            //WebSocketSessionManager.getInstance().sendMessage(playerEntity.getPlayer().getSession().getWsChannel(), new InteractivNotificationWebPacket("sendNotification", "ach_notif", username, "Minijuego", username + " te ha invitado a una partida Piedra, Papel o Tijera.", "acceptMinigame"));
            //playerEntity.getPlayer().getSession().send(new MassEventMessageComposer("habblet/open/varNotif?type=ach_notif&username=" + username + "&category=Minijuego&text=" + username + " te ha invitado a una partida Piedra, Papel o Tijera.&incoming=acceptRPS"));
            playerEntity.getPlayer().getSession().send(new ConfirmableAlertMessageComposer(username, ConfirmableAlertType.MINIGAME.getId(), false));

            client.getPlayer().getEntity().setStatusType(0);
            client.getPlayer().setLastPhotoTaken(System.currentTimeMillis());
        }
    }


}
