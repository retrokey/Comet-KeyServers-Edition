package com.cometproject.server.network;

import com.cometproject.networking.api.INetworkingServer;
import com.cometproject.networking.api.config.NetworkingServerConfig;
import com.cometproject.networking.api.sessions.INetSessionFactory;
import io.netty.bootstrap.ServerBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class NettyNetworkingServer implements INetworkingServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyNetworkingServer.class.getName());

    private final NetworkingServerConfig serverConfig;
    private final INetSessionFactory sessionFactory;
    private final ServerBootstrap serverBootstrap;

    public NettyNetworkingServer(NetworkingServerConfig serverConfig, INetSessionFactory sessionFactory, ServerBootstrap serverBootstrap) {
        this.serverConfig = serverConfig;
        this.sessionFactory = sessionFactory;
        this.serverBootstrap = serverBootstrap;
    }

    @Override
    public void start() {
        for (short port : this.serverConfig.getPorts()) {
            try {
                this.serverBootstrap.bind(new InetSocketAddress(this.serverConfig.getHost(), port)).addListener(objectFuture -> {
                    if (!objectFuture.isSuccess()) {
                        LOGGER.error("Failed to start sockets on " + this.serverConfig.getHost() + ", port: " + port);
                    }
                });

                LOGGER.info("CometServer listening on port: " + port);
            } catch (Exception e) {
                LOGGER.error("Failed to start sockets on " + this.serverConfig.getHost() + ", port: " + port, e);
            }
        }
    }

    @Override
    public NetworkingServerConfig getServerConfig() {
        return serverConfig;
    }

    @Override
    public INetSessionFactory getSessionFactory() {
        return this.sessionFactory;
    }
}
