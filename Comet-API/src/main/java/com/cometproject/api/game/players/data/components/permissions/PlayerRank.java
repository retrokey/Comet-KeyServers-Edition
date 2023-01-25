package com.cometproject.api.game.players.data.components.permissions;

public interface PlayerRank {
    int getId();

    String getName();

    boolean floodBypass();

    int floodTime();

    boolean disconnectable();

    boolean bannable();

    boolean modTool();

    boolean alfaTool();

    boolean roomKickable();

    boolean roomFullControl();

    boolean roomMuteBypass();

    boolean roomFilterBypass();

    boolean roomIgnorable();

    boolean roomEnterFull();

    boolean roomEnterLocked();

    boolean roomStaffPick();

    boolean messengerStaffChat();
    boolean messengerModChat();

    boolean messengerLogChat();

    boolean messengerAlfaChat();

    int messengerMaxFriends();

    boolean aboutDetailed();

    boolean aboutStats();
}
