package com.cometproject.server.game.achievements;

import com.cometproject.api.game.achievements.IAchievementsService;
import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.api.game.achievements.types.IAchievementGroup;
import com.cometproject.api.game.achievements.types.ITalentTrackLevel;
import com.cometproject.server.storage.queries.achievements.AchievementDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AchievementManager implements IAchievementsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AchievementManager.class);
    private static AchievementManager achievementManager;
    private final Map<AchievementType, IAchievementGroup> achievementGroups;
    private final Map<Integer, Map<AchievementType, IAchievementGroup>> gameCenterAchievements;
    private final Map<Integer, ITalentTrackLevel> talentTrack;

    public AchievementManager() {
        this.achievementGroups = new ConcurrentHashMap<>();
        this.gameCenterAchievements = new ConcurrentHashMap<>();
        this.talentTrack = new ConcurrentHashMap<>();
    }

    public static AchievementManager getInstance() {
        if (achievementManager == null) {
            achievementManager = new AchievementManager();
        }

        return achievementManager;
    }

    @Override
    public void initialize() {
        this.loadAchievements();
        this.loadGameCenterAchievements();
        this.loadTalentTrack();
        LOGGER.info("AchievementManager initialized");
    }

    @Override
    public void loadAchievements() {
        if (this.achievementGroups.size() != 0) {
            for (IAchievementGroup achievementGroup : this.achievementGroups.values()) {
                if (achievementGroup.getAchievements().size() != 0) {
                    achievementGroup.getAchievements().clear();
                }
            }

            this.achievementGroups.clear();
        }

        final int achievementCount = AchievementDao.getAchievements(this.achievementGroups);

        LOGGER.info("Loaded " + achievementCount + " achievements (" + this.achievementGroups.size() + " groups)");

    }

    @Override
    public void loadGameCenterAchievements() {
        if(this.gameCenterAchievements.size() != 0) {
            for (Map<AchievementType, IAchievementGroup> achievementGroups : this.gameCenterAchievements.values()) {
                for(IAchievementGroup achievementGroup : achievementGroups.values()) {
                    if (achievementGroup.getAchievements().size() != 0) {
                        achievementGroup.getAchievements().clear();
                    }
                }
            }

            this.gameCenterAchievements.clear();
        }

        final int gameCenterAchievementCount = AchievementDao.getGameCenterAchievements(this.gameCenterAchievements);

        LOGGER.info("Loaded " + gameCenterAchievementCount + " game-center achievements (" + this.gameCenterAchievements.size() + " groups)");
    }

    @Override
    public void loadTalentTrack() {
        if(this.talentTrack.size() != 0) {
            this.talentTrack.clear();
        }

        final int talentTrackCount = AchievementDao.getTalents(this.talentTrack);

        LOGGER.info("Loaded " + talentTrackCount + " talent levels.");
    }

    @Override
    public IAchievementGroup getAchievementGroup(AchievementType groupName) {
        return this.achievementGroups.get(groupName);
    }

    @Override
    public Map<Integer, Map<AchievementType, IAchievementGroup>> getGameCenterAchievementGroups() {
        return this.gameCenterAchievements;
    }

    @Override
    public Map<Integer, ITalentTrackLevel> getTalentTrack() {
        return this.talentTrack;
    }

    @Override
    public Map<AchievementType, IAchievementGroup> getAchievementGroups() {
        return this.achievementGroups;
    }
}
