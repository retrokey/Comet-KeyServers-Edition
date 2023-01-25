package com.cometproject.server.game.commands.gimmicks;

import com.cometproject.api.game.quests.QuestType;
import com.cometproject.api.networking.messages.IMessageComposer;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.objects.entities.effects.PlayerEffect;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.storage.api.data.rooms.RoomData;

public class LavaCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        RoomData data = (RoomData)client.getPlayer().getEntity().getRoom().getData();
        if(!data.funCommands){
            LavaCommand.sendNotif("Los FunCommands están desactivados en esta sala.", client);
            return;
        }
        if (params.length != 1) {
            LavaCommand.sendNotif(Locale.getOrDefault("command.lava.none", "\u00bfA qui\u00e9n quieres echarle lava?"), client);
            return;
        }
        if (client.getPlayer().getEntity().isRoomMuted() || client.getPlayer().getEntity().getRoom().getRights().hasMute(client.getPlayer().getId())) {
            LavaCommand.sendNotif(Locale.getOrDefault("command.user.muted", "Est\u00e1s silenciado."), client);
            return;
        }
        String kissedPlayer = params[0];
        Session kissedSession = NetworkManager.getInstance().getSessions().getByPlayerUsername(kissedPlayer);
        if (kissedSession == null) {
            LavaCommand.sendNotif(Locale.getOrDefault("command.user.offline", "\u00a1El usuario no est\u00e1 en l\u00ednea!"), client);
            return;
        }
        if (kissedSession.getPlayer().getEntity() == null) {
            LavaCommand.sendNotif(Locale.getOrDefault("command.user.notinroom", "El usuario no esta en ninguna sala."), client);
            return;
        }
        if (kissedSession.getPlayer().getData().getUsername().equals(client.getPlayer().getData().getUsername())) {
            LavaCommand.sendNotif(Locale.getOrDefault("command.lava.himself", "Respetamos la algolagnia, pero lo de tirarte lava encima como que ya es pasarse."), client);
            return;
        }
        int posX = kissedSession.getPlayer().getEntity().getPosition().getX();
        int posY = kissedSession.getPlayer().getEntity().getPosition().getY();
        int playerX = client.getPlayer().getEntity().getPosition().getX();
        int playerY = client.getPlayer().getEntity().getPosition().getY();
        if (Math.abs(posX - playerX) < 2 && Math.abs(posY - playerY) < 2) {
            client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new WhisperMessageComposer(client.getPlayer().getEntity().getId(), "<b>" + client.getPlayer().getData().getUsername() + Locale.getOrDefault("command.lava.word", "</b> echa lava sobre") + " " + kissedSession.getPlayer().getData().getUsername() + ". <i>*¡AAA me quemo!</i>", 1));
            client.getPlayer().getEntity().applyEffect(new PlayerEffect(990, 2));
            kissedSession.getPlayer().getEntity().applyEffect(new PlayerEffect(4001, 18));

        } else {
            client.getPlayer().getSession().send(new WhisperMessageComposer(client.getPlayer().getEntity().getId(), Locale.getOrDefault("command.notaround", "No est\u00e1s lo suficientemente cerca de %playername%. Ac\u00e9rcate para poder interactuar.").replace("%playername%", kissedSession.getPlayer().getData().getUsername()), 34));
        }
    }

    @Override
    public String getPermission() {
        return "lava_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.parameter.username", "%username%");
    }

    @Override
    public String getDescription() {
        return Locale.get("command.lava.description");
    }
}


