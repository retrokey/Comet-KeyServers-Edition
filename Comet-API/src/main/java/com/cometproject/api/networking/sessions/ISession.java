package com.cometproject.api.networking.sessions;

import com.cometproject.api.game.players.IPlayer;
import com.cometproject.api.networking.messages.IMessageComposer;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;

public interface ISession {
    IPlayer getPlayer();

    void disconnect();

    ISession send(IMessageComposer messageComposer);

    ISession sendQueue(IMessageComposer messageComposer);

    String getIpAddress();

    Logger getLogger();

    ChannelHandlerContext getChannel();

    void flush();
}
