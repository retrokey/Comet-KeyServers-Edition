package com.cometproject.server.game.permissions;

public enum PermissionSetting {

    DISALLOWED,


    ALLOWED,


    ROOM_OWNER;

    public static PermissionSetting fromString(String value) {
        switch (value) {
            case "1":
                return ALLOWED;
            case "2":
                return ROOM_OWNER;

        }

        return DISALLOWED;
    }
}
