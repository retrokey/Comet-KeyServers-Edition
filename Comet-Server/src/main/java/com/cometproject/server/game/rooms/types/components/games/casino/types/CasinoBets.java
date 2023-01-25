package com.cometproject.server.game.rooms.types.components.games.casino.types;

import com.cometproject.server.game.players.types.Player;

public class CasinoBets {
    private final Player player;
    private final int bet;
    private final int amount;
    private boolean paid;

    public CasinoBets(Player player, int bet, int amount, boolean paid) {
        this.player = player;
        this.bet = bet;
        this.amount = amount;
        this.paid = paid;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getAmount() { return amount; }

    public int getBet() { return bet; }

    public int getPlayerId() {
        return this.player.getData().getId();
    }

    public void setPaid(){
        this.paid = true;
    }

    public boolean isPaid() {
        return paid;
    }
}
