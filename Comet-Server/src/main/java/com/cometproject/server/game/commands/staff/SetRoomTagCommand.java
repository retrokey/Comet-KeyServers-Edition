package com.cometproject.server.game.commands.staff;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.sessions.Session;


public class SetRoomTagCommand extends ChatCommand {

    @Override
    public void execute(Session client, String[] params) {
        if (params.length != 1) {
            sendNotif("Etiqueta inválida", client);
            return;
        }

        client.getPlayer().getEntity().getRoom().getData().setTags(params);
        sendNotif("Seteado", client);
    }

    @Override
    public String getPermission() {
        return "warp_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return "";
    }
}
