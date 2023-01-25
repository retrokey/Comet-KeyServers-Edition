package com.cometproject.server.game.commands.gimmicks;

import com.cometproject.api.game.quests.QuestType;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.achievements.BattlePassGlobals;
import com.cometproject.server.game.achievements.types.BattlePassMission;
import com.cometproject.server.game.achievements.types.BattlePassMissionEnums;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.objects.entities.effects.PlayerEffect;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.room.avatar.ActionMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.storage.api.data.rooms.RoomData;


public class KissCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        RoomData data = (RoomData)client.getPlayer().getEntity().getRoom().getData();
        if(!data.funCommands){
            KissCommand.sendNotif("Los FunCommands están desactivados en esta sala.", client);
            return;
        }
        if (params.length != 1) {
            KissCommand.sendNotif(Locale.getOrDefault("command.puke.none", "\u00bfA qui\u00e9n quieres besar?"), client);
            return;
        }
        if (client.getPlayer().getEntity().isRoomMuted() || client.getPlayer().getEntity().getRoom().getRights().hasMute(client.getPlayer().getId())) {
            KissCommand.sendNotif(Locale.getOrDefault("command.user.muted", "Est\u00e1s silenciado."), client);
            return;
        }

        if(params[0] == "confirmar" || params[0].contains("confirmar")){
            if(client.getPlayer().getData().kiss_player != null){
                String kissedPlayer = client.getPlayer().getData().kiss_player;
                Session kissedSession = NetworkManager.getInstance().getSessions().getByPlayerUsername(kissedPlayer);
                if (kissedSession == null) {
                    KissCommand.sendNotif(Locale.getOrDefault("command.user.offline", "\u00a1El usuario no est\u00e1 en l\u00ednea!"), client);
                    return;
                }
                if (kissedSession.getPlayer().getEntity() == null) {
                    KissCommand.sendNotif(Locale.getOrDefault("command.user.notinroom", "El usuario no est\u00e1 en ninguna sala."), client);
                    return;
                }
                if (kissedSession.getPlayer().getData().getRank() >= 6) {
                    client.getPlayer().getQuests().progressQuest(QuestType.EXPLORE_2, 1);
                }

                int posX = kissedSession.getPlayer().getEntity().getPosition().getX();
                int posY = kissedSession.getPlayer().getEntity().getPosition().getY();
                int playerX = client.getPlayer().getEntity().getPosition().getX();
                int playerY = client.getPlayer().getEntity().getPosition().getY();
                if (Math.abs(posX - playerX) < 2 && Math.abs(posY - playerY) < 2) {
                    String messagePlayer = "* <b>" + kissedSession.getPlayer().getEntity().getUsername() + "</b> ha besado a <b>" + client.getPlayer().getEntity().getUsername() + "</b> *";
                    String messageKissed = "* <b>" + client.getPlayer().getEntity().getUsername() + "</b> se ha sonrojado *";
                    kissedSession.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new WhisperMessageComposer(kissedSession.getPlayer().getEntity().getId(), messagePlayer, 16));
                    client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new WhisperMessageComposer(client.getPlayer().getEntity().getId(), messageKissed, 16));
                    client.getPlayer().getEntity().applyEffect(new PlayerEffect(168, 18));
                    kissedSession.getPlayer().getEntity().applyEffect(new PlayerEffect(168, 18));
                    client.getPlayer().getData().kiss_player = null;

                    BattlePassMission ms = BattlePassGlobals.battlePassMissions.stream().filter(x -> x.type == BattlePassMissionEnums.MissionType.KISSPLAYER).findAny().orElse(null);
                    if(ms != null){
                        if(client.getPlayer().getData().battlePass != null) client.getPlayer().getData().battlePass.addExperiencePoint(ms.id);
                        if(kissedSession.getPlayer().getData().battlePass != null) kissedSession.getPlayer().getData().battlePass.addExperiencePoint(ms.id);
                    }
                } else {
                    client.getPlayer().getSession().send(new WhisperMessageComposer(client.getPlayer().getEntity().getId(), Locale.getOrDefault("command.notaround", "No est\u00e1s lo suficientemente cerca de %playername%. Ac\u00e9rcate para poder interactuar.").replace("%playername%", kissedSession.getPlayer().getData().getUsername()), 34));
                }
            }
            else{
                KissCommand.sendNotif("Nadie te ha pedido un beso.", client);
                return;
            }
        }
        else{
            String kissedPlayer = params[0];
            Session kissedSession = NetworkManager.getInstance().getSessions().getByPlayerUsername(kissedPlayer);
            if (kissedSession == null) {
                KissCommand.sendNotif(Locale.getOrDefault("command.user.offline", "\u00a1El usuario no est\u00e1 en l\u00ednea!"), client);
                return;
            }
            if (kissedSession.getPlayer().getEntity() == null) {
                KissCommand.sendNotif(Locale.getOrDefault("command.user.notinroom", "El usuario no est\u00e1 en ninguna sala."), client);
                return;
            }
            if (kissedSession.getPlayer().getData().getUsername().equals(client.getPlayer().getData().getUsername())) {
                KissCommand.sendNotif(Locale.getOrDefault("command.puke.himself", "No puedes darte amor a ti mismo."), client);
                return;
            }
            if (kissedSession.getPlayer().getData().getRank() >= 6) {
                client.getPlayer().getQuests().progressQuest(QuestType.EXPLORE_2, 1);
            }

            int posX = kissedSession.getPlayer().getEntity().getPosition().getX();
            int posY = kissedSession.getPlayer().getEntity().getPosition().getY();
            int playerX = client.getPlayer().getEntity().getPosition().getX();
            int playerY = client.getPlayer().getEntity().getPosition().getY();
            if (Math.abs(posX - playerX) < 2 && Math.abs(posY - playerY) < 2) {
                kissedSession.getPlayer().getData().kiss_player = client.getPlayer().getEntity().getUsername();
                String messageToClient = "Se ha enviado una petición de beso a " + kissedPlayer;
                String messageToKissed = client.getPlayer().getEntity().getUsername() + " te ha enviado una petición de beso. Usa :besar confirmar";
                client.send(new WhisperMessageComposer(client.getPlayer().getEntity().getId(), messageToClient, 16));
                kissedSession.send(new WhisperMessageComposer(client.getPlayer().getEntity().getId(), messageToKissed, 16));
            } else {
                client.getPlayer().getSession().send(new WhisperMessageComposer(client.getPlayer().getEntity().getId(), Locale.getOrDefault("command.notaround", "No est\u00e1s lo suficientemente cerca de %playername%. Ac\u00e9rcate para poder interactuar.").replace("%playername%", kissedSession.getPlayer().getData().getUsername()), 34));
            }
        }
    }

    @Override
    public String getPermission() {
        return "kiss_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.parameter.username", "%username%");
    }

    @Override
    public String getDescription() {
        return Locale.get("command.kiss.description");
    }
}
