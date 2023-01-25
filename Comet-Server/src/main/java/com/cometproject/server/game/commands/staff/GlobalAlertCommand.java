package com.cometproject.server.game.commands.staff;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.MassEventMessageComposer;
import com.cometproject.server.network.sessions.Session;


public class GlobalAlertCommand extends ChatCommand {

    @Override
    public void execute(Session client, String[] params) {
        if (params.length < 1) {
            sendNotif("Parámetros inválidos. :globalalert tipo texto", client);
            return;
        }

        if(params[0].equals("game")){
            NetworkManager.getInstance().getSessions().broadcast(new MassEventMessageComposer("galert/@gm/"+client.getPlayer().getEntity().getRoom().getId()+"/"+client.getPlayer().getData().getUsername()+"/"+this.merge(params, 1)));
            sendNotif("Enviado", client);
        }

        if(params[0].equals("event")){
            NetworkManager.getInstance().getSessions().broadcast(new MassEventMessageComposer("galert/@ev/"+client.getPlayer().getEntity().getRoom().getId()+"/"+client.getPlayer().getData().getUsername()+"/"+this.merge(params, 1)));
            sendNotif("Enviado", client);
        }

        if(params[0].equals("publi")){
            NetworkManager.getInstance().getSessions().broadcast(new MassEventMessageComposer("galert/@pbl/"+client.getPlayer().getEntity().getRoom().getId()+"/"+client.getPlayer().getData().getUsername()+"/"+this.merge(params, 1)));
            sendNotif("Enviado", client);
        }
    }

    @Override
    public String getPermission() {
        return "globalalert_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.globalalert.description");
    }
}
