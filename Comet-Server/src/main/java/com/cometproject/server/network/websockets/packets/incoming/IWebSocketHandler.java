package com.cometproject.server.network.websockets.packets.incoming;

import io.netty.channel.ChannelHandlerContext;

public interface IWebSocketHandler {
    void handle(ChannelHandlerContext ctx, String data);
}
