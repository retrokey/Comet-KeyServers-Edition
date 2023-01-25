package com.cometproject.server.network.websockets.packets.incoming.battleroyale;

import com.cometproject.server.game.rooms.types.components.games.survival.types.SurvivalQueue;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.network.websockets.packets.incoming.AbstractWebSocketHandler;
import io.netty.channel.ChannelHandlerContext;

public class BattleRoyaleLeaveQueueHandler extends AbstractWebSocketHandler<BattleRoyaleLeaveQueueHandler.ASMData> {

    public BattleRoyaleLeaveQueueHandler() {
        super(ASMData.class);
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ASMData eventData) {
        int playerId = Integer.parseInt(eventData.session);
        int roomId = Integer.parseInt(eventData.roomId);
        Session s = NetworkManager.getInstance().getSessions().getByPlayerId(playerId);

        if(s != null){
            if(s.getPlayer().antiSpam(getClass().getName(), 0.5))
                return;

            if(SurvivalQueue.getInstance().playerHasQueue(roomId, playerId)){
                SurvivalQueue.getInstance().removePlayerFromQueue(roomId, playerId, s.getPlayer().getQueueData());
            }
        }

    }

    class ASMData {
        private String session;
        private String roomId;
    }
}
