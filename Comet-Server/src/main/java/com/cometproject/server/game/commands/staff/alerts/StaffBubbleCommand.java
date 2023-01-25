package com.cometproject.server.game.commands.staff.alerts;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.moderation.ModerationManager;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.sessions.Session;

public class StaffBubbleCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] message) {
        final NotificationMessageComposer msg = new NotificationMessageComposer(Locale.get("command.staffbubble.image"), this.merge(message) + "\n\n- " + client.getPlayer().getData().getUsername());

        if (message.length == 0) {
            return;
        }

        for (Session player : ModerationManager.getInstance().getModerators()) {
            player.send(msg);
        }

    }

    @Override
    public boolean isAsync() {
        return true;
    }

    @Override
    public String getPermission() {
        return "staffbubble_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.parameter.message", "%message%");
    }

    @Override
    public String getDescription() {
        return Locale.get("command.staffbubble.description");
    }
}