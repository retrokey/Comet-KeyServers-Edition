package com.cometproject.server.game.commands.user.settings;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.storage.queries.player.PlayerDao;

public class SelectWindowModeCommand extends ChatCommand {

    @Override
    public void execute(Session client, String[] params) {
        if(params[0] == null)
            return;

        String commandName = params[0];
        String n = "Has establecido tus alertas con el método convencional.";

        switch (commandName) {
            case "1":
                client.getPlayer().getSettings().setEventType(commandName);
                break;
            case "2":
                client.getPlayer().getSettings().setEventType(commandName);
                n = "Has establecido tus alertas con el método dinámico.";
                break;
            case "3":
                client.getPlayer().getSettings().setEventType(commandName);
                n = "Has establecido tus alertas con la burbuja.";
                break;
            case "4":
                client.getPlayer().getSettings().setEventType(commandName);
                n = "Has establecido tus alertas con el susurro.";
                break;
            default:
                commandName = "1";
                client.getPlayer().getSettings().setEventType("1");
                break;
        }

        PlayerDao.updateEventType(commandName, client.getPlayer().getData().getId());
        client.send(new NotificationMessageComposer("event_type",n,"habbopages/info.txt"));
    }

    @Override
    public String getPermission() {
        return "selectwindow_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.window.description");
    }
}
