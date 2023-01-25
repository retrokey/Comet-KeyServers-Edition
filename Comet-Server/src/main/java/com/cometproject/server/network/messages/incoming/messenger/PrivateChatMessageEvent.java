package com.cometproject.server.network.messages.incoming.messenger;

import com.cometproject.api.config.CometSettings;
import com.cometproject.api.game.GameContext;
import com.cometproject.api.game.groups.types.IGroup;
import com.cometproject.api.game.players.data.components.messenger.IMessengerFriend;
import com.cometproject.api.networking.sessions.ISession;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.moderation.ModerationManager;
import com.cometproject.server.game.rooms.RoomManager;
import com.cometproject.server.game.rooms.filter.FilterResult;
import com.cometproject.server.logging.LogManager;
import com.cometproject.server.logging.entries.MessengerChatLogEntry;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.messenger.InstantChatMessageComposer;
import com.cometproject.server.network.messages.outgoing.messenger.MessengerErrorMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.AdvancedAlertMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;


public class PrivateChatMessageEvent implements Event {
    public void handle(Session client, MessageEvent msg) {
        int userId = msg.readInt();
        String message = msg.readString();

        final int timeMutedExpire = client.getPlayer().getData().getTimeMuted() - (int) Comet.getTime();

        if (client.getPlayer().getData().getTimeMuted() != 0) {
            if (client.getPlayer().getData().getTimeMuted() > (int) Comet.getTime()) {
                client.getPlayer().getSession().send(new AdvancedAlertMessageComposer(Locale.getOrDefault("command.mute.muted", "You are muted for violating the rules! Your mute will expire in %timeleft% seconds").replace("%timeleft%", timeMutedExpire + "")));
                return;
            }
        }

        final long time = System.currentTimeMillis();

        if (!client.getPlayer().getPermissions().getRank().roomFilterBypass()) {
            FilterResult filterResult = RoomManager.getInstance().getFilter().filter(message);

            if (filterResult.isBlocked()) {
                filterResult.sendLogToStaffs(client, "ConsoleMessage");
                client.send(new AdvancedAlertMessageComposer(Locale.get("game.message.blocked").replace("%s", filterResult.getMessage())));
                return;
            } else if (filterResult.wasModified()) {
                message = filterResult.getMessage();
            }
        }

        if (userId == Integer.MIN_VALUE + 5000 && client.getPlayer().getPermissions().getRank().messengerStaffChat()) {
            for (Session player : ModerationManager.getInstance().getModerators()) {
                if (player == client) continue;
                player.send(new InstantChatMessageComposer(message, userId, client.getPlayer().getData().getUsername(), client.getPlayer().getData().getFigure(), client.getPlayer().getId()));
            }
            return;
        }

        if (userId == Integer.MIN_VALUE + 5000 && client.getPlayer().getPermissions().getRank().messengerModChat()) {
            for (Session player : ModerationManager.getInstance().getModerators()) {
                if (player == client) continue;
                player.send(new InstantChatMessageComposer(message, userId, client.getPlayer().getData().getUsername(), client.getPlayer().getData().getFigure(), client.getPlayer().getId()));
            }
            return;
        }

        if (userId == Integer.MIN_VALUE + 5001 && client.getPlayer().getPermissions().getRank().messengerAlfaChat()) {
            for (Session player : ModerationManager.getInstance().getAlfas()) {
                if (player == client) continue;
                player.send(new InstantChatMessageComposer(message, userId, client.getPlayer().getData().getUsername(), client.getPlayer().getData().getFigure(), client.getPlayer().getId()));
            }
            return;
        }

        if (userId == Integer.MAX_VALUE - 1 && client.getPlayer().getPermissions().getRank().messengerLogChat()) {
            return;
        }

        if (userId < 0 && CometSettings.groupChatEnabled) {
            final int groupId = -userId;
            final IGroup group = GameContext.getCurrent().getGroupService().getGroup(groupId);

            if (group != null && client.getPlayer().getGroups().contains(groupId)) {
                group.getMembers().broadcastMessage(NetworkManager.getInstance().getSessions(), new InstantChatMessageComposer(message, userId, client.getPlayer().getData().getUsername(), client.getPlayer().getData().getFigure(), client.getPlayer().getId()), client.getPlayer().getId());
            }

            return;
        }

        IMessengerFriend friend = client.getPlayer().getMessenger().getFriendById(userId);

        if (friend == null) {
            return;
        }

        ISession friendClient = friend.getSession();

        if (friendClient == null) {
            client.send(new MessengerErrorMessageComposer(5, friend.getUserId()));
            return;
        }

        try {
            if (LogManager.ENABLED && CometSettings.messengerLogMessages)
                LogManager.getInstance().getStore().getLogEntryContainer().put(new MessengerChatLogEntry(client.getPlayer().getId(), userId, message));
        } catch (Exception ignored) {

        }
        friendClient.send(new InstantChatMessageComposer(message, client.getPlayer().getId()));
    }
}