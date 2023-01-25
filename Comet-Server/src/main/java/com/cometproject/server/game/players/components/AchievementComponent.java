package com.cometproject.server.game.players.components;

import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.api.game.achievements.types.IAchievement;
import com.cometproject.api.game.achievements.types.IAchievementGroup;
import com.cometproject.api.game.players.IPlayer;
import com.cometproject.api.game.players.data.components.PlayerAchievements;
import com.cometproject.api.game.players.data.components.achievements.IAchievementProgress;
import com.cometproject.server.game.achievements.AchievementManager;
import com.cometproject.server.game.players.components.types.achievements.AchievementProgress;
import com.cometproject.server.game.players.types.PlayerComponent;
import com.cometproject.server.network.messages.outgoing.messenger.FriendNotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.achievements.AchievementPointsMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.achievements.AchievementProgressMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.achievements.AchievementUnlockedMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.purse.UpdateActivityPointsMessageComposer;
import com.cometproject.server.storage.queries.achievements.PlayerAchievementDao;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Map;

public class AchievementComponent extends PlayerComponent implements PlayerAchievements {
    private Map<AchievementType, IAchievementProgress> progression;

    public AchievementComponent(IPlayer player) {
        super(player);

        this.loadAchievements();
    }

    @Override
    public void loadAchievements() {
        if (this.progression != null) {
            this.progression.clear();
        }

        this.progression = PlayerAchievementDao.getAchievementProgress(this.getPlayer().getId());
    }

    @Override
    public void progressAchievement(AchievementType type, int data) {
        IAchievementGroup achievementGroup = AchievementManager.getInstance().getAchievementGroup(type);

        if (achievementGroup == null) {
            return;
        }

        IAchievementProgress progress;

        if (this.progression.containsKey(type)) {
            progress = this.progression.get(type);
        } else {
            progress = new AchievementProgress(1, 0);
            this.progression.put(type, progress);
        }

        int totalLevels = achievementGroup.getAchievements().size();
        int currentLevel = progress.getLevel();
        int currentProgress = progress.getProgress();
        int totalProgressRequired = achievementGroup.getAchievement(totalLevels).getProgressNeeded();

        if (achievementGroup.getAchievements() == null)
            return;

        if (totalLevels == currentLevel && currentProgress == totalProgressRequired) {
            return;
        }

        int targetLevel = currentLevel + 1;

        if(targetLevel >= totalLevels) {
            targetLevel = totalLevels;
        }

        final IAchievement currentAchievement = achievementGroup.getAchievement(currentLevel);
        final IAchievement targetAchievement = achievementGroup.getAchievement(targetLevel);

        int progressToGive = Math.min(currentAchievement.getProgressNeeded(), data);
        int remainingProgress = progressToGive >= data ? 0 : data - progressToGive;

        progress.increaseProgress(progressToGive);


        // Current progress is greater or equal than current progression needed.
        if (progress.getProgress() >= currentAchievement.getProgressNeeded()) {
            // subtract the difference and add it onto remainingProgress.
            int difference = progress.getProgress() - currentAchievement.getProgressNeeded();

            progress.decreaseProgress(difference);
            remainingProgress += difference;

            this.processUnlock(currentAchievement, targetAchievement, achievementGroup, progress, targetLevel, type);
            this.getPlayer().getMessenger().broadcast(new FriendNotificationMessageComposer(this.getPlayer().getId(), 1, achievementGroup.getGroupName() + currentAchievement.getLevel()));
            this.getPlayer().getMessenger().sendStatus(true, this.getPlayer().getEntity() != null);

        } else {
            this.getPlayer().getSession().send(new AchievementProgressMessageComposer(progress, achievementGroup));
        }

        boolean hasFinishedGroup = false;

        if (progress.getLevel() >= achievementGroup.getLevelCount() && progress.getProgress() >= achievementGroup.getAchievement(currentLevel).getProgressNeeded()) {
            hasFinishedGroup = true;
        }

        this.getPlayer().getData().save();
        PlayerAchievementDao.saveProgress(this.getPlayer().getId(), type, progress);

        this.getPlayer().flush();
    }

    private void processUnlock(IAchievement currentAchievement, IAchievement targetAchievement, IAchievementGroup achievementGroup, IAchievementProgress progress, int targetLevel, AchievementType type) {
        this.getPlayer().getData().increaseAchievementPoints(currentAchievement.getRewardAchievement());

        boolean isPixel = false;

        switch (currentAchievement.getRewardType()){
            case 0:
                this.getPlayer().getData().increaseActivityPoints(currentAchievement.getRewardActivityPoints());
                isPixel = true;
                break;
            case 5:
                this.getPlayer().getData().increaseVipPoints(currentAchievement.getRewardActivityPoints());
                break;
            case 103:
                this.getPlayer().getData().increaseSeasonalPoints(currentAchievement.getRewardActivityPoints());
                break;
        }

        this.getPlayer().poof();
        this.getPlayer().sendBalance();

        if(isPixel) {
            this.getPlayer().getSession().send(new UpdateActivityPointsMessageComposer(this.getPlayer().getData().getActivityPoints(), currentAchievement.getRewardAchievement(), 0));
        }

        boolean hasFinished = false;

        if (achievementGroup.getAchievement(targetLevel) != null) {
            hasFinished = progress.increaseLevel(achievementGroup.getLevelCount());
        }

        // Achievement unlocked!
        this.getPlayer().getSession().send(new AchievementPointsMessageComposer(this.getPlayer().getData().getAchievementPoints(), this.getPlayer().getStats().getLevel()));
        this.getPlayer().getSession().send(new AchievementProgressMessageComposer(progress, achievementGroup));
        this.getPlayer().getSession().send(new AchievementUnlockedMessageComposer(achievementGroup.getCategory().toString(), achievementGroup.getGroupName(), achievementGroup.getId(), targetAchievement, hasFinished));

        this.getPlayer().getInventory().achievementBadge(type.getGroupName(), currentAchievement.getLevel());

        this.getPlayer().flush();
    }

    @Override
    public boolean hasStartedAchievement(AchievementType achievementType) {
        return this.progression.containsKey(achievementType);
    }

    @Override
    public IAchievementProgress getProgress(AchievementType achievementType) {
        return this.progression.get(achievementType);
    }

    @Override
    public void dispose() {
        this.progression.clear();
    }

    public JsonArray toJson() {
        final JsonArray coreArray = new JsonArray();

        for(Map.Entry<AchievementType, IAchievementProgress> achievementEntry : progression.entrySet()) {
            final JsonObject achievementObject = new JsonObject();

            achievementObject.addProperty("type", achievementEntry.getKey().getGroupName());
            achievementObject.addProperty("level", achievementEntry.getValue().getLevel());
            achievementObject.addProperty("progress", achievementEntry.getValue().getProgress());

            coreArray.add(achievementObject);
        }

        return coreArray;
    }
}
