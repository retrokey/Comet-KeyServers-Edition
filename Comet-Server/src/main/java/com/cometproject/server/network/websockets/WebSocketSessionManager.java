package com.cometproject.server.network.websockets;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class WebSocketSessionManager {

    private Queue<ChannelHandlerContext> channelHandlerContexts = new ConcurrentLinkedQueue<>();

    public void addChannel(ChannelHandlerContext channel) {
        this.channelHandlerContexts.add(channel);
    }

    public void removeChannel(ChannelHandlerContext channel) {
        this.channelHandlerContexts.remove(channel);
    }

    public void sendMessage(String message) {
        for (ChannelHandlerContext context : this.channelHandlerContexts) {
            if(context != null)
            context.writeAndFlush(new TextWebSocketFrame(message));
        }
    }

    public void sendMessage(ChannelHandlerContext session, Object obj) {
        String message = gson.toJson(obj);

        if(session == null)
            return;

        session.writeAndFlush(new TextWebSocketFrame(message));
    }

    public void sendMessage(Object obj) {
        this.sendMessage(gson.toJson(obj));
    }

    private static Gson gson = new Gson();
    private static WebSocketSessionManager instance = new WebSocketSessionManager();

    public static WebSocketSessionManager getInstance() {
        return instance;
    }

}
