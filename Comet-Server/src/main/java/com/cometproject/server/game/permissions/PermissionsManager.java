package com.cometproject.server.game.permissions;

import com.cometproject.api.utilities.Initialisable;
import com.cometproject.server.game.permissions.types.*;
import com.cometproject.server.game.players.types.Player;
import com.cometproject.server.storage.queries.permissions.PermissionsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class PermissionsManager implements Initialisable {
    private static PermissionsManager permissionsManagerInstance;
    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionsManager.class.getName());
    private Map<Integer, Perk> perks;
    private Map<Integer, Rank> permissions;
    private Map<String, OverrideCommandPermission> overridecommands;
    private Map<Integer, Integer> effects;
    private Map<Integer, EffectPermission> overrideEffects;
    private Map<Integer, Integer> chatBubbles;

    public PermissionsManager() {

    }

    public static PermissionsManager getInstance() {
        if (permissionsManagerInstance == null)
            permissionsManagerInstance = new PermissionsManager();

        return permissionsManagerInstance;
    }

    @Override
    public void initialize() {
        this.perks = new ConcurrentHashMap<>();
        this.overridecommands = new ConcurrentHashMap<>();
        this.permissions = new ConcurrentHashMap<>();
        this.effects = new ConcurrentHashMap<>();
        this.overrideEffects = new ConcurrentHashMap<>();
        this.chatBubbles = new ConcurrentHashMap<>();

        this.loadPerks();
        this.loadPermissions();
        this.loadEffects();
        this.loadEffectsOverride();
        this.loadChatBubbles();

        LOGGER.info("PermissionsManager initialized");
    }

    public void loadPerks() {
        try {
            if (this.getPerks().size() != 0) {
                this.getPerks().clear();
            }

            this.perks = PermissionsDao.getPerks();

        } catch (Exception e) {
            LOGGER.error("Error while loading perk permissions", e);
            return;
        }

        LOGGER.info("Loaded " + this.getPerks().size() + " perks");
    }

    public void loadPermissions() {
        try {
            if (this.getPermissions().size() != 0) {
                this.getPermissions().clear();
            }

            this.permissions = PermissionsDao.getPermissions();
        } catch (Exception e) {
            LOGGER.error("Error while loading rank permissions", e);
            return;
        }

        LOGGER.info("Loaded " + this.getPermissions().size() + " ranks");
    }


    public void loadEffects() {
        try {
            if (this.getEffects().size() != 0) {
                this.getEffects().clear();
            }

            this.effects = PermissionsDao.getEffects();

        } catch (Exception e) {
            LOGGER.error("Error while reloading effect permissions", e);
            return;
        }

        LOGGER.info("Loaded " + this.getEffects().size() + " effect permissions");
    }

    public void loadEffectsOverride() {
        try {
            if (this.getEffectsOverride().size() != 0) {
                this.getEffectsOverride().clear();
            }

            this.overrideEffects = PermissionsDao.getOverrideEffects();

        } catch (Exception e) {
            LOGGER.error("Error while reloading effect override permissions", e);
            return;
        }

        LOGGER.info("Loaded " + this.getEffects().size() + " override effect permissions");
    }

    public void loadChatBubbles() {
        if (this.chatBubbles.size() != 0) {
            this.chatBubbles.clear();
        }

        this.chatBubbles = PermissionsDao.getChatBubbles();

        LOGGER.info("Loaded " + this.getEffects().size() + " chat bubbles");
    }


    public Rank getRank(final int playerRankId) {
        final Rank rank = this.permissions.get(playerRankId);

        if (rank == null) {
            LOGGER.warn("Failed to find rank by rank ID: " + playerRankId + ", are you sure it exists?");
            return this.permissions.get(1);
        }

        return rank;
    }

    public Map<Integer, Rank> getPermissions() {
        return this.permissions;
    }

    public Map<String, OverrideCommandPermission> getOverrideCommands() {
        return this.overridecommands;
    }

    public Map<Integer, Perk> getPerks() {
        return perks;
    }

    public Map<Integer, Integer> getEffects() {
        return effects;
    }

    public Map<Integer, EffectPermission> getEffectsOverride() {
        return overrideEffects;
    }

    public Map<Integer, Integer> getChatBubbles() {
        return this.chatBubbles;
    }

    public boolean rankExists(int rankId) {
        return this.permissions.containsKey(rankId);
    }

    public boolean hasPermission(Player player, String permission) {
        return this.hasPermission(player, permission, false);
    }

    public boolean hasPermission(Player player, String permission, boolean withRoomRights) {
        return this.hasPermission(player.getPermissions().getRank(), permission, withRoomRights);
    }

    public boolean hasPermission(Rank rank, String permission, boolean withRoomRights) {
        return rank.hasPermission(permission, withRoomRights);
    }
}