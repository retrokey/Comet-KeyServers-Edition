package com.cometproject.server.game.rooms.objects.entities.types.enums;

public enum RoomControllerLevel {
    NONE(0),
    GUEST(1),
    GUILD_MEMBER(2),
    GUILD_ADMIN(3),
    ROOM_OWNER(4),
    MODERATOR(5);

    private final int level;

    RoomControllerLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}