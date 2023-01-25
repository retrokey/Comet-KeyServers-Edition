package com.cometproject.server.network.messages.incoming.moderation;

import com.cometproject.api.game.GameContext;
import com.cometproject.api.game.rooms.IRoomData;
import com.cometproject.api.game.rooms.settings.RoomAccessType;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.rooms.RoomManager;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.room.engine.RoomDataMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;


public class ModToolRoomActionMessageEvent implements Event {
    private final static String INAPPROPRIATE_ROOM_NAME = "Inappropriate to hotel management";

    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        final int roomId = msg.readInt();

        if (!client.getPlayer().getPermissions().getRank().modTool()) {
            client.disconnect();
            return;
        }

        final boolean lockDoor = msg.readInt() == 1;
        final boolean changeRoomName = msg.readInt() == 1;
        final boolean kickAll = msg.readInt() == 1;

        IRoomData roomData = GameContext.getCurrent().getRoomService().getRoomData(roomId);
        boolean isActive = RoomManager.getInstance().isActive(roomId);

        if (roomData == null)
            return;

        if (changeRoomName) {
            roomData.setName(Locale.getOrDefault("game.room.inappropriate", INAPPROPRIATE_ROOM_NAME));
        }

        if (isActive) {
            Room room = RoomManager.getInstance().get(roomData.getId());

            if (room == null)
                return;

            if (lockDoor)
                room.getData().setAccess(RoomAccessType.DOORBELL);

            GameContext.getCurrent().getRoomService().saveRoomData(room.getData());

            if (kickAll) {
                for (PlayerEntity entity : room.getEntities().getPlayerEntities()) {
                    if (entity.getPlayer().getData().getRank() > client.getPlayer().getData().getRank()) continue;

                    entity.kick();
                }
            }

            if (lockDoor || changeRoomName && !kickAll) {
                room.getEntities().broadcastMessage(new RoomDataMessageComposer(room));
            }
        } else {
            if (lockDoor) {
                roomData.setAccess(RoomAccessType.DOORBELL);
            }
        }

        GameContext.getCurrent().getRoomService().saveRoomData(roomData);
    }
}
