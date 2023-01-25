package com.cometproject.server.network.websockets.packets.incoming.minigames;

import com.cometproject.api.config.CometSettings;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.room.engine.RoomForwardMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.network.websockets.packets.incoming.AbstractWebSocketHandler;
import io.netty.channel.ChannelHandlerContext;

public class VisitEventHandler extends AbstractWebSocketHandler<VisitEventHandler.EventData> {

    public VisitEventHandler() {
        super(EventData.class);
    }

    @Override
    public void handle(ChannelHandlerContext ctx, EventData eventData) {
        Session s = NetworkManager.getInstance().getSessions().getByPlayerId(Integer.parseInt(eventData.session));

        if(s != null) {
            s.send(new RoomForwardMessageComposer(CometSettings.currentEventRoom));
        }

    }

    class EventData {
        private String roomId;
        private String session;
    }
}
