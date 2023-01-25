package com.cometproject.server.network.websockets.packets.outgoing.battleroyale;

public class BattleRoyalePowerUpsWebPacket {
    private String handle;
    private String gun;
    private String sniper;
    private String bandage;

    public BattleRoyalePowerUpsWebPacket(String handle, String gun, String sniper, String bandage) {
        this.handle = handle;
        this.gun = gun;
        this.sniper = sniper;
        this.bandage = bandage;
    }
}