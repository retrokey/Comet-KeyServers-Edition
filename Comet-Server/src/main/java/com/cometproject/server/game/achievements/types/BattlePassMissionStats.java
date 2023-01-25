package com.cometproject.server.game.achievements.types;

public class BattlePassMissionStats {
    public int id;
    public String missionName;
    public int expDone;
    public int expMax;
    public String imageReward;

    public BattlePassMissionStats(int id, String missionName, int expDone, int expMax, String imageReward){
        this.id = id;
        this.missionName = missionName;
        this.expDone = expDone;
        this.expMax = expMax;
        this.imageReward = imageReward;
    }
}
