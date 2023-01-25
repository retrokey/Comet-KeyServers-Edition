package com.cometproject.games.snowwar.gameevents;
import com.cometproject.games.snowwar.gameobjects.HumanGameObject;
public class PlayerLeft extends Event {
    public HumanGameObject player;

    public PlayerLeft(HumanGameObject player) {
        this.EventType = 1;
        this.player = player;
    }

    public void apply() {
        this.player.currentSnowWar.queueDeleteObject(this.player);
        this.player.cleanTiles();
    }
}