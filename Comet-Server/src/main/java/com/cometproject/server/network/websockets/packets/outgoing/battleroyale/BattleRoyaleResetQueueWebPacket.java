package com.cometproject.server.network.websockets.packets.outgoing.battleroyale;

public class BattleRoyaleResetQueueWebPacket {
    private String handle;

    public BattleRoyaleResetQueueWebPacket(String handle) {
        this.handle = handle;
    }

}
