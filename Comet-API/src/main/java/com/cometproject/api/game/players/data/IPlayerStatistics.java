package com.cometproject.api.game.players.data;

public interface IPlayerStatistics {
    void save();
    void saveFireworks();
    void incrementExperiencePoints(int amount);
    void incrementLevel();
    void incrementCautions(int amount);
    void incrementFireworks(int amount);
    void decrementFireworks(int amount);
    int getFireworks();
    void incrementRespectPoints(int amount);
    void decrementDailyRespects(int amount);
    void incrementBans(int amount);
    void incrementAbusiveHelpTickets(int amount);
    int getPlayerId();
    int getDailyRespects();
    int getRespectPoints();
    int getExperiencePoints();
    int getLevel();
    boolean levelPass();
    int getFriendCount();
    int getHelpTickets();
    void setHelpTickets(int helpTickets);
    int getAbusiveHelpTickets();
    void setAbusiveHelpTickets(int abusiveHelpTickets);
    int getCautions();
    void setCautions(int cautions);
    int getBans();
    void setBans(int bans);
    void addBan();
    void setDailyRespects(int points);
    void setScratches(int scratches);
    int getScratches();
    void setDailyRolls(int amount);
    int getDailyRolls();
}
