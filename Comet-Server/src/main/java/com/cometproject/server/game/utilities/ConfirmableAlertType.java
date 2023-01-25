package com.cometproject.server.game.utilities;

public enum ConfirmableAlertType {
    MINIGAME(1),
    RP_PACKAGE(2);

    private final int id;
    ConfirmableAlertType(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }
}

