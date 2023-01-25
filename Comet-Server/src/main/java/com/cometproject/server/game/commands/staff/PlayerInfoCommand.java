package com.cometproject.server.game.commands.staff;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.permissions.PermissionsManager;
import com.cometproject.server.game.permissions.types.Rank;
import com.cometproject.server.game.players.data.PlayerData;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.AdvancedAlertMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.storage.queries.player.PlayerDao;

public class PlayerInfoCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if (params.length != 1) return;

        final String username = params[0];
        Session session = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);

        PlayerData playerData;

        if (session == null || session.getPlayer() == null || session.getPlayer().getData() == null || session.getPlayer().getSettings() == null) {
            playerData = PlayerDao.getDataByUsername(username);
        } else {
            playerData = session.getPlayer().getData();
        }

        if (playerData == null) return;

        final Rank playerRank = PermissionsManager.getInstance().getRank(playerData.getRank());

        if (playerRank.modTool() && !client.getPlayer().getPermissions().getRank().modTool()) {
            // send player info failed alert
            client.send(new AdvancedAlertMessageComposer(Locale.getOrDefault("command.playerinfo.title", "Información de") + ": " + username, Locale.getOrDefault("command.playerinfo.staff", "You cannot view the information of a staff member!")));
            return;
        }

        final StringBuilder userInfo = new StringBuilder();

        if (client.getPlayer().getPermissions().getRank().modTool()) {
            userInfo.append("<b>" + Locale.getOrDefault("command.playerinfo.id", "ID") + "</b>: " + playerData.getId() + "<br>");
        }

        userInfo.append("<b>" + Locale.getOrDefault("command.playerinfo.username", "Username") + "</b>: " + playerData.getUsername() + "<br>");
        userInfo.append("<b>" + Locale.getOrDefault("command.playerinfo.motto", "Misión") + "</b>: " + playerData.getMotto() + "<br>");
        userInfo.append("<b>" + Locale.getOrDefault("command.playerinfo.gender", "Género") + "</b>: " + (playerData.getGender().toLowerCase().equals("m") ? Locale.getOrDefault("command.playerinfo.male", "Male") : Locale.getOrDefault("command.playerinfo.female", "Female")) + "<br>");
        userInfo.append("<b>" + Locale.getOrDefault("command.playerinfo.status", "Conectado") + "</b>: " + (session == null ? Locale.getOrDefault("command.playerinfo.offline", "Offline") : Locale.getOrDefault("command.playerinfo.online", "Online")) + "<br>");
        userInfo.append("<b>" + Locale.getOrDefault("command.playerinfo.achievementPoints", "Puntos de Recompensa") + "</b>: " + playerData.getAchievementPoints() + "<br>");
        //userInfo.append("<b>" + Locale.getOrDefault("command.playerinfo.karma", "Karma") + "</b>: " + playerData.getKarma() + "<br>");
        //userInfo.append("<b>" + Locale.getOrDefault("command.playerinfo.prestige", "Karma Prestige") + "</b>: " + playerData.getPrestige() + "<br>");


        if (client.getPlayer().getPermissions().getRank().modTool()) {
            userInfo.append("<b>" + Locale.getOrDefault("command.playerinfo.rank", "Rank") + "</b>: " + playerData.getRank() + "<br>");
        }

        userInfo.append("<b>" + Locale.getOrDefault("command.playerinfo.currencyBalances", "Monedas del usuario") + "</b><br>");
        userInfo.append("<i>" + playerData.getCredits() + " <b><font color='#E79D1C'>" + Locale.getOrDefault("command.playerinfo.koins", "créditos") + "</font></b></i><br>");

        if (client.getPlayer().getPermissions().getRank().modTool()) {
            userInfo.append("<i>" + playerData.getVipPoints() + " <b><font color='#F71053'>" + Locale.getOrDefault("command.playerinfo.rubys", "asteroides") + "</font></b></i><br>");
        }

        userInfo.append("<i>" + playerData.getActivityPoints() + " <b><font color='#28BCD4'>" + Locale.getOrDefault("command.playerinfo.activityPoints", "cometas") + "</font></b></i><br>");
        userInfo.append("<i>" + playerData.getSeasonalPoints() + " <b><font color='#1C9DE7'>" + Locale.getOrDefault("command.playerinfo.seasonal", "Puntos de Juego") + "</font></b></i><br><br>");


        userInfo.append("<b>" + Locale.getOrDefault("command.playerinfo.roomInfo", "Información de la sala:") + "</b><br>");

        if (session != null && session.getPlayer().getEntity() != null) {
            userInfo.append("<b>" + Locale.getOrDefault("command.playerinfo.roomId", "ID de la sala:") + "</b>: " + session.getPlayer().getEntity().getRoom().getData().getId() + "<br>");
            userInfo.append("<b>" + Locale.getOrDefault("command.playerinfo.roomName", "Nombre de la sala:") + "</b>: " + session.getPlayer().getEntity().getRoom().getData().getName() + "<br>");
            userInfo.append("<b>" + Locale.getOrDefault("command.playerinfo.roomOwner", "Anfitrión:") + "</b>: " + session.getPlayer().getEntity().getRoom().getData().getOwner() + "<br>");
        } else {
            if (session == null)
                userInfo.append("<i>" + Locale.getOrDefault("command.playerinfo.notOnline", "¡No está en línea!") + "</i>");
            else
                userInfo.append("<i>" + Locale.getOrDefault("command.playerinfo.notInRoom", "¡No está en ninguna sala!") + "</i>");
        }

        client.send(new AdvancedAlertMessageComposer(Locale.getOrDefault("command.playerinfo.title", "Información de") + ": " + username, userInfo.toString(), "usr/body/" + playerData.getUsername()));
    }

    @Override
    public boolean isAsync() {
        return true;
    }

    @Override
    public String getPermission() {
        return "playerinfo_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.parameter.username", "%username%");
    }

    @Override
    public String getDescription() {
        return Locale.get("command.playerinfo.description");
    }
}
