package com.cometproject.games.snowwar;
import com.cometproject.games.snowwar.gameobjects.GameItemObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class SnowWarArenaBase {
    public int ArenaHeight;
    public int ArenaType;
    public int ArenaWidth;
    public String HeightMap;
    public List<GamefuseObject> fuseObjects = new ArrayList<>(200);
    public List<SpawnPoint> spawnsBLUE = new ArrayList<>(5);
    public List<SpawnPoint> spawnsRED = new ArrayList<>(5);
    public abstract void gameObjects(Map<Integer, GameItemObject> paramMap, SnowWarRoom paramSnowWarRoom);
}