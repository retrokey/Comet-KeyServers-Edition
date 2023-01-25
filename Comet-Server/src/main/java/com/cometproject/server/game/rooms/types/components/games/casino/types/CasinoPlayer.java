package com.cometproject.server.game.rooms.types.components.games.casino.types;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;

public class CasinoPlayer {
    private final PlayerEntity entity;

    private int bet = 0;
    private int multiplyer = 1;

    public CasinoPlayer(PlayerEntity playerEntity) {
        this.entity = playerEntity;
    }

    public PlayerEntity getEntity() {
        return entity;
    }
}
