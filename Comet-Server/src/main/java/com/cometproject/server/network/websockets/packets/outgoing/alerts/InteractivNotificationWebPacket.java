package com.cometproject.server.network.websockets.packets.outgoing.alerts;

public class InteractivNotificationWebPacket {
    private String handle;
    private String type;
    private String username;
    private String category;
    private String text;
    private String incoming;

    public InteractivNotificationWebPacket(String handle, String type, String username, String category, String text, String incoming) {
        this.handle = handle;
        this.type = type;
        this.username = username;
        this.category = category;
        this.text = text;
        this.incoming = incoming;
    }
}
