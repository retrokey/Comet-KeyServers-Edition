package com.cometproject.server.game.commands.staff.alerts;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.moderation.ModerationManager;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.sessions.Session;

public class GlobalBubbleCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] message) {
        final NotificationMessageComposer msg = new NotificationMessageComposer(Locale.get("command.globalbubble.image"), this.merge(message) + "\n\n- " + client.getPlayer().getData().getUsername());

        if (message.length == 0) {
            return;
        }

        NetworkManager.getInstance().getSessions().broadcast(msg);
    }

    @Override
    public boolean isAsync() {
        return true;
    }

    @Override
    public String getPermission() {
        return "globalbubble_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.globalbubble.message", "%message%");
    }

    @Override
    public String getDescription() {
        return Locale.get("command.globalbubble.description");
    }
}