package com.cometproject.api.game.achievements.types;

import java.util.Set;

public interface ITalentTrackLevel {
    int getLevel();

    Set<AchievementType> getAchievements();

    Set<Integer> getItems();

    String[] getBadges();

    String[] getPerks();
}
