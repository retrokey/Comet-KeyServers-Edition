package com.cometproject.server.network.messages.incoming.user.details;

import com.cometproject.server.config.Locale;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.notification.MassEventMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.details.NuxStatusMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;
import com.cometproject.server.storage.queries.player.PlayerDao;

public class ProcessNUXMessageEvent implements Event {
    public void handle(Session client, MessageEvent msg) {
        if(client.getPlayer() == null) { return; }

        client.getPlayer().getSettings().incrementNuxStatus();
        int status = client.getPlayer().getSettings().getNuxStatus();

        String phase = status == 1 ? "CREDITS_BUTTON" : status == 2 ? "BLACK_BUTTON" : status == 3 ? "LIKE_ROOM_BUTTON" : status == 4 ? "MEMENU_ACHIEVEMENTS" : "MEMENU_CLOTHES";

        if(status < 6)
            client.send(new MassEventMessageComposer("helpBubble/add/" + phase + "/" + Locale.get(phase + status)));


        if(status == 6) {
            client.send(new NuxStatusMessageComposer(0));
            PlayerDao.updateNuxStatus(6, client.getPlayer().getData().getId());
        }
    }
}
