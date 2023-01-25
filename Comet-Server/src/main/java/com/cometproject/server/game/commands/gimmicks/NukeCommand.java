package com.cometproject.server.game.commands.gimmicks;

import com.cometproject.api.game.quests.QuestType;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.objects.entities.effects.PlayerEffect;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.storage.api.data.rooms.RoomData;

public class NukeCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        RoomData data = (RoomData)client.getPlayer().getEntity().getRoom().getData();
        if(!data.funCommands){
            NukeCommand.sendNotif("Los FunCommands están desactivados en esta sala.", client);
            return;
        }
        if (params.length != 1) {
            NukeCommand.sendNotif(Locale.getOrDefault("command.puke.none", "\u00bfA qui\u00e9n quieres explotar?"), client);
            return;
        }
        if (client.getPlayer().getEntity().isRoomMuted() || client.getPlayer().getEntity().getRoom().getRights().hasMute(client.getPlayer().getId())) {
            NukeCommand.sendNotif(Locale.getOrDefault("command.user.muted", "Est\u00e1s silenciado."), client);
            return;
        }
        String kissedPlayer = params[0];
        Session kissedSession = NetworkManager.getInstance().getSessions().getByPlayerUsername(kissedPlayer);
        if (kissedSession == null) {
            NukeCommand.sendNotif(Locale.getOrDefault("command.user.offline", "\u00a1El usuario no est\u00e1 en l\u00ednea!"), client);
            return;
        }
        if (kissedSession.getPlayer().getEntity() == null) {
            NukeCommand.sendNotif(Locale.getOrDefault("command.user.notinroom", "El usuario no est\u00e1 en ninguna sala."), client);
            return;
        }
        if (kissedSession.getPlayer().getData().getUsername().equals(client.getPlayer().getData().getUsername())) {
            NukeCommand.sendNotif(Locale.getOrDefault("command.puke.himself", "No puedes explotarte a ti mismo."), client);
            return;
        }
        if (kissedSession.getPlayer().getData().getRank() >= 6) {
            client.getPlayer().getQuests().progressQuest(QuestType.EXPLORE_2, 1);
        }

        String messagePlayer = "* <b>" + client.getPlayer().getEntity().getUsername() + "</b> ha explotado a a <b>" + kissedSession.getPlayer().getEntity().getUsername() + "</b> *";
        String messageKissed = "* <b>" + kissedSession.getPlayer().getEntity().getUsername() + "</b> ha sido explotad@ por <b>" + client.getPlayer().getEntity().getUsername() + "</b> *";
        client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new WhisperMessageComposer(client.getPlayer().getEntity().getId(), messagePlayer, 1));
        kissedSession.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new WhisperMessageComposer(kissedSession.getPlayer().getEntity().getId(), messageKissed, 1));
        client.getPlayer().getEntity().applyEffect(new PlayerEffect(540, 18));
        kissedSession.getPlayer().getEntity().applyEffect(new PlayerEffect(108, 18));
    }

    @Override
    public String getPermission() {
        return "nuke_command";
    }
    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.parameter.username", "%username%");
    }
    @Override
    public String getDescription() {
        return Locale.get("command.nuke.description");
    }
}

