package com.cometproject.api.game.rooms.entities;

public enum RoomEntityStatus {
    SIT("sit"),
    MOVE("mv"),
    LAY("lay"),
    SIGN("sign"),
    CONTROLLER("flatctrl"),
    TRADE("trd"),
    VOTE("vote"),
    GESTURE("gst"),
    PLAY("pla"),
    PLAY_DEAD("ded"),
    JUMP("jmp"),
    EAT("eat"),
    SWIM("dip"),

    //Monster Plants status
    RIP("rip"),
    GROW("grw"),
    GROW_1("grw1"),
    GROW_2("grw2"),
    GROW_3("grw3"),
    GROW_4("grw4"),
    GROW_5("grw5"),
    GROW_6("grw6"),
    GROW_7("grw7"),
    FLASH("spd"),
    SAD("sad"),
    HAPPY("sml");

    private final String statusCode;

    RoomEntityStatus(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public static RoomEntityStatus fromString(String key) {
        for (RoomEntityStatus status : values()) {
            if (status.statusCode.equals(key)) {
                return status;
            }
        }

        return null;
    }
}
