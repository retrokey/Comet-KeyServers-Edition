package com.cometproject.server.game.pets.races.plants;


public class PetMonsterPlant {

    private String name;
    private int rarity;
    private int lifeTime;
    private int id;
    private int growthTime;

    public PetMonsterPlant(int id, String name, int rarity, int lifeTime, int growthTime) {
        this.name = name;
        this.rarity = rarity;
        this.lifeTime = lifeTime;
        this.id = id;
        this.growthTime = growthTime;
    }

    public int getGrowthTime() {
        return growthTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public int getId() {
        return id;
    }

    public int getRarity() {
        return rarity;
    }


}
