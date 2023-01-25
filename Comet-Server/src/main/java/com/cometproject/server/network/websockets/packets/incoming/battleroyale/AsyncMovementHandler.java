package com.cometproject.server.network.websockets.packets.incoming.battleroyale;

import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.network.websockets.packets.incoming.AbstractWebSocketHandler;
import io.netty.channel.ChannelHandlerContext;

public class AsyncMovementHandler extends AbstractWebSocketHandler<AsyncMovementHandler.ASMData> {

    public AsyncMovementHandler() {
        super(ASMData.class);
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ASMData eventData) {
        Session s = NetworkManager.getInstance().getSessions().getByPlayerId(Integer.parseInt(eventData.session));

        if(s != null && s.getPlayer() != null && s.getPlayer().getEntity() != null){
            if(s.getPlayer().antiSpam(getClass().getName(), 0.5))
                return;

            PlayerEntity player = s.getPlayer().getEntity();

            if (player.hasAttribute("tpencours")) {
                return;
            }

            if (player.hasAttribute("warp")) {
                return;
            }

            if (player.hasAttribute(("tptpencours"))) {
                return;
            }

            switch (eventData.direction){
                case 37:
                    player.moveTo(player.getPosition().getX() - 1, player.getPosition().getY());
                    break;
                case 38:
                    player.moveTo(player.getPosition().getX(), player.getPosition().getY() - 1);
                    break;
                case 39:
                    player.moveTo(player.getPosition().getX() + 1, player.getPosition().getY());
                    break;
                case 40:
                    player.moveTo(player.getPosition().getX(), player.getPosition().getY() + 1);
                    break;
            }
        }

    }

    class ASMData {
        private String session;
        private Integer direction;
    }
}
