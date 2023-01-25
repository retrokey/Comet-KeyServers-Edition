package com.cometproject.server.network.rcon.packets;

import com.cometproject.server.game.achievements.types.BattlePassMissionStats;

import java.util.ArrayList;

public class UpdateBattlePass {
    public String handle;
    public boolean isActive;
    public int level;
    public int exp;
    public ArrayList<BattlePassMissionStats> missionStats;

    public UpdateBattlePass(boolean isActive, int level, int exp, ArrayList<BattlePassMissionStats> missionStats){
        this.handle = "updateBattlePass";
        this.isActive = isActive;
        this.level = level;
        this.exp = exp;
        this.missionStats = missionStats;
    }
}
