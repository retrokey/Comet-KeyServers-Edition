package com.cometproject.server.game.commands.development;

import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.sessions.Session;
import org.apache.commons.lang.StringUtils;

public class FastProcessCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        int processTime = 490;

        if (params.length == 1 && StringUtils.isNumeric(params[0])) {
            processTime = Integer.parseInt(params[0]);
        }

        client.getPlayer().getEntity().getRoom().getProcess().setDelay(processTime);
    }

    @Override
    public String getPermission() {
        return "dev";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public boolean isHidden() {
        return true;
    }
}
