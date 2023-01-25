package com.cometproject.server.game.commands.user.room;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.sessions.Session;

public class ChangeFloorEditCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        client.getPlayer().getEntity().floorEditCustom = !client.getPlayer().getEntity().floorEditCustom;

        final String msg = client.getPlayer().getEntity().floorEditCustom ? "disabled" : "enabled";
        sendNotif(Locale.get("command.flooredit." + msg), client);
    }

    @Override
    public String getPermission() {
        return "flooredit_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.flooredit.description");
    }
}
