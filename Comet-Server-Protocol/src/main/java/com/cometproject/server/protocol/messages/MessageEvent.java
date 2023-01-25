package com.cometproject.server.protocol.messages;

import com.cometproject.api.networking.messages.IMessageEvent;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;
import java.util.Arrays;

public final class MessageEvent implements IMessageEvent  {
    private final short id;
    private final ByteBuf buffer;

    public MessageEvent(short id, ByteBuf buf) {
        this.buffer = (buf != null) && (buf.readableBytes() > 0) ? buf : Unpooled.EMPTY_BUFFER;
        this.id = id;
    }

    @Override
    public short readShort() {
        return this.content().readShort();
    }

    @Override
    public int readInt() {
        try {
            return this.content().readInt();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public boolean readBoolean() {
        return this.content().readByte() == 1;
    }

    @Override
    public String readString() {
        final int length = this.readShort();
        final byte[] data = new byte[length];
        this.content().readBytes(data);
        return new String(data);
    }

    @Override
    public byte[] readBytes(int length) {
        final byte[] bytes = new byte[length];
        this.content().readBytes(bytes);
        return bytes;
    }

    @Override
    public byte[] toRawBytes() {
        byte[] complete = this.buffer.array();
        return Arrays.copyOfRange(complete, 6, complete.length);
    }

    public String toString() {
        String body = this.content().toString((Charset.defaultCharset()));

        for (int i = 0; i < 13; i++) {
            body = body.replace(Character.toString((char) i), "[" + i + "]");
        }

        return body;
    }

    @Override
    public short getId() {
        return this.id;
    }

    private ByteBuf content() {
        return this.buffer;
    }

    @Override
    public int getLength() {
        return buffer.readableBytes();
    }
}