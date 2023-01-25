package com.cometproject.server.game.commands.user;

import com.cometproject.api.config.CometSettings;
import com.cometproject.api.networking.messages.IMessageComposer;
import com.cometproject.api.networking.sessions.ISession;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.moderation.ModerationManager;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.landing.TargettedOfferMessageComposer;
import com.cometproject.server.network.messages.outgoing.messenger.InstantChatMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.AdvancedAlertMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.sessions.Session;

public class StaffOnCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        StringBuilder text = new StringBuilder();
        for (ISession session : NetworkManager.getInstance().getSessions().getSessions().values()){
            if(session.getPlayer().getData().getRank() != 1){
                text.append(session.getPlayer().getData().getUsername() + " | " + session.getPlayer().getData().getRank()).append("\n");
            }
        }

        StaffOnCommand.sendAlert("Staffs en linea:\n\n" + text, client);
    }

    @Override
    public String getPermission() {
        return "staffonline_command";
    }
    @Override
    public String getParameter() {
        return Locale.getOrDefault(null, "");
    }
    @Override
    public String getDescription() {
        return Locale.get("command.staffonline.description");
    }
}

