package com.cometproject.server.network.messages.incoming.group;

import com.cometproject.api.game.GameContext;
import com.cometproject.server.composers.catalog.groups.GroupElementsMessageComposer;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class RequestGuildPartsMessageEvent implements Event {
    public void handle(Session client, MessageEvent msg) {
        if(client != null)
        client.send(new GroupElementsMessageComposer(GameContext.getCurrent().getGroupService().getItemService()));
    }
}
