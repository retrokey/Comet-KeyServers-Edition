package com.cometproject.server.network.websockets.packets.incoming;

import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;

public abstract class AbstractWebSocketHandler<TData> implements IWebSocketHandler {

    private static Gson gson = new Gson();

    private final Class<TData> type;

    public AbstractWebSocketHandler(Class<TData> type) {
        this.type = type;
    }

    public abstract void handle(ChannelHandlerContext ctx, TData data);

    @Override
    public void handle(ChannelHandlerContext ctx, String data) {
        handle(ctx, gson.fromJson(data, this.type));
    }
}
