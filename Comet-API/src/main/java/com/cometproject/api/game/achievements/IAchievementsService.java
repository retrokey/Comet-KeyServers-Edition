package com.cometproject.api.game.achievements;

import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.api.game.achievements.types.IAchievementGroup;
import com.cometproject.api.game.achievements.types.ITalentTrackLevel;
import com.cometproject.api.utilities.Initialisable;

import java.util.Map;

public interface IAchievementsService extends Initialisable {
    void loadAchievements();
    void loadTalentTrack();
    void loadGameCenterAchievements();

    IAchievementGroup getAchievementGroup(AchievementType groupName);

    Map<AchievementType, IAchievementGroup> getAchievementGroups();

    Map<Integer, Map<AchievementType, IAchievementGroup>> getGameCenterAchievementGroups();

    Map<Integer, ITalentTrackLevel> getTalentTrack();
}
