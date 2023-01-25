package com.cometproject.server.network.messages.outgoing.user.talents;

import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.api.game.achievements.types.IAchievementGroup;
import com.cometproject.api.game.achievements.types.ITalentTrackLevel;
import com.cometproject.api.game.players.data.components.achievements.IAchievementProgress;
import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.achievements.AchievementManager;
import com.cometproject.server.game.achievements.types.TalentTrackLevel;
import com.cometproject.server.game.players.components.AchievementComponent;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

import java.util.Map;


public class TalentTrackMessageComposer extends MessageComposer {
    private final AchievementComponent achievementComponent;

    public TalentTrackMessageComposer(final AchievementComponent achievementComponent) {
        this.achievementComponent = achievementComponent;
    }

    @Override
    public short getId() {
        return Composers.TalentTrackMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeString("citizenship");
        msg.writeInt(AchievementManager.getInstance().getTalentTrack().size()); // size

        for (Map.Entry<Integer, ITalentTrackLevel> entry : AchievementManager.getInstance().getTalentTrack().entrySet()) {
            ITalentTrackLevel level = entry.getValue();
            TalentTrackState state = TalentTrackState.LOCKED;
            int currentLevel = 1;
            state = TalentTrackState.IN_PROGRESS;

            msg.writeInt(level.getLevel());
            msg.writeInt(state.id); // STATE

            msg.writeInt(level.getAchievements().size());

            final TalentTrackState finalState = state;
            int i = 0;

            for(AchievementType type : level.getAchievements()){
                IAchievementGroup achievementGroup = AchievementManager.getInstance().getAchievementGroup(type);
                IAchievementProgress achievementProgress = this.achievementComponent.getProgress(type);

                msg.writeInt(achievementGroup.getId()); // ID
                msg.writeInt(i++); // ID
                msg.writeString(achievementGroup.getGroupName() + "1");

                if(finalState != TalentTrackState.LOCKED) {
                    if(achievementProgress != null && achievementProgress.getProgress() < achievementGroup.getAchievement(1).getProgressNeeded()){
                        msg.writeInt(2);
                    } else {
                        msg.writeInt(1);
                    }
                } else {
                    msg.writeInt(0);
                }

                msg.writeInt(achievementProgress == null ? 0 : achievementProgress.getProgress()); // IN PROGRESS / LOCKED / 0
                msg.writeInt(achievementGroup.getAchievement(1).getProgressNeeded()); // IN PROGRESS / LOCKED / 0
            }

            msg.writeInt(-1); // size
            msg.writeInt(-1); // size

        }
    }

    private enum TalentTrackState {
        LOCKED(0),
        IN_PROGRESS(1),
        COMPLETED(2);

        public final int id;

        TalentTrackState(int id) {
            this.id = id;
        }
    }
}



