package com.cometproject.server.game.commands.user;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.types.components.types.ChatMessageColour;
import com.cometproject.server.network.sessions.Session;

public class ColourCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        BankCommand.sendNotif("Uso del comando incorrecto. :pay usuario cantidad moneda.", client);
        System.out.println("ola xd");
    }

    @Override
    public String getPermission() {
        return "colour_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.parameter.colour", "%colour%");
    }

    @Override
    public String getDescription() {
        return Locale.getOrDefault("command.colour.description", "Allows you to change the colour of your chat messages");
    }
}
