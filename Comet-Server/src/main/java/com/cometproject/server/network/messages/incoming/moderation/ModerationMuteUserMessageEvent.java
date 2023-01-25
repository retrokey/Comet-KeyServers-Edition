package com.cometproject.server.network.messages.incoming.moderation;

import com.cometproject.server.boot.Comet;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.moderation.BanManager;
import com.cometproject.server.game.moderation.types.BanType;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.notification.AdvancedAlertMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class ModerationMuteUserMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        final int playerId = msg.readInt();
        int muteLength = msg.readInt();

        // TODO: use length.

        if (!client.getPlayer().getPermissions().getRank().modTool()) {
            client.disconnect();

            return;
        }

        Session session = NetworkManager.getInstance().getSessions().getByPlayerId(playerId);

        muteLength = 3600;

        if (session != null) {
            session.send(new AdvancedAlertMessageComposer(Locale.get("command.mute.muted").replace("%timeleft", muteLength + "")));
        }

        BanManager.getInstance().banPlayer(BanType.MUTE, session.getPlayer().getId() + "", muteLength, Comet.getTime() + muteLength, Locale.getOrDefault("command.advban.reason", "Advertising"), client.getPlayer().getId());
    }
}
