package com.cometproject.server.game.gamecenter;

public class GameCenterInfo {
    private int gameId;
    private String gameName;
    private String gamePath;
    private int roomId;

    public GameCenterInfo(int gameId, String gameName, String gamePath, int roomId) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.gamePath = gamePath;
        this.roomId = roomId;
    }

    public int getGameId() {
        return this.gameId;
    }

    public String getGameName() {
        return this.gameName;
    }

    public String getGamePath() {
        return this.gamePath;
    }

    public int getGameRoomId() {
        return this.roomId;
    }
}