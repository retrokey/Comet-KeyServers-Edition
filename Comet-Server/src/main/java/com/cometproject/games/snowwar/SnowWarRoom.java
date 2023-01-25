package com.cometproject.games.snowwar;
import com.cometproject.api.networking.messages.IMessageComposer;
import com.cometproject.games.snowwar.gameevents.Event;
import com.cometproject.games.snowwar.gameobjects.HumanGameObject;
import io.netty.channel.ChannelHandlerContext;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SnowWarRoom extends SynchronizedGameStage {
    public int[] teamScore = {SnowWar.TEAM_BLUE,SnowWar.TEAM_RED};
    public int checksum;
    public int roomId;
    public int Result;
    public int STATUS;
    public int TimeToStart;
    public int Turn;
    public int Winner;
    public String Name;
    public String Owner;
    public boolean LobbyFull;
    public SnowWarArenaBase ArenaType;
    public HumanGameObject MostHits;
    public HumanGameObject MostKills;
    public SnowWarGameStage map;
    public final Map<Integer, Map<Integer, HumanGameObject>> TeamPlayers = new ConcurrentHashMap<>(2);
    public final Map<Integer, HumanGameObject> players = new ConcurrentHashMap<>(10);
    public final List<Event> gameEvents = Collections.synchronizedList(new ArrayList<>());
    public List<ChannelHandlerContext> fullGameStatusQueue;
    private Map<Integer, HumanGameObject> stageLoadedPlayers;

    public SnowWarRoom(int id) {
        super();
        this.ArenaType = new SnowWarArena8();//SnowWar.getRandomArena();

        this.roomId = id;
        this.Name = "SnowStorm level " + this.ArenaType.ArenaType;
        this.map = new SnowWarGameStage();
        this.map.initialize(this.ArenaType);
        for (int TeamId : SnowWar.TEAMS) {
            this.TeamPlayers.put(TeamId, new ConcurrentHashMap<>());
        }
    }

    public void broadcast(IMessageComposer Message) {
        for (HumanGameObject player : this.players.values()) {
            if (player.currentSnowWar != null) {
                player.cn.getChannel().writeAndFlush(Message);
            }
        }
    }

    public Collection<HumanGameObject> getStageLoadedPlayers() {
        if (this.stageLoadedPlayers == null) {
            return null;
        }
        Collection<HumanGameObject> result = this.stageLoadedPlayers.values();
        this.stageLoadedPlayers = null;
        return result;
    }

    public void stageLoaded(HumanGameObject humanObject) {
        if (this.stageLoadedPlayers == null) {
            this.stageLoadedPlayers = new ConcurrentHashMap<>();
        }
        this.stageLoadedPlayers.put(humanObject.objectId, humanObject);
        humanObject.stageLoaded = true;
    }
}