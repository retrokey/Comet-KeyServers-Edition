package com.cometproject.server.network.websockets;

import com.cometproject.api.utilities.Initialisable;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class WebSocketManager implements Initialisable {

    @Override
    public void initialize() {
/*
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(new HttpServerCodec())
                                    .addLast(new WebSocketHandler());
                        }

                    })
                    .option(ChannelOption.SO_BACKLOG, 1024);

            serverBootstrap.bind(2087);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
*/
    }

    private static WebSocketManager instance;

    public static WebSocketManager getInstance() {
        if (instance == null) instance = new WebSocketManager();

        return instance;
    }

}
