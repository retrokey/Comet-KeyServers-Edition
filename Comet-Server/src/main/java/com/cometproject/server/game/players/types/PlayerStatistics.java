package com.cometproject.server.game.players.types;

import com.cometproject.api.game.players.data.IPlayerStatistics;
import com.cometproject.server.storage.queries.player.PlayerDao;
import com.cometproject.server.storage.queries.player.messenger.MessengerDao;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PlayerStatistics implements IPlayerStatistics {
    private int playerId;

    private int experiencePoints;
    private int level;

    private final Player player;

    private int dailyRespects;
    private int respectPoints;
    private int dailyRolls;

    private int scratches;
    private int fireworks;

    private int helpTickets;
    private int abusiveHelpTickets;
    private int cautions;
    private int bans;
    private long tradeLock;

    public PlayerStatistics(ResultSet data, boolean isLogin, Player player) throws SQLException {
        if (isLogin) {
            this.playerId = data.getInt("playerId");
            this.experiencePoints = data.getInt("playerStats_experiencePoints");
            this.level = data.getInt("playerStats_level");
            this.dailyRespects = data.getInt("playerStats_dailyRespects");
            this.respectPoints = data.getInt("playerStats_totalRespectPoints");
            this.helpTickets = data.getInt("playerStats_helpTickets");
            this.abusiveHelpTickets = data.getInt("playerStats_helpTicketsAbusive");
            this.cautions = data.getInt("playerStats_cautions");
            this.bans = data.getInt("playerStats_bans");
            this.scratches = data.getInt("playerStats_scratches");
            this.tradeLock = data.getLong("playerStats_tradeLock");
            this.fireworks = data.getInt("playerStats_fireworks");
            this.dailyRolls = data.getInt("playerStats_dailyRolls");
        } else {
            this.playerId = data.getInt("player_id");
            this.experiencePoints = data.getInt("experience_points");
            this.level = data.getInt("level");
            this.dailyRespects = data.getInt("daily_respects");
            this.respectPoints = data.getInt("total_respect_points");
            this.helpTickets = data.getInt("help_tickets");
            this.abusiveHelpTickets = data.getInt("help_tickets_abusive");
            this.cautions = data.getInt("cautions");
            this.bans = data.getInt("bans");
            this.scratches = data.getInt("daily_scratches");
            this.tradeLock = data.getInt("trade_lock");
            this.fireworks = data.getInt("fireworks");
            this.dailyRolls = data.getInt("daily_rolls");
        }

        this.player = player;
    }

    public PlayerStatistics(int userId) {
        this.playerId = userId;
        this.experiencePoints = 0;
        this.level = 1;
        this.respectPoints = 0;
        this.dailyRespects = 3;
        this.scratches = 3;
        this.helpTickets = 0;
        this.abusiveHelpTickets = 0;
        this.cautions = 0;
        this.bans = 0;
        this.tradeLock = 0;
        this.fireworks = 0;
        this.dailyRolls = 5;
        this.player = null;
    }

    public void save() {
        PlayerDao.updatePlayerStatistics(this);
    }

    public void saveFireworks(){
        PlayerDao.updateFireworks(this.fireworks, this.playerId);
    }

    public void incrementExperiencePoints(int amount) {
        this.experiencePoints += amount;
        this.save();
    }

    public int getExperiencePoints() {
        return this.experiencePoints;
    }


    public int getLevel() {
        return this.level;
    }

    public int getFireworks(){
        return this.fireworks;
    }

    public void incrementFireworks(int amount) {
        this.fireworks += amount;
    }

    public void decrementFireworks(int amount) {
        this.fireworks -= amount;
    }

    public void incrementLevel() {
        this.level++;
        this.save();
    }

    public void incrementCautions(int amount) {
        this.cautions += amount;
        this.save();
    }

    public void incrementRespectPoints(int amount) {
        this.respectPoints += amount;
        this.save();
    }

    public void decrementDailyRespects(int amount) {
        this.dailyRespects -= amount;
        this.save();
    }

    public void decrementDailyRolls(int amount) {
        this.dailyRolls -= amount;
        PlayerDao.updateDailyRolls(this.dailyRolls, this.getPlayerId());
    }

    public void incrementBans(int amount) {
        this.bans += amount;
    }

    public void incrementAbusiveHelpTickets(int amount) {
        this.abusiveHelpTickets += amount;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public int getDailyRespects() {
        return this.dailyRespects;
    }

    @Override
    public int getDailyRolls() {
        return this.dailyRolls;
    }

    @Override
    public void setDailyRespects(int points) {
        this.dailyRespects = points;
    }

    @Override
    public void setDailyRolls(int amount) {
        this.dailyRolls = amount;
    }


    public int getRespectPoints() {
        return this.respectPoints;
    }

    public int getFriendCount() {
        return MessengerDao.getFriendCount(this.playerId);
    }

    public int getHelpTickets() {
        return helpTickets;
    }

    public void setHelpTickets(int helpTickets) {
        this.helpTickets = helpTickets;
    }

   @Override
   public boolean levelPass(){
        return this.level >= 2;
   }

    public int getAbusiveHelpTickets() {
        return abusiveHelpTickets;
    }

    public void setAbusiveHelpTickets(int abusiveHelpTickets) {
        this.abusiveHelpTickets = abusiveHelpTickets;
    }

    public int getCautions() {
        return cautions;
    }

    public void setCautions(int cautions) {
        this.cautions = cautions;
    }

    public int getBans() {
        return bans;
    }

    public void setBans(int bans) {
        this.bans = bans;
    }

    public void addBan() {
        this.bans = this.bans++;
    }

    @Override
    public int getScratches() {
        return scratches;
    }

    @Override
    public void setScratches(int scratches) {
        this.scratches = scratches;
    }

    public JsonElement toJson() {
        final JsonObject coreObject = new JsonObject();
        coreObject.addProperty("achievementPoints", experiencePoints);
        coreObject.addProperty("level", level);
        coreObject.addProperty("dailyRespects", dailyRespects);
        coreObject.addProperty("respectPoints", respectPoints);
        coreObject.addProperty("scratches", scratches);
        coreObject.addProperty("helpTickets", helpTickets);
        coreObject.addProperty("abusiveHelpTickets", abusiveHelpTickets);
        coreObject.addProperty("cautions", cautions);
        coreObject.addProperty("bans", bans);
        coreObject.addProperty("tradeLock", tradeLock);
        coreObject.addProperty("fireworks", fireworks);
        coreObject.addProperty("dailyRolls", dailyRolls);
        return coreObject;
    }

    public Player getPlayer() {
        return player;
    }

    public long getTradeLock() {
        return tradeLock;
    }

    public void setTradeLock(long tradeLock) {
        this.tradeLock = tradeLock;
    }
}
