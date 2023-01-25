package com.cometproject.server.network.websockets.packets.incoming.player;

import com.cometproject.api.config.CometSettings;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.network.websockets.packets.incoming.AbstractWebSocketHandler;
import io.netty.channel.ChannelHandlerContext;

public class CallForHelpHandler extends AbstractWebSocketHandler<CallForHelpHandler.CFHData> {

    public CallForHelpHandler() {
        super(CFHData.class);
    }

    @Override
    public void handle(ChannelHandlerContext ctx, CFHData eventData) {
        Session s = NetworkManager.getInstance().getSessions().getByPlayerId(Integer.parseInt(eventData.session));

        if(s != null) {
            int time = (int) Comet.getTime();
            int timeSinceLastUpdate = time - s.getPlayer().getLastCFH();

            if(timeSinceLastUpdate >= CometSettings.callForHelpCooldown){
                /*s.send(new NuxMessageComposer("help/tour"));*/
                s.getPlayer().setLastCFH(time);
                return;
            }

            s.send(new NotificationMessageComposer("ambassador", "Tu dois encore patienter " + (300 - timeSinceLastUpdate) + " secondes pour demander de l'aide.", ""));
        }

    }


    class CFHData {
        private String session;
    }
}
