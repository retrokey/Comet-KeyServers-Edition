package com.cometproject.server.game.moderation;

import com.cometproject.api.utilities.Initialisable;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.game.moderation.types.Ban;
import com.cometproject.server.game.moderation.types.BanType;
import com.cometproject.server.storage.queries.moderation.BanDao;
import com.corundumstudio.socketio.misc.ConcurrentHashSet;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class BanManager implements Initialisable {
    public static BanManager banManagerInstance;
    Logger LOGGER = LoggerFactory.getLogger(BanManager.class.getName());
    private Map<String, Ban> bans;
    private Set<Integer> mutedPlayers;

    public BanManager() {

    }

    public static BanManager getInstance() {
        if (banManagerInstance == null)
            banManagerInstance = new BanManager();

        return banManagerInstance;
    }

    @Override
    public void initialize() {
        this.mutedPlayers = new ConcurrentHashSet<>();

        loadBans();
        LOGGER.info("BanManager initialized");
    }

    public void loadBans() {
        if (this.bans != null)
            this.bans.clear();

        try {
            this.bans = BanDao.getActiveBans();
            LOGGER.info("Loaded " + this.bans.size() + " bans");
        } catch (Exception e) {
            LOGGER.error("Error while loading bans", e);
        }
    }

    public void processBans() {
        List<Ban> bansToRemove = Lists.newArrayList();

        for (Ban ban : this.bans.values()) {
            if (ban.getExpire() != 0 && Comet.getTime() >= ban.getExpire()) {
                bansToRemove.add(ban);
            }
        }

        if (bansToRemove.size() != 0) {
            for (Ban ban : bansToRemove) {
                this.bans.remove(ban.getData());
            }
        }

        bansToRemove.clear();
    }

    public void banPlayer(BanType type, String data, int length, long expire, String reason, int bannerId) {
        int banId = BanDao.createBan(type, length, expire, data, bannerId, reason);
        this.add(new Ban(banId, data, length == 0 ? length : expire, type, reason));
    }

    private void add(Ban ban) {
        this.bans.put(ban.getData(), ban);
    }

    public boolean hasBan(String data, BanType type) {
        if (this.bans.containsKey(data)) {
            Ban ban = this.bans.get(data);

            if (ban != null && ban.getType() == type) {
                return ban.getExpire() == 0 || Comet.getTime() < ban.getExpire();
            }
        }

        return false;
    }

    public boolean unBan(String data) {
        if(!data.equals("0")) {
            if (this.bans.containsKey(data)) {
                this.bans.remove(data);
                removeBan(data);

                initialize();
                return true;
            } else return false;
        } else return false;
    }

    private void removeBan(String data) {
        BanDao.deleteBan(data);
    }

    public Ban get(String data) {
        return this.bans.get(data);
    }

    public boolean isMuted(int playerId) {
        return this.mutedPlayers.contains(playerId);
    }

    public void mute(int playerId) {
        this.mutedPlayers.add(playerId);
    }

    public void unmute(int playerId) {
        this.mutedPlayers.remove(playerId);
    }
}
