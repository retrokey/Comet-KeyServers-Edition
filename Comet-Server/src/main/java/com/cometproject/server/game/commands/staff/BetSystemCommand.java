package com.cometproject.server.game.commands.staff;

import com.cometproject.api.config.CometSettings;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.sessions.Session;

public class BetSystemCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if (CometSettings.casinoFreeRolls) {
            CometSettings.setCasinoFreeRolls(false);
            sendNotif(Locale.getOrDefault("command.betsystem.disabled", "Acabas de desactivar las apuestas gratuitas."), client);
        } else {
            CometSettings.setCasinoFreeRolls(true);
            sendNotif(Locale.getOrDefault("command.betsystem.enabled", "Acabas de activar las apuestas gratuitas."), client);
        }
    }

    @Override
    public String getPermission() {
        return "betsystem_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.betsystem.description");
    }
}
