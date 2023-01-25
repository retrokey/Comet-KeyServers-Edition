package com.cometproject.server.game.players.components.types.achievements;

import com.cometproject.api.game.players.data.components.achievements.IAchievementProgress;

public class AchievementProgress implements IAchievementProgress {
    private int level;
    private int progress;

    public AchievementProgress(int level, int progress) {
        this.level = level;
        this.progress = progress;
    }

    public void increaseProgress(int amount) {
        this.progress += amount;
    }

    public void decreaseProgress(int difference) {
        this.progress -= difference;
    }

    public boolean increaseLevel(int amount) {
        if(this.level == amount)
            return true;

        this.level += 1;
        return false;
    }

    public int getLevel() {
        return this.level;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
