package com.cometproject.server.boot;

import com.cometproject.api.config.Configuration;
import com.cometproject.api.game.GameContext;
import com.cometproject.games.snowwar.thread.WorkerTasks;
import com.cometproject.server.api.APIManager;
import com.cometproject.server.boot.utils.gui.CometGui;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.GameCycle;
import com.cometproject.server.game.achievements.AchievementManager;
import com.cometproject.server.game.catalog.CatalogManager;
import com.cometproject.server.game.commands.CommandManager;
import com.cometproject.server.game.gamecenter.GameCenterManager;
import com.cometproject.server.game.groups.items.GroupItemManager;
import com.cometproject.server.game.guides.GuideManager;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.landing.LandingManager;
import com.cometproject.server.game.moderation.BanManager;
import com.cometproject.server.game.moderation.ModerationManager;
import com.cometproject.server.game.navigator.NavigatorManager;
import com.cometproject.server.game.permissions.PermissionsManager;
import com.cometproject.server.game.pets.PetManager;
import com.cometproject.server.game.players.PlayerManager;
import com.cometproject.server.game.polls.PollManager;
import com.cometproject.server.game.quests.QuestManager;
import com.cometproject.server.game.rooms.RoomManager;
import com.cometproject.server.game.rooms.bundles.RoomBundleManager;
import com.cometproject.server.game.utilities.validator.PlayerFigureValidator;
import com.cometproject.server.logging.LogManager;
import com.cometproject.server.modules.ModuleManager;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.rcon.RconServer;
import com.cometproject.server.storage.StorageManager;
import com.cometproject.server.storage.queries.config.ConfigDao;
import com.cometproject.server.storage.queries.rooms.RoomDao;
import com.cometproject.server.tasks.CometThreadManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class CometServer {
    public static final String CLIENT_VERSION = "PRODUCTION-201709192204-203982672";
    private final Logger LOGGER = LoggerFactory.getLogger(CometServer.class);

    public CometServer(Map<String, String> overridenConfig) {
        Configuration.setConfiguration(new Configuration("./config/comet.properties"));

        if (overridenConfig != null) {
            Configuration.currentConfig().override(overridenConfig);
        }
    }

    /**
     * Initialize Comet Server
     */
    public void init() {
        ModuleManager.getInstance().initialize();
        APIManager.getInstance().initialize();
        //WebSocketManager.getInstance().initialize();
        PlayerFigureValidator.loadFigureData();

        CometThreadManager.getInstance().initialize();
        StorageManager.getInstance().initialize();
        LogManager.getInstance().initialize();

        // Locale & config
        ConfigDao.getAll();
        ConfigDao.getSurvivalSettings();

        Locale.initialize();
        PermissionsManager.getInstance().initialize();
        RoomBundleManager.getInstance().initialize();
        ItemManager.getInstance().initialize();
        CatalogManager.getInstance().initialize();
        RoomManager.getInstance().initialize();
        NavigatorManager.getInstance().initialize();
        CommandManager.getInstance().initialize();
        BanManager.getInstance().initialize();
        ModerationManager.getInstance().initialize();
        PetManager.getInstance().initialize();
        LandingManager.getInstance().initialize();
        PlayerManager.getInstance().initialize();
        QuestManager.getInstance().initialize();
        AchievementManager.getInstance().initialize();
        PollManager.getInstance().initialize();
        GuideManager.getInstance().initialize();
        GameCenterManager.getInstance().initialize();
        WorkerTasks.initWorkers();

        GameContext gameContext = new GameContext();

        gameContext.setCatalogService(CatalogManager.getInstance());
        gameContext.setFurnitureService(ItemManager.getInstance());
        gameContext.setPlayerService(PlayerManager.getInstance());

        GameContext.setCurrent(gameContext);

        String ipAddress = this.getConfig().get("comet.network.host"),
                port = this.getConfig().get("comet.network.port");

        NetworkManager.getInstance().initialize(ipAddress, port);


        ModuleManager.getInstance().setupModules();

        RoomDao.getEmojis();

        GameContext.getCurrent().getGroupService().setItemService(new GroupItemManager());

        GameCycle.getInstance().initialize();

        new Thread() {
            public void run() {
                System.out.println("Starting RCON TCP server...");
                RconServer sv = new RconServer();
                sv.run();
            }
        }.start();

        if (Comet.showGui) {
            CometGui gui = new CometGui();
            gui.setVisible(true);
        }
    }

    /**
     * Get the Comet configuration
     *
     * @return Comet configuration
     */
    public Configuration getConfig() {
        return Configuration.currentConfig();
    }

    public Logger getLogger() {
        return LOGGER;
    }
}
