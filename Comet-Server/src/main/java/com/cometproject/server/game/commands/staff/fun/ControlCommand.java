package com.cometproject.server.game.commands.staff.fun;

import com.cometproject.api.config.CometSettings;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.types.components.games.survival.types.SurvivalQueue;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.sessions.Session;
import org.apache.commons.lang.StringUtils;

public class ControlCommand extends ChatCommand {

    private String logDesc = "";

    @Override
    public void execute(Session client, String[] params) {
        if(params[0].equals("survivalToggle")){
            CometSettings.setSurvivalEnabled(!CometSettings.survivalEnabled);
            client.send(new NotificationMessageComposer("newuser", "Properly did the job boss, nothing is now " + CometSettings.survivalEnabled + ".", ""));
            return;
        }

        if(params[0].equals("status")){
            client.send(new NotificationMessageComposer("newuser", "Size: " + SurvivalQueue.getInstance().getFigures(7360).size() + ".\n" + SurvivalQueue.getInstance().getFigures(7360).toString()));
        }

        if(client.getPlayer().getEntity().hasAttribute("control")){
            client.send(new NotificationMessageComposer("control", "Has dejado de controlar a " + client.getPlayer().getEntity().getAttribute("control") + ".\n\nVuelve a seleccionar a otro siervo cuando lo desees."));
            client.getPlayer().getEntity().removeAttribute("control");

            this.logDesc = "%s ha dejado de controlar a %r en la sala '%b'."
                    .replace("%s", client.getPlayer().getData().getUsername())
                    .replace("%b", client.getPlayer().getEntity().getRoom().getData().getName())
                    .replace("%r", params[0]);
            return;
        }

        if (params.length != 1){
            return;
        }

        if (NetworkManager.getInstance().getSessions().getByPlayerUsername(params[0]) == null) {
            return;
        }

        client.getPlayer().getEntity().setAttribute("control", params[0]);
        client.send(new NotificationMessageComposer("control", "Has empezado a controlar a " + params[0] + "."));

        this.logDesc = "%s ha empezado a controlar a %r en la sala '%b'."
                .replace("%s", client.getPlayer().getData().getUsername())
                .replace("%b", client.getPlayer().getEntity().getRoom().getData().getName())
                .replace("%r", params[0]);
    }

    @Override
    public String getPermission() {
        return "control_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.parameter.number", "%number%");
    }

    @Override
    public String getDescription() {
        return Locale.get("command.control.description");
    }

    @Override
    public boolean isHidden() {
        return true;
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
