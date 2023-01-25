package com.cometproject.server.game.rooms.objects.entities.types;

import com.cometproject.api.game.bots.IBotData;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.api.utilities.JsonUtil;
import com.cometproject.server.game.bots.BotData;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.ai.BotAI;
import com.cometproject.server.game.rooms.objects.entities.types.ai.bots.*;
import com.cometproject.server.game.rooms.objects.entities.types.data.BotDataObject;
import com.cometproject.server.game.rooms.objects.entities.types.data.types.SpyBotData;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.mapping.RoomTile;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.messages.outgoing.room.avatar.LeaveRoomMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class BotEntity extends RoomEntity {
    private IBotData data;
    private int cycleCount = 0;
    private BotAI ai;

    private BotDataObject dataObject;

    private Map<String, Object> attributes = new ConcurrentHashMap<>();

    public BotEntity(IBotData data, int identifier, Position startPosition, int startBodyRotation, int startHeadRotation, Room roomInstance) {
        super(identifier, startPosition, startBodyRotation, startHeadRotation, roomInstance);

        this.data = data;

        switch (data.getBotType()) {
            default:
                this.ai = new DefaultAI(this);
                break;

            case WAITER:
                this.ai = new WaiterAI(this);
                break;

            case QUEST:
                this.ai = new QuestAI(this);
                break;

            case MIMIC:
                this.ai = new MinionAI(this);
                break;

            case SPY:
                this.ai = new SpyAI(this);

                if (this.data.getData() == null) {
                    this.dataObject = new SpyBotData(new LinkedList<>());
                } else {
                    this.dataObject = JsonUtil.getInstance().fromJson(this.data.getData(), SpyBotData.class);
                }

                break;
        }
    }

    public void say(String message) {
        this.getRoom().getEntities().broadcastMessage(new TalkMessageComposer(this.getId(), message, ChatEmotion.NONE, 2));
    }

    @Override
    public boolean joinRoom(Room room, String password) {
        return true;
    }

    @Override
    protected void finalizeJoinRoom() {

    }

    @Override
    public void onReachedTile(RoomTile tile) {
    }

    public void leaveRoom() {
        this.leaveRoom(false, false, false);
    }

    @Override
    public void leaveRoom(boolean isOffline, boolean isKick, boolean toHotelView) {
        // Send leave room message to all instance entities
        this.getRoom().getEntities().broadcastMessage(new LeaveRoomMessageComposer(this.getId()));

        // Remove entity from the room
        this.getRoom().getEntities().removeEntity(this);
        this.getRoom().getBots().removeBot(this.getUsername());

        this.data.dispose();
        this.data = null;

        this.attributes.clear();
    }

    @Override
    public boolean onChat(String message) {
        return false;
    }

    public void saveDataObject() {
        if (this.dataObject != null) {
            this.data.setData(JsonUtil.getInstance().toJson(this.dataObject));
            this.data.save();
        }
    }

    @Override
    public boolean onRoomDispose() {
        // Send leave room message to all instance entities
        this.getRoom().getEntities().broadcastMessage(new LeaveRoomMessageComposer(this.getId()));

        this.saveDataObject();

        this.data.dispose();
        this.data = null;

        this.attributes.clear();

        return true;
    }

    @Override
    public String getUsername() {
        return this.data.getUsername();
    }

    @Override
    public String getMotto() {
        return this.data.getMotto();
    }

    @Override
    public String getFigure() {
        return this.data.getFigure();
    }

    @Override
    public String getGender() {
        return this.data.getGender();
    }

    public int getBotId() {
        return this.data.getId();
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(this.getBotId());
        msg.writeString(this.getUsername());
        msg.writeString(this.getMotto());
        msg.writeString(this.getFigure());
        msg.writeInt(this.getId());

        msg.writeInt(this.getPosition().getX());
        msg.writeInt(this.getPosition().getY());
        msg.writeDouble(this.getPosition().getZ());
        msg.writeInt(this.getBodyRotation());
        msg.writeInt(4);

        msg.writeString(this.getGender().toLowerCase());
        msg.writeInt(this.getRoom().getData().getOwnerId());
        msg.writeString(this.getRoom().getData().getOwner());

        msg.writeInt(10);
        msg.writeShort(0);
        msg.writeShort(1);
        msg.writeShort(2);
        msg.writeShort(3);
        msg.writeShort(4);
        msg.writeShort(5);
        msg.writeShort(6);
        msg.writeShort(7);
        msg.writeShort(8);
        msg.writeShort(9);

    }

    public BotData getData() {
        return (BotData) this.data;
    }

    public int getCycleCount() {
        return this.cycleCount;
    }

    public void decrementCycleCount() {
        cycleCount--;
    }

    public void incrementCycleCount() {
        cycleCount++;
    }

    public void resetCycleCount() {
        this.cycleCount = 0;
    }

    public BotAI getAI() {
        return ai;
    }

    @Override
    public void setAttribute(String attributeKey, Object attributeValue) {
        if (this.attributes.containsKey(attributeKey)) {
            this.attributes.replace(attributeKey, attributeValue);
        } else {
            this.attributes.put(attributeKey, attributeValue);
        }
    }

    @Override
    public Object getAttribute(String attributeKey) {
        return this.attributes.get(attributeKey);
    }

    @Override
    public boolean hasAttribute(String attributeKey) {
        return this.attributes.containsKey(attributeKey);
    }

    @Override
    public void removeAttribute(String attributeKey) {
        this.attributes.remove(attributeKey);
    }

    public BotDataObject getDataObject() {
        return dataObject;
    }
}
