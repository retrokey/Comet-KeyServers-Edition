package com.cometproject.server.network.websockets.packets.outgoing.alerts;

public class HotelAlertWebPacket {
    private String handle;
    private String figure;
    private String hotelAlert;

    public HotelAlertWebPacket(String handle, String figure, String hotelAlert) {
        this.handle = handle;
        this.figure = figure;
        this.hotelAlert = hotelAlert;
    }

}
