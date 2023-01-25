package com.cometproject.server.protocol.codec.websockets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.util.List;

public class WebSocketFrameCodec extends MessageToMessageCodec<WebSocketFrame, ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        out.add(new BinaryWebSocketFrame(in).retain());
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, WebSocketFrame in, List<Object> out) {
        out.add(in.content().retain());
    }
}