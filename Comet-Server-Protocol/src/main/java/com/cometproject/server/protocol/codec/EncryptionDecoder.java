package com.cometproject.server.protocol.codec;


import com.cometproject.server.protocol.crypto.exceptions.HabboRC4;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class EncryptionDecoder extends ByteToMessageDecoder {

    private final HabboRC4 rc4;

    public EncryptionDecoder(byte[] key) {
        this.rc4 = new HabboRC4(key);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        out.add(this.rc4.decipher(in));
    }
}
