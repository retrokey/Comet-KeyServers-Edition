package com.cometproject.server.game.commands.gimmicks;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.objects.entities.effects.PlayerEffect;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.sessions.Session;

public class FrozenCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if (params.length != 1) {
            sendNotif(Locale.getOrDefault("command.frozen.none", "Indica el nombre de la persona que quieres congelar."), client);
            return;
        }

        if (client.getPlayer().getEntity().isRoomMuted() || client.getPlayer().getEntity().getRoom().getRights().hasMute(client.getPlayer().getId())) {
            sendNotif(Locale.getOrDefault("command.user.muted", "Estás silenciado."), client);
            return;
        }

        String kissedPlayer = params[0];
        Session kissedSession = NetworkManager.getInstance().getSessions().getByPlayerUsername(kissedPlayer);

        if (kissedSession == null) {
            sendNotif(Locale.getOrDefault("command.user.offline", "¡El usuario no está en línea!"), client);
            return;
        }

        if (kissedSession.getPlayer().getEntity() == null) {
            sendNotif(Locale.getOrDefault("command.user.notinroom", "El usuario no está en ninguna sala."), client);
            return;
        }

        if (kissedSession.getPlayer().getData().getUsername() == client.getPlayer().getData().getUsername()) {
            sendNotif(Locale.getOrDefault("command.puke.himself", "Respetamos la coprofagia, pero lo de congelarte a tí mismo como que ya es pasarse."), client);
            return;
        }

        int posX = kissedSession.getPlayer().getEntity().getPosition().getX();
        int posY = kissedSession.getPlayer().getEntity().getPosition().getY();
        int playerX = client.getPlayer().getEntity().getPosition().getX();
        int playerY = client.getPlayer().getEntity().getPosition().getY();

        if (!((Math.abs((posX - playerX)) >= 2) || (Math.abs(posY - playerY) >= 2))) {
            client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new WhisperMessageComposer(client.getPlayer().getEntity().getId(), "<b>" + client.getPlayer().getData().getUsername() + Locale.getOrDefault("command.frozen.word", "</b> ha congelado a") + " " + kissedSession.getPlayer().getData().getUsername() + ".", 1));
            client.getPlayer().getEntity().applyEffect(new PlayerEffect(989, 2));
            kissedSession.getPlayer().getEntity().applyEffect(new PlayerEffect(12, 4));
        } else {
            client.getPlayer().getSession().send(new WhisperMessageComposer(client.getPlayer().getEntity().getId(), Locale.getOrDefault("command.notaround", "No estás lo suficientemente cerca de %playername%. Acércate para poder interactuar.").replace("%playername%", kissedSession.getPlayer().getData().getUsername()), 34));
        }
    }

    @Override
    public String getPermission() {
        return "puke_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.parameter.username", "%username%");
    }

    @Override
    public String getDescription() {
        return Locale.getOrDefault("command.frozen.description", "Congela a tu oponente.");
    }
}