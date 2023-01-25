package com.cometproject.server.game.achievements.types;

import com.cometproject.api.game.achievements.types.IAchievement;

public class Achievement implements IAchievement {
    private final int level;
    private final int rewardActivityPoints;
    private final int rewardAchievement;
    private final int rewardType;
    private final int progressNeeded;

    public Achievement(int level, int rewardActivityPoints, int rewardAchievement, int rewardType, int progressNeeded) {
        this.level = level;
        this.rewardActivityPoints = rewardActivityPoints;
        this.rewardAchievement = rewardAchievement;
        this.rewardType = rewardType;
        this.progressNeeded = progressNeeded;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getRewardActivityPoints() {
        return rewardActivityPoints;
    }

    @Override
    public int getRewardAchievement() {
        return rewardAchievement;
    }

    @Override
    public int getRewardType() {
        return rewardType;
    }

    @Override
    public int getProgressNeeded() {
        return progressNeeded;
    }
}
