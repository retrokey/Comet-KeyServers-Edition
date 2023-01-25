package com.cometproject.server.protocol.messages;

import com.cometproject.api.networking.messages.IComposer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;

import java.nio.charset.StandardCharsets;

public class Composer implements IComposer, ByteBufHolder {
    protected final int id;
    protected final ByteBuf body;

    public Composer(short id, ByteBuf body) {
        this.id = id;
        this.body = body;

        try {
            this.body.writeInt(-1);
            this.body.writeShort(id);
        } catch (Exception e) {
            exceptionCaught(e);
        }
    }

    public Composer(int id, ByteBuf body) {
        this.id = id;
        this.body = body;
    }

    private void exceptionCaught(Exception e) {
        e.printStackTrace();
    }

    @Override
    public ByteBuf content() {
        return this.body;
    }

    @Override
    public Composer copy() {
        return new Composer(this.id, this.body.copy());
    }

    @Override
    public Composer duplicate() {
        return new Composer(this.id, this.body.duplicate());
    }

    @Override
    public int refCnt() {
        return this.body.refCnt();
    }

    @Override
    public Composer retain() {
        this.body.retain();
        return this;
    }

    @Override
    public Composer retainedDuplicate() {
        return new Composer(this.id, this.body.retainedDuplicate());
    }

    @Override
    public Composer replace(ByteBuf byteBuf) {
        return new Composer(this.id, byteBuf);
    }

    @Override
    public Composer retain(int increment) {
        this.body.retain(increment);
        return this;
    }

    @Override
    public boolean release() {
        return this.body.release();
    }

    @Override
    public boolean release(int decrement) {
        return this.body.release(decrement);
    }

    @Override
    public Composer touch() {
        this.body.touch();
        return this;
    }

    @Override
    public Composer touch(Object o) {
        this.body.touch(o);
        return this;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void clear() {
        this.body.clear();
    }

    @Override
    public boolean isFinalised() {
        return (this.body.getInt(0) > -1);
    }

    @Override
    public void writeString(Object obj) {
        try {
            String string = "";

            if (obj != null) {
                string = String.valueOf(obj);
            }

            byte[] dat = string.getBytes(StandardCharsets.UTF_8);
            this.body.writeShort(dat.length);
            this.body.writeBytes(dat);
        } catch (Exception e) {
            exceptionCaught(e);
        }
    }

    @Override
    public void writeDouble(double d) {
        this.writeString(Double.toString(d));
    }

    @Override
    public void writeInt(int i) {
        try {
            this.body.writeInt(i);
        } catch (Exception e) {
            exceptionCaught(e);
        }
    }

    @Override
    public void writeLong(long i) {
        try {
            this.body.writeLong(i);
        } catch (Exception e) {
            exceptionCaught(e);
        }
    }

    @Override
    public void writeBoolean(Boolean b) {
        try {
            this.body.writeByte(b ? 1 : 0);
        } catch (Exception e) {
            exceptionCaught(e);
        }
    }

    @Override
    public void writeByte(int b) {
        try {
            this.body.writeByte(b);
        } catch (Exception e) {
            exceptionCaught(e);
        }
    }

    @Override
    public void writeShort(int s) {
        try {
            this.body.writeShort((short) s);
        } catch (Exception e) {
            exceptionCaught(e);
        }
    }
}