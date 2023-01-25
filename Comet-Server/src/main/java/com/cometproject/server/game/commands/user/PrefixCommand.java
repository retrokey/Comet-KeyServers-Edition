package com.cometproject.server.game.commands.user;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.types.components.types.ChatMessageColour;
import com.cometproject.server.network.sessions.Session;

public class PrefixCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if(params.length != 1){
            client.getPlayer().getData().setTag("");
            client.getPlayer().getData().save();
            sendNotif("Prefijo eliminado. Para cambiar de prefijo usa :prefix prefijo", client);
            return;
        }

        if(client.getPlayer().getData().getRank() == 1){
            sendNotif("Este sistema es para VIPs", client);
            return;
        }

        client.getPlayer().getData().setTag(params[0]);
        client.getPlayer().getData().save();
        sendNotif("Prefijo actualizado con éxito", client);
    }

    @Override
    public String getPermission() {
        return "prefix_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.getOrDefault("command.prefix.description", "Cambia tu prefijo");
    }
}
