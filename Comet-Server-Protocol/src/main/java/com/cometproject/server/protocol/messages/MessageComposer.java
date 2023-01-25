package com.cometproject.server.protocol.messages;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.api.networking.messages.IMessageComposer;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class MessageComposer implements IMessageComposer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageComposer.class);

    public MessageComposer() {
    }

    public final IComposer writeMessage(ByteBuf buf) {
        return this.writeMessageImpl(buf);
    }

    public final Composer writeMessageImpl(ByteBuf buffer) {
        final Composer composer = new Composer(this.getId(), buffer);

        // Do anything we need to do with the buffer.
        try {
            this.compose(composer);
        } catch (Exception e) {
            LOGGER.error("Error composing message " + this.getId() + " / " + this.getClass().getSimpleName(), e);
            throw e;
        } finally {
            this.dispose();
        }

        return composer;
    }

    public abstract short getId();

    public abstract void compose(IComposer msg);

    public void dispose() {

    }
}
