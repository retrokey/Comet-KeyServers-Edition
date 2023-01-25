package com.cometproject.server.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class Logging {

    private static final Logger LOGGER = LoggerFactory.getLogger("LegacyLogger");

    /**
     * @deprecated Do not use. Please use LoggerFactory.getLogger(YourClass.class) to log.
     */
    @Deprecated
    public void logStart(Object line) {
        LOGGER.info("[LOADING] {}", line);
    }

    /**
     * @deprecated Do not use. Please use LoggerFactory.getLogger(YourClass.class) to log.
     */
    @Deprecated
    public void logShutdownLine(Object line) {
        LOGGER.info("[SHUTDOWN] {}", line);
    }

    /**
     * @deprecated Do not use. Please use LoggerFactory.getLogger(YourClass.class) to log.
     */
    @Deprecated
    public void logUserLine(Object line) {
        LOGGER.info("[USER] {}", line);
    }

    /**
     * @deprecated Do not use. Please use LoggerFactory.getLogger(YourClass.class) to log.
     */
    @Deprecated
    public void logDebugLine(Object line) {
        LOGGER.debug("[DEBUG] {}", line);
    }

    /**
     * @deprecated Do not use. Please use LoggerFactory.getLogger(YourClass.class) to log.
     */
    @Deprecated
    public void logErrorLine(Object line) {
        LOGGER.error("[ERROR] {}", line);
    }

    /**
     * @deprecated Do not use. Please use LoggerFactory.getLogger(YourClass.class) to log.
     */
    @Deprecated
    public void logSQLException(SQLException e) {
        LOGGER.error("[ERROR] SQLException", e);
    }

    /**
     * @deprecated Do not use. Please use LoggerFactory.getLogger(YourClass.class) to log.
     */
    @Deprecated
    public void logPacketError(Object e) {
        LOGGER.error("[ERROR] PacketError {}", e);
    }

    /**
     * @deprecated Do not use. Please use LoggerFactory.getLogger(YourClass.class) to log.
     */
    @Deprecated
    public void handleException(Exception e) {
        LOGGER.error("[ERROR] Exception", e);
    }

}
