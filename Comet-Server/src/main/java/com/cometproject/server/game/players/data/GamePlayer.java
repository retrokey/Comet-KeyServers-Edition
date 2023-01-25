package com.cometproject.server.game.players.data;

public class GamePlayer {

    private int id;
    private String username;
    private String figure;
    private String gender;
    private int points;
    private int lastPoints;

    public GamePlayer(int id, String username, String figure, String gender, int points, int lastPoints) {
        this.id = id;
        this.username = username;
        this.figure = figure;
        this.gender = gender;
        this.points = points;
        this.lastPoints = lastPoints;
    }

    public int getId() {
        return id;
    }

    public int getPoints() {
        return points;
    }

    public int getLastPoints() {
        return lastPoints;
    }

    public String getUsername() {
        return username;
    }

    public String getFigure() {
        return figure;
    }

    public String getGender() {
        return gender;
    }
}
