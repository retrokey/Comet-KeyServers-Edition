package com.cometproject.server.network.websockets;

import com.cometproject.api.config.CometSettings;
import com.cometproject.api.utilities.JsonUtil;
import com.cometproject.server.network.websockets.packets.incoming.*;
import com.cometproject.server.network.websockets.packets.incoming.battleroyale.AsyncMovementHandler;
import com.cometproject.server.network.websockets.packets.incoming.battleroyale.BattleRoyaleLeaveQueueHandler;
import com.cometproject.server.network.websockets.packets.incoming.battleroyale.BattleRoyaleWeaponSwapHandler;
import com.cometproject.server.network.websockets.packets.incoming.minigames.AcceptDuelSuggestionHandler;
import com.cometproject.server.network.websockets.packets.incoming.minigames.AcceptMinigameSuggestionHandler;
import com.cometproject.server.network.websockets.packets.incoming.minigames.VisitEventHandler;
import com.cometproject.server.network.websockets.packets.incoming.player.*;
import com.cometproject.server.network.websockets.packets.incoming.system.SubscriptionRevisionHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.websocketx.*;


import java.util.HashMap;
import java.util.Map;


public class WebSocketHandler extends SimpleChannelInboundHandler<HttpRequest> {

    private WebSocketServerHandshaker handshaker;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpRequest httpRequest) throws Exception {
        HttpHeaders headers = httpRequest.headers();

        if (headers.get("connection").equalsIgnoreCase("Upgrade") && headers.get("upgrade").equalsIgnoreCase("webSocket")) {

            ctx.pipeline().replace(this, "websocketHandler", new FrameHandler());
            handleHandshake(ctx, httpRequest);
            WebSocketSessionManager.getInstance().addChannel(ctx);
        } else {
            System.out.println("Error");
        }
    }

    private void handleHandshake(ChannelHandlerContext ctx, HttpRequest req) {
        WebSocketServerHandshakerFactory wsFactory =
                new WebSocketServerHandshakerFactory(getWebSocketURL(req), null, true);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    private static String getWebSocketURL(HttpRequest req) {
        return CometSettings.webSocketUrl.replace("%host", req.headers().get("Host").replace("%uri", req.getUri()));
        //return "wss://" + req.headers().get("Host") + "/websocket";
        //return "ws://" + req.headers().get("Host") + req.getUri();
    }

    class FrameHandler extends ChannelInboundHandlerAdapter {
        private final Map<String, IWebSocketHandler> handlers;

        FrameHandler() {
            this.handlers = new HashMap<>();
            this.handlers.put("authentication", new AuthenticationHandler());
            this.handlers.put("visitEvent", new VisitEventHandler());
            this.handlers.put("callForHelp", new CallForHelpHandler());
            this.handlers.put("asyncMove", new AsyncMovementHandler());
            this.handlers.put("builderSync", new BuilderSyncHandler());
            this.handlers.put("leaveQueue", new BattleRoyaleLeaveQueueHandler());
            this.handlers.put("acceptMinigame", new AcceptMinigameSuggestionHandler());
            this.handlers.put("acceptDuel", new AcceptDuelSuggestionHandler());
            this.handlers.put("handleMacro", new MacroHandler());
            this.handlers.put("weaponSwap", new BattleRoyaleWeaponSwapHandler());
            this.handlers.put("verifyOffer", new SubscriptionRevisionHandler());
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof WebSocketFrame) {
                if (msg instanceof TextWebSocketFrame) {

                    IncomingPayload incomingPayload = JsonUtil.getInstance().fromJson(((TextWebSocketFrame) msg).text(), IncomingPayload.class);
                    if (this.handlers.containsKey(incomingPayload.handler)) {
                        this.handlers.get(incomingPayload.handler).handle(ctx, ((TextWebSocketFrame) msg).text());
                    }

                } else if (msg instanceof CloseWebSocketFrame) {

                    WebSocketSessionManager.getInstance().removeChannel(ctx);
                }
            }
        }
    }

    private class IncomingPayload {
        private String handler;
    }

}
