package com.cometproject.games.snowwar.gameevents;
import com.cometproject.games.snowwar.gameobjects.HumanGameObject;
public class UserMove extends Event {
    public HumanGameObject player;
    public int x;
    public int y;

    public UserMove(HumanGameObject player, int x, int y) {
        this.EventType = 2;
        this.player = player;
        this.x = x;
        this.y = y;
    }

    public void apply() {
        this.player.setMove(this.x, this.y);
    }
}