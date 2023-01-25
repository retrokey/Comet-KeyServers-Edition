package com.cometproject.server.network.messages.incoming.help.guides;

import com.cometproject.server.game.guides.types.HelpRequest;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.help.guides.GuideSessionInviteMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;


public class GuideRoomInviteMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        final HelpRequest helpRequest = client.getPlayer().getHelpRequest();
        final Room r = client.getPlayer().getEntity().getRoom();

        helpRequest.getOtherElement(client, helpRequest).send(new GuideSessionInviteMessageComposer(r.getId(), r.getData().getName()));
        client.send(new GuideSessionInviteMessageComposer(r.getId(), r.getData().getName()));
    }
}
