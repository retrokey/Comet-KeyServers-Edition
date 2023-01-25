package com.cometproject.server.game.achievements.types;
import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.api.game.achievements.types.ITalentTrackLevel;

import java.util.Set;

public class TalentTrackLevel implements ITalentTrackLevel {
    private final int level;
    private final Set<AchievementType> achievements;
    private final Set<Integer> items;
    private final String[] perks;
    private final String[] badges;

    public TalentTrackLevel(int level, Set<AchievementType> achievements, Set<Integer> items, String[] perks, String[] badges) {
        this.level = level;
        this.achievements = achievements;
        this.items = items;
        this.perks = perks;
        this.badges = badges;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public Set<AchievementType> getAchievements() {
        return achievements;
    }

    @Override
    public Set<Integer> getItems() {
        return items;
    }

    @Override
    public String[] getBadges() {
        return badges;
    }

    @Override
    public String[] getPerks() {
        return perks;
    }
}
