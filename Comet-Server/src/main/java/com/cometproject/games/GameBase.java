package com.cometproject.games;

public class GameBase {
    public boolean isEnabled;
    public final int gameId;
    public final String gameName;
    public final String bgColor;
    public final String textColor;
    public final String imagesPath;

    public GameBase(int id, String code, String bgcolor, String textcolor, String imagespath) {
        this.gameId = id;
        this.gameName = code;
        this.bgColor = bgcolor;
        this.textColor = textcolor;
        this.imagesPath = imagespath;

        GamesLeaderboard.leaderboards.put(this.gameId, new GamesLeaderboard(this.gameId));
    }
}