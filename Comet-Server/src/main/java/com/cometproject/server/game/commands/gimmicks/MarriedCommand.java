package com.cometproject.server.game.commands.gimmicks;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.AlertMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.UpdateInfoMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.storage.queries.player.relationships.RelationshipDao;


public class MarriedCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if (params.length < 1) {
            sendNotif(Locale.getOrDefault("command.mrrd.none", "¿Con quien te quieres casar?"), client);
            return;
        }

        if (client.getPlayer().getEntity().isRoomMuted() || client.getPlayer().getEntity().getRoom().getRights().hasMute(client.getPlayer().getId())) {
            sendNotif(Locale.getOrDefault("command.user.muted", "Estás muteado!"), client);
            return;
        }

        String username = client.getPlayer().getData().getUsername();
        Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(params[0]);
        String targetUsername = user.getPlayer().getData().getUsername();
        boolean ask = client.getPlayer().getData().getAsk();

        if (targetUsername == client.getPlayer().getData().getUsername()) {
            sendNotif(Locale.getOrDefault("command.mrrd.himself", "¡No puedes casarte contigo mismo!"), client);
            return;
        }

        if(user.getPlayer().getEntity() != null && params.length == 1) {
            client.send(new WhisperMessageComposer(client.getPlayer().getId(), "Le haz preguntado a " + targetUsername + " si se quiere casar contigo, ahora espera la respuesta.", 21));
            user.send(new AlertMessageComposer("" + username + " quiere casarse contigo.\r\rEscribe:\r<b>:married " + username + " si. \r</b>Si quieres aceptar la propuesta de matrimonio " + username + "\r<b>:married " + username + " no</b>\rSi no quieres estar con " + username + ""));

            client.getPlayer().getData().setAsk(true);
            user.getPlayer().getData().setAsk(true);

        } else if(user.getPlayer().getEntity() == null) {
            sendNotif(Locale.getOrDefault("command.tsm.none", "El jugador no está en tu habitación"), client);
        }

        if(ask && user.getPlayer().getData().getAsk() && params[1].equals("si")) {

            client.getPlayer().getData().setMotto("Casad@ con: " + targetUsername + "");
            user.getPlayer().getData().setMotto("Junto con: " + username + "");

            client.send(new WhisperMessageComposer(client.getPlayer().getId(), "Ahora estás casad@ con " + targetUsername + "", 34));
            user.send(new WhisperMessageComposer(client.getPlayer().getId(), "Ahora estás casad@ con " + username + "", 34));

            client.getPlayer().getData().save();
            user.getPlayer().getData().save();

            client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new UpdateInfoMessageComposer(client.getPlayer().getEntity()));
            client.send(new UpdateInfoMessageComposer(-1, client.getPlayer().getEntity()));

            user.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new UpdateInfoMessageComposer(user.getPlayer().getEntity()));
            user.send(new UpdateInfoMessageComposer(-1, user.getPlayer().getEntity()));
            user.send(new UpdateInfoMessageComposer(-1, user.getPlayer().getEntity()));

            client.getPlayer().getData().setAsk(false);
            user.getPlayer().getData().setAsk(true);
        } else if(ask && params[1].equals("no")) {

            user.send(new WhisperMessageComposer(client.getPlayer().getId(), "¡Oh no! " +username+" lamentablemente respondió que no.", 21));
            client.send(new WhisperMessageComposer(client.getPlayer().getId(), "Haz respondido que no.", 21));

            client.getPlayer().getData().setAsk(false);
            user.getPlayer().getData().setAsk(false);
        }
    }

    @Override
    public String getPermission() {
        return "married_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.parameter.username", "%username%");
    }

    @Override
    public String getDescription() {
        return Locale.get("command.married.description");
    }
}
