package com.cometproject.games.snowwar.gameevents;

import com.cometproject.games.snowwar.gameobjects.HumanGameObject;

public class BallThrowToHuman extends Event {
    public HumanGameObject attacker;
    public HumanGameObject victim;
    public int type;

    public BallThrowToHuman(HumanGameObject attacker, HumanGameObject victim, int type) {
        this.EventType = 3;
        this.attacker = attacker;
        this.victim = victim;
        this.type = type;
    }

    public void apply() {
        this.attacker._vs(this.victim.location3D().x(), this.victim.location3D().y());
        this.attacker.increaseFireLimiter();
    }
}
