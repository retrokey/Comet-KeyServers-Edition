package com.cometproject.server.game.commands.staff;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.MassEventMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.sessions.Session;


public class AlertNotiCommand extends ChatCommand {

    @Override
    public void execute(Session client, String[] params) {
        if (params.length < 1) {
            sendNotif("Parámetros inválidos. :alertnoti tipo texto", client);
            return;
        }

        int roomId = client.getPlayer().getEntity().getRoom().getId();

        if(params[0].equals("inter")){
            NetworkManager.getInstance().getSessions().broadcast(new NotificationMessageComposer(Locale.get("command.alertinter.image"), this.merge(params, 1) + "\n\n- " + client.getPlayer().getData().getUsername(), "navigator/goto/" + roomId));
            sendNotif("Enviado", client);
        }

        if(params[0].equals("master")){
            NetworkManager.getInstance().getSessions().broadcast(new NotificationMessageComposer(Locale.get("command.alertmaster.image"), this.merge(params, 1) + "\n\n- " + client.getPlayer().getData().getUsername(), "navigator/goto/" + roomId));
            sendNotif("Enviado", client);
        }

        if(params[0].equals("dj")){
            NetworkManager.getInstance().getSessions().broadcast(new NotificationMessageComposer(Locale.get("command.alertdj.image"), this.merge(params, 1) + "\n\n- " + client.getPlayer().getData().getUsername(), "navigator/goto/" + roomId));
            sendNotif("Enviado", client);
        }
    }

    @Override
    public String getPermission() {
        return "alertnoti_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.alertnoti.description");
    }
}
