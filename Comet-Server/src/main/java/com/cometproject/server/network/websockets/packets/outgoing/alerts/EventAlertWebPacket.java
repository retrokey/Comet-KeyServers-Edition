package com.cometproject.server.network.websockets.packets.outgoing.alerts;

public class EventAlertWebPacket {
    private String handle;
    private String figure;
    private String eventHost;
    private String eventName;
    private String roomId;

    public EventAlertWebPacket(String handle, String figure, String eventHost, String eventName, String roomId) {
        this.handle = handle;
        this.figure = figure;
        this.eventHost = eventHost;
        this.eventName = eventName;
        this.roomId = roomId;
    }

}
