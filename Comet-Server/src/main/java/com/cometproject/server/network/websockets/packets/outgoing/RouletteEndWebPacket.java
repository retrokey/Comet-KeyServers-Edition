package com.cometproject.server.network.websockets.packets.outgoing;

public class RouletteEndWebPacket {
    private String handle;

    public RouletteEndWebPacket(String handle) {
        this.handle = handle;
    }

}
