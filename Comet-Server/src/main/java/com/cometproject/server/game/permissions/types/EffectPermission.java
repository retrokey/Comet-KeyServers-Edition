package com.cometproject.server.game.permissions.types;

import java.sql.ResultSet;
import java.sql.SQLException;


public class EffectPermission {
    private int effectId;
    private int playerId;
    private boolean enabled;

    public EffectPermission(ResultSet data) throws SQLException {
        this.effectId = data.getInt("effect_id");
        this.playerId = data.getInt("player_id");
        this.enabled = data.getString("enabled").equals("1");
    }

    public int getEffectId() {
        return effectId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
