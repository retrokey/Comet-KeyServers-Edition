package com.cometproject.server.protocol.codec.websockets;

import com.cometproject.api.config.CometSettings;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;

import java.net.URI;
import java.net.URISyntaxException;

public class HttpCustomHandler extends ChannelInboundHandlerAdapter {
    private static final String ORIGIN_HEADER = "Origin";
    private static final String FORWARD_PROXY_REALIP = "X-Forwarded-For";
    public static final AttributeKey<String> WS_IP = AttributeKey.valueOf("WS_IP");

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof HttpMessage) {
            if(!handleHttpRequest(ctx, (HttpMessage) msg))
            {
                ReferenceCountUtil.release(msg);//discard message
                return;
            }
        }
        super.channelRead(ctx, msg);
        ctx.pipeline().remove(this);
    }

    public boolean handleHttpRequest(ChannelHandlerContext ctx, HttpMessage req) {
        String origin = "error";

        try {
            if(req.headers().contains(ORIGIN_HEADER)) {
                origin = getDomainName(req.headers().get(ORIGIN_HEADER));
            }
        } catch (Exception ignored) { }

        if(!isWhitelisted(origin, CometSettings.websocketOriginWhitelist)) {
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.FORBIDDEN, Unpooled.wrappedBuffer("Origin forbidden".getBytes()));
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
            return false;
        }

        if(req.headers().contains(FORWARD_PROXY_REALIP)) {
            String ip = req.headers().get(FORWARD_PROXY_REALIP);
            ctx.channel().attr(WS_IP).set(ip);
        }
        return true;
    }

    public static String getDomainName(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

    public static boolean isWhitelisted(String origin, String[] allowedOrigins) {
        for(String entry : allowedOrigins) {
            if(entry.startsWith("*")) {
                if(origin.endsWith(entry.substring(1)) || ("." + origin).equals(entry.substring(1))) return true;
            } else {
                if(origin.equals(entry)) return true;
            }
        }
        return false;
    }
}