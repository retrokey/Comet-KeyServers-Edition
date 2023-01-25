package com.cometproject.server.protocol.codec;

import com.cometproject.server.protocol.messages.MessageEvent;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MessageDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        try {
            short header = in.readShort();

            out.add(new MessageEvent(header, in.readBytes(in.readableBytes())));
//            ctx.fireChannelReadComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}