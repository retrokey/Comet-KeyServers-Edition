package com.cometproject.server.boot;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import com.cometproject.api.stats.CometStats;
import com.cometproject.server.boot.utils.ConsoleCommands;
import com.cometproject.server.boot.utils.ShutdownProcess;
import com.cometproject.server.game.rooms.RoomManager;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.utilities.CometRuntime;
import com.cometproject.server.utilities.TimeSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;


public class Comet {
    public static String instanceId = UUID.randomUUID().toString();
    /**
     * The time the server was started
     */
    public static long start;
    /**
     * Is a debugger attached?
     */
    public static volatile boolean isDebugging = false;
    /**
     * Is Comet running?
     */
    public static volatile boolean isRunning = true;
    /**
     * Whether or not we want to show the GUI
     */
    public static boolean showGui = false;
    /**
     * Whether we're running Comet in daemon mode or not
     */
    public static boolean daemon = false;
    /**
     * Logging during start-up & console commands
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Comet.class);
    /**
     * The main server instance
     */
    private static CometServer server;

    private static final String OS_NAME = System.getProperty("os.name");
    private static final String CLASS_PATH = System.getProperty("java.class.path");

    /**
     * Start the server!
     *
     * @param args The arguments passed from the run command
     */
    public static void run(String[] args) {
        start = System.currentTimeMillis();

        // Check if running on Windows and not in IntelliJ.
        // If so, we need to reconfigure the console appender and enable Jansi for colors.
        if (OS_NAME.startsWith("Windows") && !CLASS_PATH.contains("idea_rt.jar")) {
            ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
            ConsoleAppender<ILoggingEvent> appender = (ConsoleAppender<ILoggingEvent>) root.getAppender("Console");

            appender.stop();
            appender.setWithJansi(true);
            appender.start();
        }


        LOGGER.info("Comet Server - " + getBuild());

        if (isDebugging) {
            ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
            root.setLevel(Level.DEBUG);
            LOGGER.debug("Debugging enabled.");
        }


        server = new CometServer(null);
        server.init();

        if (!daemon) {
            ConsoleCommands.init();
        }

        ShutdownProcess.init();
    }

    /**
     * Exit the comet server
     *
     * @param message The message to display to the console
     */
    public static void exit(String message) {
        LOGGER.error("Comet has shutdown. Reason: \"" + message + "\"");
        System.exit(0);
    }

    /**
     * Get the instance time in seconds
     *
     * @return The time in seconds
     */
    public static long getTime() {
        return (System.currentTimeMillis() / 1000L);
    }

    /**
     * Get the instance date [HH:MM:SS]
     *
     * @return The date
     */
    public static String getDate() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    public static Random getRandom() {
        return new Random();
    }

    /**
     * Get the instance build of Comet
     *
     * @return The instance build of Comet
     */
    public static String getBuild() {
        return Comet.class.getPackage().getImplementationVersion() == null ? "Comet-DEV" : Comet.class.getPackage().getImplementationVersion();
    }

    /**
     * Gets the instance server stats
     *
     * @return Server stats object
     */
    public static CometStats getStats() {
        CometStats statsInstance = new CometStats();

        statsInstance.setPlayers(NetworkManager.getInstance().getSessions().getUsersOnlineCount());
        statsInstance.setRooms(RoomManager.getInstance().getRoomInstances().size());
        statsInstance.setUptime(TimeSpan.millisecondsToDate(System.currentTimeMillis() - Comet.start));

        statsInstance.setProcessId(CometRuntime.processId);
        statsInstance.setAllocatedMemory((Runtime.getRuntime().totalMemory() / 1024) / 1024);
        statsInstance.setUsedMemory(statsInstance.getAllocatedMemory() - (Runtime.getRuntime().freeMemory() / 1024) / 1024);
        statsInstance.setOperatingSystem(CometRuntime.operatingSystem + " (" + CometRuntime.operatingSystemArchitecture + ")");
        statsInstance.setCpuCores(Runtime.getRuntime().availableProcessors());

        return statsInstance;
    }

    /**
     * Get the main server instance
     *
     * @return The main server instance
     */
    public static CometServer getServer() {
        return server;
    }
}
