package com.cometproject.server.game.commands.staff.alerts;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.sessions.Session;


public class WhisperAlertCommand extends ChatCommand {

    @Override
    public void execute(Session client, String[] params) {
        NetworkManager.getInstance().getSessions().broadcast(new TalkMessageComposer(client.getPlayer().getEntity().getId(), this.merge(params), ChatEmotion.NONE, 34));
    }

    @Override
    public boolean isAsync() {
        return true;
    }

    @Override
    public String getPermission() {
        return "wha_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.parameter.message", "%message%");
    }

    @Override
    public String getDescription() {
        return Locale.get("command.whisperalert.description");
    }
}