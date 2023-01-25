package com.cometproject.server.network.websockets.packets.incoming.player;

import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.network.websockets.packets.incoming.AbstractWebSocketHandler;
import io.netty.channel.ChannelHandlerContext;

public class AuthenticationHandler extends AbstractWebSocketHandler<AuthenticationHandler.AuthenticationData> {

    public AuthenticationHandler() {
        super(AuthenticationData.class);
    }

    @Override
    public void handle(ChannelHandlerContext ctx, AuthenticationData authenticationData) {
        if(authenticationData.ssoTicket.isEmpty() || !isNumeric(authenticationData.ssoTicket))
            return;

        Session s = NetworkManager.getInstance().getSessions().getByPlayerId(Integer.parseInt(authenticationData.ssoTicket));

        if(s == null)
            return;

        if(s.getPlayer().antiSpam(getClass().getName(), 0.5))
            return;

        System.out.print("[WS] - Assigned context handler for user " + s.getPlayer().getData().getUsername() + ".\n");

        s.setWsChannel(ctx);

    }


    class AuthenticationData {
        private String ssoTicket;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
