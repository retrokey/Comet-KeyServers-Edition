package com.cometproject.server.api;

import com.cometproject.api.config.Configuration;
import com.cometproject.api.utilities.Initialisable;
import com.cometproject.server.api.routes.PhotoRoutes;
import com.cometproject.server.api.routes.PlayerRoutes;
import com.cometproject.server.api.routes.RoomRoutes;
import com.cometproject.server.api.routes.SystemRoutes;
import com.cometproject.server.api.transformers.JsonTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;


public class APIManager implements Initialisable {
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(APIManager.class.getName());
    /**
     * Create an array of config properties that are required before enabling the API
     * If none of these properties exist, the API will be automatically disabled
     */
    private static final String[] configProperties = new String[]{
            "comet.api.enabled",
            "comet.api.port",
            "comet.api.token"
    };
    /**
     * The global API Manager instance
     */
    private static APIManager apiManagerInstance;
    /**
     * Is the API enabled?
     */
    private boolean enabled;

    /**
     * The port the API server will listen on
     */
    private int port;

    /**
     * The token used for authentication
     */
    private String authToken;


    /**
     * The transformer to convert objects into JSON formatted strings
     */
    private JsonTransformer jsonTransformer;

    /**
     * Construct the API manager
     */
    public APIManager() {

    }

    public static APIManager getInstance() {
        if (apiManagerInstance == null)
            apiManagerInstance = new APIManager();

        return apiManagerInstance;
    }

    /**
     * Initialize the API
     */
    @Override
    public void initialize() {
        this.initializeConfiguration();
        this.initializeSpark();
        this.initializeRouting();
    }

    /**
     * Initialize the configuration
     */
    private void initializeConfiguration() {
        for (String configProperty : configProperties) {
            if (!Configuration.currentConfig().containsKey(configProperty)) {
                LOGGER.warn("API configuration property not available: " + configProperty + ", API is disabled");
                this.enabled = false;

                return;
            }
        }

        this.enabled = Configuration.currentConfig().getProperty("comet.api.enabled").equals("true");
        this.port = Integer.parseInt(Configuration.currentConfig().getProperty("comet.api.port"));
        this.authToken = Configuration.currentConfig().getProperty("comet.api.token");
    }

    /**
     * Initialize the Spark web framework
     */
    private void initializeSpark() {
        if (!this.enabled)
            return;

        Spark.setPort(this.port);

        this.jsonTransformer = new JsonTransformer();
    }

    /**
     * Initialize the API routing
     */
    private void initializeRouting() {
        if (!this.enabled)
            return;

        Spark.before((request, response) -> {
            boolean authenticated = request.headers("authToken") != null && request.headers("authToken").equals(this.authToken);

            if (!authenticated) {
                LOGGER.error("Unauthenticated request from: " + request.ip() + "; " + request.contextPath());
                response.type("application/json");
                Spark.halt("{\"error\":\"Invalid authentication token\"}");
            }
        });

        Spark.get("/", (request, response) -> {
            Spark.halt(404);
            return "Invalid request, if you believe you received this in error, please contact the server administrator!";
        });

        Spark.get("/player/:id/reload", PlayerRoutes::reloadPlayerData, jsonTransformer);
        Spark.get("/player/:id/disconnect", PlayerRoutes::disconnect, jsonTransformer);
        Spark.post("/player/:id/alert", PlayerRoutes::alert, jsonTransformer);
        Spark.get("/player/:id/badge/:badge", PlayerRoutes::giveBadge, jsonTransformer);

        Spark.get("/rooms/active/all", RoomRoutes::getAllActiveRooms, jsonTransformer);
        Spark.get("/room/:id/:action", RoomRoutes::roomAction, jsonTransformer);

        Spark.get("/system/status", SystemRoutes::status, jsonTransformer);
        Spark.get("/system/shutdown", SystemRoutes::shutdown, jsonTransformer);
        Spark.get("/system/reload/:type", SystemRoutes::reload, jsonTransformer);
        Spark.post("/camera/purchase", PhotoRoutes::purchase, jsonTransformer);
        Spark.get("/camera/purchase", PhotoRoutes::purchase, jsonTransformer);
    }
}
