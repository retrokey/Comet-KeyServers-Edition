package com.cometproject.server.network.rcon.packets;

public class NewLevelBattlePass {
    public String handle;
    public String missionName;
    public String rewardImage;

    public NewLevelBattlePass(String missionName, String rewardImage){
        this.handle = "newLevelBattlePass";
        this.missionName = missionName;
        this.rewardImage = rewardImage;
    }
}
