package com.cometproject.server.game.rooms.types.components.games.survival.types;
import com.cometproject.server.utilities.RandomUtil;

public enum SurvivalPowerUp {
    None(0, "None"),
    Speed(1, "Speed"),
    Sniper(2, "Sniper"),
    Gun(3, "Gun"),
    Bullets(4, "Bullets"),
    Life(5, "Life"),
    Shield(6, "Shield");

    private int id;
    private String name;

    public static SurvivalPowerUp getRandom() {
        SurvivalPowerUp[] powerUps = SurvivalPowerUp.values();

        SurvivalPowerUp result = powerUps[RandomUtil.getRandomInt(0, powerUps.length - 1)];

        if(result == SurvivalPowerUp.None){
            result = SurvivalPowerUp.Gun;
        }

        return result;
    }

    SurvivalPowerUp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getPowerUpId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
