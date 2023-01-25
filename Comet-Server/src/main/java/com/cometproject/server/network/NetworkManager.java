package com.cometproject.server.network;

import com.cometproject.api.config.CometSettings;
import com.cometproject.api.config.Configuration;
import com.cometproject.api.messaging.console.ConsoleCommandRequest;
import com.cometproject.api.messaging.exec.ExecCommandRequest;
import com.cometproject.api.messaging.exec.ExecCommandResponse;
import com.cometproject.api.messaging.status.StatusRequest;
import com.cometproject.api.messaging.status.StatusResponse;
import com.cometproject.networking.api.INetworkingServer;
import com.cometproject.networking.api.INetworkingServerFactory;
import com.cometproject.networking.api.NetworkingContext;
import com.cometproject.networking.api.config.NetworkingServerConfig;
import com.cometproject.networking.api.sessions.INetSessionFactory;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.boot.utils.ConsoleCommands;
import com.cometproject.server.network.messages.GameMessageHandler;
import com.cometproject.server.network.messages.MessageHandler;
import com.cometproject.server.network.monitor.MonitorClient;
import com.cometproject.server.network.sessions.SessionManager;
import com.cometproject.server.network.sessions.net.NetSessionFactory;
import com.google.common.collect.Sets;
import io.coerce.commons.config.CoerceConfiguration;
import io.coerce.services.messaging.client.MessagingClient;
import io.netty.util.ResourceLeakDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Set;


public class NetworkManager {
    public static boolean IDLE_TIMER_ENABLED = Boolean.parseBoolean(Configuration.currentConfig().get("comet.network.idleTimer.enabled", "true"));
    public static int IDLE_TIMER_READER_TIME = Integer.parseInt(Configuration.currentConfig().get("comet.network.idleTimer.readerIdleTime", "60"));
    public static int IDLE_TIMER_WRITER_TIME = Integer.parseInt(Configuration.currentConfig().get("comet.network.idleTimer.writerIdleTime", "0"));
    public static int IDLE_TIMER_ALL_TIME = Integer.parseInt(Configuration.currentConfig().get("comet.network.idleTimer.allIdleTime", "0"));
    private static NetworkManager networkManagerInstance;
    private static Logger LOGGER = LoggerFactory.getLogger(NetworkManager.class.getName());
    private int serverPort;
    private SessionManager sessions;
    private MessageHandler messageHandler;
    private MonitorClient monitorClient;
    private MessagingClient messagingClient;

    public NetworkManager() {

    }

    public static NetworkManager getInstance() {
        if (networkManagerInstance == null)
            networkManagerInstance = new NetworkManager();

        return networkManagerInstance;
    }

    public void initialize(String ip, String ports) {
        this.sessions = new SessionManager();
        this.messageHandler = new MessageHandler();

        this.serverPort = Integer.parseInt(ports.split(",")[0]);

        try {
            this.messagingClient = MessagingClient.create("com.cometproject:instance/" + Comet.instanceId + "/" +
                            "" + CometSettings.hotelName.replace(" ", "-").toLowerCase(),
                    new CoerceConfiguration("configuration/Coerce.json"));

            this.messagingClient.observe(ConsoleCommandRequest.class, (consoleCommandRequest -> {
                ConsoleCommands.handleCommand(consoleCommandRequest.getCommand());
            }));

            this.messagingClient.observe(ExecCommandRequest.class, (execRequest -> {
                final String command = execRequest.getCommand();

                try {
                    Process process = Runtime.getRuntime().exec(command);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(
                            process.getInputStream()));

                    StringBuilder commandOutput = new StringBuilder();
                    String buffer;

                    while ((buffer = reader.readLine()) != null) {
                        commandOutput.append(buffer).append("\n");
                    }

                    this.messagingClient.sendResponse(execRequest.getMessageId(), execRequest.getSender(),
                            new ExecCommandResponse(commandOutput.toString()));
                } catch (IOException e) {
                    this.messagingClient.sendResponse(execRequest.getMessageId(), execRequest.getSender(),
                            new ExecCommandResponse("Exception: " + e));
                }
            }));

            this.messagingClient.observe(StatusRequest.class, (statusRequest -> {
                messagingClient.sendResponse(statusRequest.getMessageId(), statusRequest.getSender(),
                        new StatusResponse(Comet.getStats(), Comet.getBuild()));
            }));

            final InetAddress address = InetAddress.getLocalHost();

            this.messagingClient.connect(address.getHostAddress(), 6500, (client) -> {
            });
        } catch (Exception e) {
            System.out.println("Failed to initialise NetworkManager\n" + e.toString() + "\n --------");
            System.exit(0);
            return;
        }

        //InternalLoggerFactory.setDefaultFactory(new Log4JLoggerFactory());

        System.setProperty("io.netty.leakDetectionLevel", "disabled");
        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.DISABLED);

        final INetSessionFactory sessionFactory = new NetSessionFactory(this.sessions, new GameMessageHandler());
        final INetworkingServerFactory serverFactory = new NettyNetworkingServerFactory(Configuration.currentConfig());
        final NetworkingContext networkingContext = new NetworkingContext(serverFactory);

        NetworkingContext.setCurrentContext(networkingContext);

        final Set<Short> portSet = Sets.newHashSet();

        if (ports.contains(",")) {
            for (String port : ports.split(",")) {
                portSet.add(Short.parseShort(port));
            }
        } else {
            portSet.add(Short.parseShort(ports));
        }

        final INetworkingServer gameServer = serverFactory.createServer(new NetworkingServerConfig(ip, portSet),
                sessionFactory);

        gameServer.start();
    }

    public SessionManager getSessions() {
        return this.sessions;
    }

    public MessageHandler getMessages() {
        return this.messageHandler;
    }

    public MonitorClient getMonitorClient() {
        return monitorClient;
    }

    public int getServerPort() {
        return serverPort;
    }

    public MessagingClient getMessagingClient() {
        return this.messagingClient;
    }
}
