package com.cometproject.server.protocol.codec.websockets;

import com.cometproject.api.config.Configuration;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolConfig;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.util.CharsetUtil;

public class MessageInterceptorHandler extends ChannelInboundHandlerAdapter {
    public static final String NAME = "messageInterceptor";

    private static final int MAX_PACKET_LENGTH = Integer.parseInt(Configuration.currentConfig().get("comet.network.maxPacketSize", "500000"));
    private final WebSocketServerProtocolConfig config = WebSocketServerProtocolConfig.newBuilder()
            .websocketPath("/")
            .checkStartsWith(true)
            .maxFramePayloadLength(MAX_PACKET_LENGTH)
            .build();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        if(byteBuf.toString(CharsetUtil.UTF_8).startsWith("GET")) {
            ctx.pipeline().addAfter(NAME, "websocketHandler", new WebSocketFrameCodec());
            ctx.pipeline().addAfter(NAME, "protocolHandler", new WebSocketServerProtocolHandler(config));
            ctx.pipeline().addAfter(NAME, "customHttpHandler", new HttpCustomHandler());
            ctx.pipeline().addAfter(NAME, "objectAggregator", new HttpObjectAggregator(MAX_PACKET_LENGTH));
            ctx.pipeline().addAfter(NAME, "httpCodec", new HttpServerCodec());
        }

        super.channelRead(ctx, msg);
        ctx.pipeline().remove(this);
    }
}