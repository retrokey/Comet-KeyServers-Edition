package com.cometproject.server.game.players.types;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MisteryComponent {
    int playerId;
    String mistery_key;
    String mistery_box;


    public MisteryComponent(ResultSet data) throws SQLException {
        this.playerId = data.getInt("player_id");
        this.mistery_key = data.getString("mistery_key");
        this.mistery_box = data.getString("mistery_box");
    }

    public MisteryComponent(int playerId){
        this.playerId = playerId;
        this.mistery_key = "";
        this.mistery_box = "";
    }

    public String getMisteryBox() { return mistery_box; }
    public String getMisteryKey() { return mistery_key; }

    public void setMisteryBox(String box) { this.mistery_box = box; }
    public void setMisteryKey(String key) { this.mistery_key = key; }
}
