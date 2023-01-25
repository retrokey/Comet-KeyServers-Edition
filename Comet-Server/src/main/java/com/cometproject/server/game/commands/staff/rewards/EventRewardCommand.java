package com.cometproject.server.game.commands.staff.rewards;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.achievements.BattlePassGlobals;
import com.cometproject.server.game.achievements.types.BattlePassMission;
import com.cometproject.server.game.achievements.types.BattlePassMissionEnums;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.commands.staff.alerts.NotificationCommand;
import com.cometproject.server.game.players.PlayerManager;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.storage.queries.player.PlayerDao;
import com.cometproject.storage.api.StorageContext;
import com.cometproject.storage.api.data.Data;

public class EventRewardCommand extends ChatCommand {

    private String logDesc = "";

    @Override
    public void execute(Session client, String[] params) {
        if (params.length != 1){
            sendNotif("Uso del comando incorrecto. :eventreward nombre", client);
        }

        Session session = NetworkManager.getInstance().getSessions().getByPlayerUsername(params[0]);
        if(session != null){
            session.getPlayer().getData().increaseActivityPoints(7);
            session.getPlayer().getData().increaseCredits(10);
            session.getPlayer().getData().flush();
            session.getPlayer().sendBalance();

            sendNotif("Comando ejecutado con éxito", client);
            NetworkManager.getInstance().getSessions().broadcast(new NotificationMessageComposer("generic", "¡%username% ha ganado un evento!".replace("%username%", session.getPlayer().getData().getUsername())));

            BattlePassMission ms = BattlePassGlobals.battlePassMissions.stream().filter(x -> x.type == BattlePassMissionEnums.MissionType.EVENTWON).findAny().orElse(null);
            if(ms != null){
                if(session.getPlayer().getData().battlePass != null) session.getPlayer().getData().battlePass.addExperiencePoint(ms.id);
            }
        }
        else sendNotif("Usuario no encontrado", client);

        this.logDesc = "El staff %s ha hecho eventreward al usuario '%b'"
                .replace("%s", client.getPlayer().getData().getUsername())
                .replace("%b", params[0]);
    }

    @Override
    public String getPermission() {
        return "eventreward_command";
    }

    @Override
    public String getParameter() {
        return "%username% %badge%";
    }

    @Override
    public String getDescription() {
        return Locale.getOrDefault("command.eventreward.description", "Gives a player a badge & points");
    }

    @Override
    public String getLoggableDescription(){
        return this.logDesc;
    }

    @Override
    public boolean Loggable(){
        return true;
    }
}
