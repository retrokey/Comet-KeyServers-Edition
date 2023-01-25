package com.cometproject.server.network.messages.incoming.catalog.groups;

import com.cometproject.api.game.GameContext;
import com.cometproject.api.game.groups.types.IGroupData;
import com.cometproject.api.networking.messages.IMessageComposer;
import com.cometproject.server.composers.group.GroupDataMessageComposer;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;

public class GroupFurnitureCatalogMessageEvent
implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        ArrayList groupData = Lists.newArrayList();
        for (Integer groupId : client.getPlayer().getGroups()) {
            IGroupData data = GameContext.getCurrent().getGroupService().getData(groupId.intValue());
            if (data == null) continue;
            groupData.add(data);
        }
        client.send(new GroupDataMessageComposer((List)groupData, GameContext.getCurrent().getGroupService().getItemService(), client.getPlayer().getId()));
    }
}

