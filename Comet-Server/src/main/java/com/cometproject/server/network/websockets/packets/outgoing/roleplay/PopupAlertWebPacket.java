package com.cometproject.server.network.websockets.packets.outgoing.roleplay;

public class PopupAlertWebPacket {
    private String handle;
    private String badge;
    private String popupTitle;
    private String hotelAlert;

    public PopupAlertWebPacket(String handle, String badge, String popupTitle, String hotelAlert) {
        this.handle = handle;
        this.badge = badge;
        this.popupTitle = popupTitle;
        this.hotelAlert = hotelAlert;
    }

}