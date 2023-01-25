package com.cometproject.server.network.websockets.packets.outgoing.roleplay;

public class RPWeaponSyncWebPacket {
    private String handle;
    private String weapon1;
    private String weapon2;
    private String weapon3;
    private String bandage;
    private String currentSlot;

    public RPWeaponSyncWebPacket(String handle, String weapon1, String weapon2, String weapon3, String bandage, String currentSlot) {
        this.handle = handle;
        this.weapon1 = weapon1;
        this.weapon2 = weapon2;
        this.weapon3 = weapon3;
        this.bandage = bandage;
        this.currentSlot = currentSlot;
    }
}