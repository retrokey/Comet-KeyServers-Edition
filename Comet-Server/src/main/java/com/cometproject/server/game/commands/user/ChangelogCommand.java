package com.cometproject.server.game.commands.user;

import com.cometproject.server.boot.Comet;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.messages.outgoing.notification.MassEventMessageComposer;
import com.cometproject.server.network.sessions.Session;

public class ChangelogCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if(client == null || client.getPlayer() == null || client.getPlayer().getEntity() == null || client.getPlayer().getEntity().getRoom() == null || params[0] == null)
            return;

        String lawea = params[0];


        client.send(new MassEventMessageComposer("habbopages/" + lawea + ".txt?" + Comet.getTime()));
        //client.send(new MassEventMessageComposer("friendlist/openchat/" + lawea));
    }

    @Override
    public String getPermission() {
        return "changelog_command";
    }

    @Override
    public String getDescription() {
        return Locale.getOrDefault("command.changelog.description", "Revisa todas las actualizaciones de HTHOR.");
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.parameter.colour", "");
    }

}
