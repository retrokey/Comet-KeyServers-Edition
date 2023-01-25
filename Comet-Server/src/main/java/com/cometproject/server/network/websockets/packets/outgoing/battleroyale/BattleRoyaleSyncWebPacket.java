package com.cometproject.server.network.websockets.packets.outgoing.battleroyale;

public class BattleRoyaleSyncWebPacket {
    private String handle;
    private String figure;
    private String health;
    private String shield;
    private String bullets;
    private String kills;
    private String remaining;
    private String damage;

    public BattleRoyaleSyncWebPacket(String handle, String figure, String health, String shield, String bullets, String kills, String remaining, String damage) {
        this.handle = handle;
        this.figure = figure;
        this.health = health;
        this.shield = shield;
        this.bullets = bullets;
        this.kills = kills;
        this.remaining = remaining;
        this.damage = damage;
    }

}
