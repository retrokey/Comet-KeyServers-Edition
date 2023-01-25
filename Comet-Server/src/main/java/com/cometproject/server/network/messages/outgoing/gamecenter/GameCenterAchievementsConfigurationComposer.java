package com.cometproject.server.network.messages.outgoing.gamecenter;

import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.api.game.achievements.types.IAchievement;
import com.cometproject.api.game.achievements.types.IAchievementGroup;
import com.cometproject.api.game.players.data.components.achievements.IAchievementProgress;
import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.achievements.AchievementGroup;
import com.cometproject.server.game.achievements.AchievementManager;
import com.cometproject.server.game.players.components.AchievementComponent;

import com.cometproject.server.protocol.messages.MessageComposer;
import com.cometproject.server.protocol.headers.Composers;

import java.util.*;

public class GameCenterAchievementsConfigurationComposer extends MessageComposer {
    private final int gameId;
    private final AchievementComponent playerAchivements;
    private final Map<AchievementType, IAchievementGroup> gameAchievements;

    public GameCenterAchievementsConfigurationComposer(int gameId, AchievementComponent playerAchievements){
        this.gameId = gameId;
        this.playerAchivements = playerAchievements;
        Map<AchievementType, IAchievementGroup> gameAch = AchievementManager.getInstance().getGameCenterAchievementGroups().get(gameId);
        this.gameAchievements = gameAch == null ? new HashMap<>() : gameAch;
    }

    @Override
    public short getId() {
        return Composers.GameAchievementsMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(gameId);

        msg.writeInt(this.gameAchievements.size()); //count
        int count = 0;

        for (Map.Entry<AchievementType, IAchievementGroup> entry : this.gameAchievements.entrySet()) {
            IAchievementProgress achievementProgress = this.playerAchivements.getProgress(entry.getKey());
            IAchievement achievement = achievementProgress == null ? entry.getValue().getAchievement(1) : entry.getValue().getAchievement(achievementProgress.getLevel());

            int actualLevel = achievement.getLevel();
            int actualProgress = achievementProgress == null ? 0 : achievementProgress.getProgress();

            msg.writeInt(count++);
            msg.writeInt(actualLevel);
            msg.writeString(entry.getKey().getGroupName()  + actualLevel);
            msg.writeInt(achievement.getProgressNeeded());
            msg.writeInt(achievement.getProgressNeeded());
            msg.writeInt(achievement.getRewardActivityPoints());
            msg.writeInt(achievement.getRewardAchievement());
            msg.writeInt(actualProgress);
            msg.writeBoolean(actualProgress >= achievement.getProgressNeeded());
            msg.writeString(entry.getKey().getGroupName());
            msg.writeString("basejump");
            msg.writeInt(0);
            msg.writeInt(0);

            //System.out.print("Handled achievement: " + entry.getKey().getGroupName() + achievement.getLevel() + "\n");
        }

        msg.writeString("");
    }
}