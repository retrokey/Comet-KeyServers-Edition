package com.cometproject.server.network.websockets.packets.outgoing.alerts;

public class YoutubeWebPacket {
    private String handle;
    private String url;

    public YoutubeWebPacket(String handle, String url) {
        this.handle = handle;
        this.url = url;
    }

}
