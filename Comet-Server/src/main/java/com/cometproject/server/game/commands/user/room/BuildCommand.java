package com.cometproject.server.game.commands.user.room;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.messages.outgoing.notification.MassEventMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.sessions.Session;

public class BuildCommand extends ChatCommand {

    @Override
    public void execute(Session client, String[] message) {
        if (!client.getPlayer().getEntity().getRoom().getRights().hasRights(client.getPlayer().getId())
                && !client.getPlayer().getPermissions().getRank().roomFullControl()) {
            return;
        }

        if(message.length == 1 && message[0].equals("dev")){
            boolean isDev = !client.getPlayer().isDeveloping();
            String display = isDev ? "Modo de desarrollo <b>activo</b>, haz <b>dos clicks</b> sobre los objetos para recibir <b>información</b>." : "Has <b>desactivado</b> el modo de desarrollo.";
            client.getPlayer().setIsDeveloping(isDev);
            client.send(new WhisperMessageComposer(-1, display, 33));
            return;
        }

        if (client.getPlayer().getIsBuilding()) {
            client.getPlayer().setIsBuilding(false);
            client.getPlayer().resetBuilding();
            client.send(new MassEventMessageComposer("habblet/open/buildAction?stop"));

        } else {
            client.getPlayer().setIsBuilding(true);
            client.send(new MassEventMessageComposer("habblet/open/buildAction?start"));
        }
    }

    @Override
    public String getPermission() {
        return "build_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.build.description");
    }
}