package com.cometproject.server.game.commands.staff;

import com.cometproject.api.config.CometSettings;
import com.cometproject.api.networking.sessions.ISession;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.messenger.InviteFriendMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.MassEventMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.sessions.Session;


public class PreguntarCommand extends ChatCommand {

    @Override
    public void execute(Session client, String[] params) {
        if (params.length < 1) {
            sendNotif("¿Qué quieres preguntar?", client);
            return;
        }

        for (ISession session : NetworkManager.getInstance().getSessions().getSessions().values()){
            try{

                session.send(new TalkMessageComposer(session.getPlayer().getEntity().getId(), this.merge(params, 1), ChatEmotion.NONE, 2));
            }
            catch (Exception ignored) { }
        }
    }

    @Override
    public String getPermission() {
        return "preguntar_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.preguntar.description");
    }
}
