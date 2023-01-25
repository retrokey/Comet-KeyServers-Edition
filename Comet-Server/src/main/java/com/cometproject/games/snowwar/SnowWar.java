package com.cometproject.games.snowwar;

import java.util.Random;

public class SnowWar {
    public static SnowWarArenaBase[] ArenaTypes = {
            new SnowWarArena8(),
            new SnowWarArena9(),
            new SnowWarArena11(),
            new SnowWarArena10()
    };

    public static SnowWarArenaBase getRandomArena(){
        Random rand = new Random();
        int result = rand.nextInt(4);

        switch (result){
            case 0:
                return new SnowWarArena8();
            default:
            case 1:
                return new SnowWarArena9();
            case 2:
                return new SnowWarArena10();
            case 3:
                return new SnowWarArena11();
        }
    }

    public static final int GAMESECONDS = 120;
    public static final int GAMETURNMILLIS = 150;
    public static final int GAMETURNS = 800;
    public static final int MINPLAYERS = 2;
    public static final int MAXPLAYERS = 10;
    public static final int INLOBBY = 0;
    public static final int INARENA = 1;
    public static final int CLOSE = 0;
    public static final int TIMER_TOLOBBY = 1;
    public static final int STAGE_LOADING = 2;
    public static final int STAGE_STARTING = 3;
    public static final int STAGE_RUNNING = 4;
    public static final int ARENA = 5;
    public static final int ARENA_END = 6;
    public static final int TEAM_BLUE = 1;
    public static final int TEAM_RED = 2;
    public static final int[] TEAMS = {
            SnowWar.TEAM_BLUE,
            SnowWar.TEAM_RED
    };
}