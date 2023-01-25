package com.cometproject.server.game.achievements.types;

public class BattlePassMission {
    public int id;
    public String missionName;
    public int maxExp;
    public BattlePassMissionEnums.MissionType type;
    public BattlePassRewardEnum.RewardType rewardType;
    public String rewardReference;
    public String imageReward;

    public BattlePassMission(int id, String missionName, int maxExp, BattlePassMissionEnums.MissionType type, BattlePassRewardEnum.RewardType rewardType, String rewardReference, String imageReward){
        this.id = id;
        this.missionName = missionName;
        this.maxExp = maxExp;
        this.type = type;
        this.rewardType = rewardType;
        this.rewardReference = rewardReference;
        this.imageReward = imageReward;
    }
}
