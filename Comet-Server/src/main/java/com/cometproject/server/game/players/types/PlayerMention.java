package com.cometproject.server.game.players.types;

public class PlayerMention {
    private final String username;
    private final String message;

    public PlayerMention(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public String getUsername() {
        return this.username;
    }

    public String getMessage() {
        return this.message;
    }
}
