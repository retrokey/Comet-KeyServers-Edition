package com.cometproject.server.network.messages.incoming.catalog.ads;

import com.cometproject.api.game.GameContext;
import com.cometproject.api.game.rooms.IRoomData;
import com.cometproject.api.game.rooms.settings.RoomAccessType;
import com.cometproject.api.networking.messages.IMessageComposer;
import com.cometproject.server.composers.catalog.ads.CatalogPromotionGetRoomsMessageComposer;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;

public class CatalogPromotionGetRoomsMessageEvent
implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        ArrayList roomDataList = Lists.newArrayList();
        for (Integer roomId : client.getPlayer().getRooms()) {
            IRoomData data = GameContext.getCurrent().getRoomService().getRoomData(roomId.intValue());
            if (data == null || data.getAccess() != RoomAccessType.OPEN) continue;
            roomDataList.add(data);
        }
        client.send(new CatalogPromotionGetRoomsMessageComposer((List)roomDataList));
    }
}

