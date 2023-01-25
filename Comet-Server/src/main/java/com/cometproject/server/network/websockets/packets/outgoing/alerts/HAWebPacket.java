package com.cometproject.server.network.websockets.packets.outgoing.alerts;

public class HAWebPacket {
    private String handle;
    private String figure;
    private String sender;
    private String hotelAlert;

    public HAWebPacket(String handle, String figure, String sender, String hotelAlert) {
        this.handle = handle;
        this.figure = figure;
        this.sender = sender;
        this.hotelAlert = hotelAlert;
    }

}
