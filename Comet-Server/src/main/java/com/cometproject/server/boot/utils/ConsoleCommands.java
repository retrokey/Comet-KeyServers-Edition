package com.cometproject.server.boot.utils;

import com.cometproject.api.config.CometSettings;
import com.cometproject.api.stats.CometStats;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.catalog.CatalogManager;
import com.cometproject.server.game.moderation.BanManager;
import com.cometproject.server.game.navigator.NavigatorManager;
import com.cometproject.server.game.permissions.PermissionsManager;
import com.cometproject.server.modules.ModuleManager;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.storage.SqlHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class ConsoleCommands {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleCommands.class);

    public static void init() {
        // Console commands
        final Thread cmdThr = new Thread() {
            public void run() {
                while (Comet.isRunning) {
                    if (!Comet.isRunning) {
                        break;
                    }

                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                        String input = br.readLine();

                        if (input != null) {
                            handleCommand(input);
                        }
                    } catch (Exception e) {
                        LOGGER.error("Error while parsing console command");
                    }
                }
            }
        };

        cmdThr.start();
    }

    public static void handleCommand(String line) {
        if (line.startsWith("/")) {
            switch (line.split(" ")[0]) {
                default:
                    LOGGER.error("Invalid command");
                    break;

                case "/query-log":
                    SqlHelper.queryLogEnabled = !SqlHelper.queryLogEnabled;
                    break;

                case "/":
                case "/help":
                case "/commands":
                    LOGGER.info("Commands available: /about, /reload_messages, /gc, /reload_permissions, /changemotd, /reload_catalog, /reload_bans, /reload_locale, /reload_permissions, /queries, /queries");
                    break;

                case "/reload_modules":
                    ModuleManager.getInstance().initialize();
                    LOGGER.info("Modules reloaded successfully.");
                    break;

                case "/about":
                    final CometStats stats = Comet.getStats();

                    LOGGER.info("This server is powered by Comet (" + Comet.getBuild() + ")");
                    LOGGER.info("    Players online: " + stats.getPlayers());
                    LOGGER.info("    Active rooms: " + stats.getRooms());
                    LOGGER.info("    Uptime: " + stats.getUptime());
                    LOGGER.info("    Process ID: " + stats.getProcessId());
                    LOGGER.info("    Memory allocated: " + stats.getAllocatedMemory() + "MB");
                    LOGGER.info("    Memory usage: " + stats.getUsedMemory() + "MB");
                    break;

                case "/reload_messages":
                    NetworkManager.getInstance().getMessages().load();
                    LOGGER.info("Message handlers were reloaded");
                    break;

                case "/gc":
                    System.gc();
                    LOGGER.info("GC was run");
                    break;

                case "/changemotd":
                    String motd = line.replace("/changemotd ", "");
                    CometSettings.setMotd(motd);
                    LOGGER.info("Message of the day was set.");
                    break;

                case "/reload_permissions":
                    PermissionsManager.getInstance().loadPerks();
                    PermissionsManager.getInstance().loadPermissions();
                    LOGGER.info("Permissions cache was reloaded.");
                    break;

                case "/reload_catalog":
                    CatalogManager.getInstance().loadItemsAndPages();
                    CatalogManager.getInstance().loadGiftBoxes();
                    LOGGER.info("Catalog cache was reloaded.");
                    break;

                case "/reload_bans":
                    BanManager.getInstance().loadBans();
                    LOGGER.info("Bans were reloaded.");
                    break;

                case "/reload_navigator":
                    NavigatorManager.getInstance().loadPublicRooms();
                    NavigatorManager.getInstance().loadCategories();
                    LOGGER.info("Navigator was reloaded.");
                    break;

                case "/reload_locale":
                    Locale.initialize();
                    LOGGER.info("Locale configuration was reloaded.");
                    break;

                case "/queries":

                    LOGGER.info("Queries");
                    LOGGER.info("================================================");

                    for (Map.Entry<String, AtomicInteger> query : SqlHelper.getQueryCounters().entrySet()) {
                        LOGGER.info("Query:" + query.getKey());
                        LOGGER.info("Count: " + query.getValue().get());
                        LOGGER.info("");
                    }

                    break;

                case "/clear_queries":
                    SqlHelper.getQueryCounters().clear();
                    LOGGER.info("Query counters have been cleared.");
                    break;
            }
        } else {
            LOGGER.error("Invalid command");
        }
    }
}
