package com.cometproject.server.network.messages.outgoing.user.achievements;

import com.cometproject.api.game.achievements.types.IAchievement;
import com.cometproject.api.game.achievements.types.IAchievementGroup;
import com.cometproject.api.game.players.data.components.achievements.IAchievementProgress;
import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.players.components.types.achievements.AchievementProgress;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class AchievementProgressMessageComposer extends MessageComposer {

    private final IAchievementGroup achievementGroup;
    private final IAchievementProgress achievementProgress;

    public AchievementProgressMessageComposer(IAchievementProgress achievementProgress, IAchievementGroup achievementGroup) {
        this.achievementProgress = achievementProgress == null ? null : new AchievementProgress(achievementProgress.getLevel(), achievementProgress.getProgress());
        this.achievementGroup = achievementGroup;
    }

    @Override
    public short getId() {
        return Composers.AchievementProgressedMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        final IAchievement achievement = this.achievementGroup.getAchievement(this.achievementProgress.getLevel());
        int totalLevels = this.achievementGroup.getLevelCount();

        msg.writeInt(achievementGroup.getId());
        msg.writeInt(achievement == null ? totalLevels : achievement.getLevel());
        msg.writeString(achievementGroup.getGroupName() + (achievement == null ? totalLevels : achievement.getLevel()));
        msg.writeInt(achievement == null ? this.achievementGroup.getLevelCount() : achievement.getLevel() == 1 ? 0 : achievementGroup.getAchievement(achievement.getLevel() - 1).getProgressNeeded());
        msg.writeInt(achievement == null ? this.achievementGroup.getAchievement(totalLevels).getProgressNeeded() : achievement.getProgressNeeded());
        msg.writeInt(achievement == null ? this.achievementGroup.getAchievement(totalLevels).getRewardActivityPoints() : achievement.getRewardActivityPoints());
        msg.writeInt(achievement == null ? this.achievementGroup.getAchievement(totalLevels).getRewardType() : achievement.getRewardType());
        msg.writeInt(achievementProgress.getProgress());

        if (achievementProgress.getLevel() >= achievementGroup.getLevelCount() && achievementProgress.getProgress() == (achievement == null ? this.achievementGroup.getAchievement(totalLevels).getProgressNeeded() : achievement.getProgressNeeded())) {
            msg.writeBoolean(true);
        } else {
            msg.writeBoolean(false);
        }

        msg.writeString(achievementGroup.getCategory().toString().toLowerCase());
        msg.writeString("");
        msg.writeInt(achievementGroup.getLevelCount());
        msg.writeInt(0);
    }
}
