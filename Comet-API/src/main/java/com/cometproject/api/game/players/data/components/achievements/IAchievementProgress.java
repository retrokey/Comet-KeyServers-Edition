package com.cometproject.api.game.players.data.components.achievements;

public interface IAchievementProgress {
    void increaseProgress(int amount);

    void decreaseProgress(int amount);

    boolean increaseLevel(int amount);

    int getLevel();

    int getProgress();

    void setProgress(int progress);
}
