package com.cometproject.server.network.rcon.packets;

import com.cometproject.server.game.achievements.types.BattlePassMissionStats;

import java.util.ArrayList;

public class OpenBattlePass {
    public String handle;
    public boolean isActive;
    public int level;
    public int exp;
    public ArrayList<BattlePassMissionStats> missionStats;
    public String name;
    public String look;

    public OpenBattlePass(boolean isActive, int level, int exp, ArrayList<BattlePassMissionStats> missionStats, String name, String look){
        this.handle = "openBattlePass";
        this.isActive = isActive;
        this.level = level;
        this.exp = exp;
        this.missionStats = missionStats;
        this.name = name;
        this.look = look;
    }
}
