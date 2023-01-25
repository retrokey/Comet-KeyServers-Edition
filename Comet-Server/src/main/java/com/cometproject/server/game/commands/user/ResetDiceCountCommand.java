package com.cometproject.server.game.commands.user;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.sessions.Session;

public class ResetDiceCountCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        try {
            if(client == null || client.getPlayer() == null || client.getPlayer().getEntity() == null)
                return;

            String messInfo = "Acabo de reiniciar mi tirada en dados, llevaba " + client.getPlayer().getEntity().getDiceCount() + ".";

            client.getPlayer().getEntity().resetDiceCount();
            client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new TalkMessageComposer(client.getPlayer().getEntity().getId(), messInfo, ChatEmotion.NONE, 26));
        } catch (Exception e) {
            sendNotif(Locale.getOrDefault("command.resetdicecount.invalid", "Oops, algo ha salido mal."), client);
        }
    }


    @Override
    public String getPermission() {
        return "resetdicecount_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.resetdicecount.description");
    }
}
