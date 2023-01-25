package com.cometproject.server.game.commands.user;

import com.cometproject.api.game.GameContext;
import com.cometproject.api.game.rooms.models.CustomFloorMapData;
import com.cometproject.api.utilities.JsonUtil;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.players.types.Player;
import com.cometproject.server.game.rooms.RoomManager;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.RoomReloadListener;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.engine.RoomForwardMessageComposer;
import com.cometproject.server.network.sessions.Session;

public class MaxFloorCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {

        if (!client.getPlayer().getEntity().getRoom().getRights().hasRights(client.getPlayer().getId()) && !client.getPlayer().getPermissions().getRank().roomFullControl()) {
            return;
        }

        int maxLength = 64;
        Integer lengthX = maxLength;
        Integer lengthY = maxLength;

        StringBuilder map = new StringBuilder();
        for (int y = 0; y <= lengthY; ++y) {
            for (int x = 0; x <= lengthX; ++x) {
                if (y == 0) {
                    map.append("x");
                }
                else if (y == 1 && x == 0) {
                    map.append("0");
                }
                else if (x == 0) {
                    map.append("x");
                }
                else {
                    map.append("0");
                }
            }
            map.append("\r");
        }

        int doorX = 0;
        int doorY = 1;
        int doorRotation = 2;
        int wallHeight = -1;

        Room room = client.getPlayer().getEntity().getRoom();

        CustomFloorMapData floorMapData = new CustomFloorMapData(doorX, doorY, doorRotation, map.toString().trim(), wallHeight);

        room.getData().setHeightmap(JsonUtil.getInstance().toJson(floorMapData));

        GameContext.getCurrent().getRoomService().saveRoomData(room.getData());
        room.getItems().commit();

        RoomReloadListener reloadListener = new RoomReloadListener(room, (players, newRoom) -> {

            for (Player player : players) {
                if (player.getEntity() != null) continue;
                player.getSession().send(new NotificationMessageComposer("furni_placement_error", Locale.get("command.floor.complete")));
                player.getSession().send(new RoomForwardMessageComposer(newRoom.getId()));
            }
        });

        RoomManager.getInstance().addReloadListener(room.getId(), reloadListener);
        room.reload();
    }

    @Override
    public String getPermission() {
        return "build_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.maxfloor.description");
    }
}
