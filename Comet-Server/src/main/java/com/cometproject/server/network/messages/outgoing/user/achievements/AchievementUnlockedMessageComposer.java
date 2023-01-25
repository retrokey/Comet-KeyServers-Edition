package com.cometproject.server.network.messages.outgoing.user.achievements;

import com.cometproject.api.game.achievements.types.IAchievement;
import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class AchievementUnlockedMessageComposer extends MessageComposer {
    private final String achievementCategory;
    private final String achievementName;
    private final boolean hasFinished;

    private final int currentAchievementId;
    private final IAchievement newAchievement;

    public AchievementUnlockedMessageComposer(final String achievementCategory, String achievementName, int currentAchievementId, IAchievement newAchievement, boolean hasFinished) {
        this.achievementCategory = achievementCategory;
        this.achievementName = achievementName;
        this.currentAchievementId = currentAchievementId;
        this.newAchievement = newAchievement;
        this.hasFinished = hasFinished;
    }

    @Override
    public short getId() {
        return Composers.AchievementUnlockedMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(currentAchievementId);
        msg.writeInt(newAchievement == null ? 1 : newAchievement.getLevel());
        msg.writeInt(144);
        if(this.hasFinished){
            msg.writeString(achievementName + (newAchievement == null ? 1 : (newAchievement.getLevel())));
        } else {
            msg.writeString(achievementName + (newAchievement == null ? 1 : (newAchievement.getLevel() - 1)));
        }
        msg.writeInt(newAchievement == null ? 0 : newAchievement.getRewardAchievement());
        msg.writeInt(newAchievement == null ? 0 : newAchievement.getRewardActivityPoints());
        msg.writeInt(0);
        msg.writeInt(10);
        msg.writeInt(21);
        if(this.hasFinished) {
            msg.writeString(newAchievement != null ? (newAchievement.getLevel() > 1 ? achievementName + (newAchievement.getLevel()) : "") : "");
        } else {
            msg.writeString(newAchievement != null ? (newAchievement.getLevel() > 1 ? achievementName + (newAchievement.getLevel() - 1) + "" : "") : "");
        }

        msg.writeString(achievementCategory);
        msg.writeBoolean(true);
    }
}
