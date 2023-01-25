package com.cometproject.server.boot.utils;

import com.cometproject.api.game.GameContext;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.logging.LogManager;
import com.cometproject.server.logging.database.queries.LogQueries;
import com.cometproject.server.storage.StorageManager;
import com.cometproject.server.storage.queries.system.StatisticsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ShutdownProcess {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShutdownProcess.class);

    public static void init() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                shutdown(false);
            }
        });
    }

    public static void shutdown(boolean exit) {
        LOGGER.info("Comet is now shutting down");

        Comet.isRunning = false;

        LOGGER.info("Resetting statistics");
        StatisticsDao.saveStatistics(0, 0, Comet.getBuild());

        if (LogManager.ENABLED) {
            LOGGER.info("Updating room entry data");
            LogQueries.updateRoomEntries();
        }

        LOGGER.info("Closing all database connections");

        GameContext.setCurrent(null);
        StorageManager.getInstance().shutdown();

        if (exit) {
            System.exit(0);
        }
    }
}
