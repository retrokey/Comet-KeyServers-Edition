package com.cometproject.server.game.rooms.types.components.games.survival.types;

public class QueueData {
    public int playerId;
    public String username;
    public String figure;

    public QueueData(int playerId, String username, String figure){
        this.playerId = playerId;
        this.username = username;
        this.figure = figure;
    }
}
