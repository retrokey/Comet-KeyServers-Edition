package com.cometproject.server.game.rooms.filter;

import com.cometproject.api.game.rooms.filter.IFilterResult;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.moderation.ModerationManager;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.messages.outgoing.messenger.InstantChatMessageComposer;
import com.cometproject.server.network.messages.outgoing.messenger.InviteFriendMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.sessions.Session;

public class FilterResult implements IFilterResult {
    private boolean isBlocked;
    private boolean wasModified;
    private String message;

    public FilterResult(String chatMessage) {
        this.isBlocked = false;
        this.wasModified = false;
        this.message = chatMessage;
    }

    public FilterResult(boolean isBlocked, String chatMessage) {
        this.isBlocked = isBlocked;
        this.wasModified = false;
        this.message = chatMessage;
    }

    public FilterResult(String chatMessage, boolean wasModified) {
        this.isBlocked = false;
        this.wasModified = wasModified;
        this.message = chatMessage;
    }

    @Override
    public boolean isBlocked() {
        return isBlocked;
    }

    public void sendLogToStaffs(Session client, String where) {
        for (Session player : ModerationManager.getInstance().getModerators()) {
            player.send(new InstantChatMessageComposer(Locale.getOrDefault("staff.chat.filter", "The user %s has triggered the filter on %b: [%c]")
                    .replace("%s", client.getPlayer().getData().getUsername())
                    .replace("%b", where)
                    .replace("%c", this.message), Integer.MIN_VALUE + 5000, "Spammer detected:", "sh-3089-92.ch-5516282-64-1336.lg-3058-1336.ha-51908423-73-1322.fa-1211-62.hd-3091-8.hr-115-1036.cc-58788570-73-73", 0));


            player.send(new TalkMessageComposer(-1, "<b>" + client.getPlayer().getData().getUsername() + "</b> acaba de saltarse el filtro diciendo: <i>" + this.message + "</i>. <b>Método:</b> " + where +".", ChatEmotion.NONE, 34));

        }
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public boolean wasModified() {
        return wasModified;
    }
}
