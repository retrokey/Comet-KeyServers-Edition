package com.cometproject.server.game.commands.user.room;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.sessions.Session;

public class StateCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        int state;

        try {
            state = Integer.parseInt(params[0]);
        } catch (Exception e) {
            state = -1;
        }

        if (state < -1 || state > 100) {
            sendNotif(Locale.get("command.height.invalid"), client);
            return;
        }

        client.getPlayer().setItemPlacementState(state);
        sendNotif(Locale.get("command.height.set").replace("%height%", "" + state), client);
    }

    @Override
    public String getPermission() {
        return "height_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.height.param", "%height%");
    }

    @Override
    public String getDescription() {
        return Locale.get("command.height.description");
    }
}

