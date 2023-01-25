package com.cometproject.server.game.commands.staff.cache;

import com.cometproject.api.config.CometSettings;
import com.cometproject.games.snowwar.SnowPlayerQueue;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.sessions.Session;

public class SnowStormManagerCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if (params.length != 1) return;

        final String type = params[0];
        final String alert;

        switch (type){
            case "clear":
                SnowPlayerQueue.cleanupQueues();
                alert = "Acabas de limpiar las colas del Snow Storm.";
                break;
            case "toggle":
                CometSettings.toggleSnowStorm(true);
                alert = "Acabas de activar el Snow Storm.";
                break;
            case "disable":
                CometSettings.toggleSnowStorm(false);
                alert = "Acabas de desactivar el Snow Storm.";
                break;
            default:
                alert = "";
        }

        client.getPlayer().sendBubble("snow", alert);

    }

    @Override
    public String getPermission() {
        return "vipbundle_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.parameter.type", "%type%");
    }

    @Override
    public String getDescription() {
        return Locale.getOrDefault("command.vipbundle.description", "Da un pack de VIP al usuario seleccionado.");
    }
}
