package com.cometproject.server.game.commands.staff.alerts;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.sessions.Session;

public class ReminderCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        globalNotification(Locale.getOrDefault("reminder.image", "EventG"), Locale.getOrDefault("reminder.text", "Actualmente hay un evento en curso, haz click aquí por si te lo habías perdido y quieres participar.\n\n- -u").replace("-u", client.getPlayer().getData().getUsername()), client.getPlayer().getEntity().getRoom().getId());
    }

    protected void globalNotification(String image, String message, int roomId) {
        NetworkManager.getInstance().getSessions().broadcast(new NotificationMessageComposer(image, message, "navigator/goto/" + roomId));
    }

    @Override
    public String getPermission() {
        return "eventalert_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.reminder.description");
    }
}
