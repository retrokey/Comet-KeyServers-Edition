package com.cometproject.server.game.players.types.roleplay;

public class RolePlayAttribute {
    private final int id;
    private String type;
    private int time;
    private String extradata;

    public RolePlayAttribute(final int id, String type, int time, String extradata){
        this.id = id;
        this.type = type;
        this.time = time;
        this.extradata = extradata;
    }

    public int getId() {
        return this.id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getExtradata() {
        return extradata;
    }

    public void setExtradata(String extradata) {
        this.extradata = extradata;
    }

}
