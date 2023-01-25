package com.cometproject.server.network.websockets.packets.outgoing;

public class SurvivalSoundEffectWebPacket {
    private String handle;
    private String sound;

    public SurvivalSoundEffectWebPacket(String handle, String sound) {
        this.handle = handle;
        this.sound = sound;
    }
}
