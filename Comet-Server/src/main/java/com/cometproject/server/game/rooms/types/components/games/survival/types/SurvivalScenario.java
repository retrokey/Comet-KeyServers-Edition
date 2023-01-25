package com.cometproject.server.game.rooms.types.components.games.survival.types;

public class SurvivalScenario {
    public int roomId;
    public boolean availability;

    public SurvivalScenario(int roomId, boolean availability){
        this.roomId = roomId;
        this.availability = availability;
    }

    public void updateAvailability(boolean status){
        this.availability = status;
    }
}
