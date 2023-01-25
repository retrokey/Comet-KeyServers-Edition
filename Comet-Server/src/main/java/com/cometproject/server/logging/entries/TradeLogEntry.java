package com.cometproject.server.logging.entries;

import com.cometproject.server.boot.Comet;
import com.cometproject.server.logging.AbstractLogEntry;
import com.cometproject.server.logging.LogEntryType;

public class TradeLogEntry extends AbstractLogEntry {
    private int senderId;
    private int receiverId;
    private int roomId;
    private String message;
    private int timestamp;

    public TradeLogEntry(int senderId, int receiverId, int roomId, String message) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.roomId = roomId;
        this.message = message;
        this.timestamp = (int) Comet.getTime();
    }

    @Override
    public LogEntryType getType() {
        return LogEntryType.TRADE;
    }

    @Override
    public String getString() {
        return "To: " + this.receiverId + " " + this.message;
    }

    @Override
    public int getTimestamp() {
        return this.timestamp;
    }

    @Override
    public int getRoomId() {
        return this.roomId;
    }

    @Override
    public int getPlayerId() {
        return this.senderId;
    }
}
