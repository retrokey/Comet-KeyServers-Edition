package com.cometproject.server.network.websockets.packets.outgoing.battleroyale;

import com.cometproject.server.game.rooms.types.components.games.survival.types.QueueData;

import java.util.ArrayList;

public class BattleRoyaleQueueWebPacket {
    private String handle;
    private String roomId;
    private ArrayList<QueueData> figures;
    private String username;

    public BattleRoyaleQueueWebPacket(String handle, int roomId, ArrayList<QueueData> figures) {
        this.handle = handle;
        this.roomId = roomId + "";
        this.figures = figures;
        this.username = "Custom";
    }

}
