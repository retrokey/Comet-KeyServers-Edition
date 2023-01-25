package com.cometproject.server.logging.entries;

import com.cometproject.server.boot.Comet;
import com.cometproject.server.logging.AbstractLogEntry;
import com.cometproject.server.logging.LogEntryType;

public class OfflineChatLogEntry extends AbstractLogEntry {
    private int senderId;
    private int receiverId;
    private String message;
    private int timestamp;

    public OfflineChatLogEntry(int senderId, int receiverId, String message) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.timestamp = (int) Comet.getTime();
    }

    public OfflineChatLogEntry(int senderId, int receiverId, String message, int timestamp) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.timestamp = timestamp;
    }

    @Override
    public LogEntryType getType() {
        return LogEntryType.OFFLINE_CHATLOG;
    }

    @Override
    public String getString() {
        return this.senderId + "OFF_MSG]" + this.message;
    }

    @Override
    public int getTimestamp() {
        return this.timestamp;
    }

    @Override
    public int getPlayerId() {
        return this.receiverId;
    }
}
