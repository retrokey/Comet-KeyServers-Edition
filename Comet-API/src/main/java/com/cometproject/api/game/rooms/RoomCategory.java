package com.cometproject.api.game.rooms;

public interface RoomCategory {
    int getId();

    String getCategory();
    String getCategoryId();
    String getPublicName();

    boolean canDoActions();

    int getColour();
}
