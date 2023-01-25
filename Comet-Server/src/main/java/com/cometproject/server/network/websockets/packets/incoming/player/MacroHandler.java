package com.cometproject.server.network.websockets.packets.incoming.player;

import com.cometproject.server.network.websockets.packets.incoming.AbstractWebSocketHandler;
import io.netty.channel.ChannelHandlerContext;

public class MacroHandler extends AbstractWebSocketHandler<MacroHandler.ASMData> {

    public MacroHandler() {
        super(ASMData.class);
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ASMData eventData) {
        /*
        Session s = NetworkManager.getInstance().getSessions().getByPlayerId(Integer.parseInt(eventData.session));

        if(s != null && s.getPlayer() != null){

            switch (eventData.macro){
                case "navigator":
                    s.send(new MassEventMessageComposer("navigator/search/"));
                    break;
                case "inventory":
                    s.send(new MassEventMessageComposer("inventory/open/furni"));
                    break;
                case "catalog":
                    s.send(new MassEventMessageComposer("catalog/open"));
                    break;
            }
        }
        */

    }


    class ASMData {
        private String session;
        private String macro;
    }
}
