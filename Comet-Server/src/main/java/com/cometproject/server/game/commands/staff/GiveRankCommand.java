package com.cometproject.server.game.commands.staff;

import com.cometproject.server.api.DiscordClient;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.players.PlayerManager;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.AlertMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.MotdNotificationMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.storage.queries.player.PlayerDao;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.List;

public class GiveRankCommand extends ChatCommand {
    private String logDesc = "";


    @Override
    public void execute(Session client, String[] params) {
        if (params.length < 2) {
            GiveRankCommand.sendNotif("Argumentos inválidos", client);
            return;
        }
        String username = params[0];
        Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
        if(user != null){
            try{
                int rank = Integer.parseInt(params[1]);
                if(!(rank >= 20)){
                    user.getPlayer().getData().setRank(rank);
                    PlayerDao.updateRank(rank, user.getPlayer().getEntity().getUsername());
                    GiveRankCommand.sendNotif("Le has entregado el rango al usuario con éxito", client);
                }
                else GiveRankCommand.sendNotif("Rango inválido", client);
            }
            catch (Exception ex){
                GiveRankCommand.sendNotif("Rango inválido.", client);
            }
        }
        else{
            try{
                int rank = Integer.parseInt(params[1]);
                if(!(rank >= 20)){
                    PlayerDao.updateRank(rank, username);
                    GiveRankCommand.sendNotif("Le has entregado el rango al usuario con éxito", client);
                }
                else GiveRankCommand.sendNotif("Rango inválido", client);
            }
            catch (Exception ex){
                GiveRankCommand.sendNotif("Rango inválido.", client);
            }
        }
    }

    @Override
    public String getPermission() {
        return "giverank_command";
    }

    @Override
    public String getParameter() {
        return "%username% %rank%";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.giverank.description");
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
