package com.cometproject.server.game.commands.user.room;

import com.cometproject.api.game.GameContext;
import com.cometproject.api.game.rooms.models.CustomFloorMapData;
import com.cometproject.api.game.rooms.models.RoomTileState;
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

public class AutoFloorCommand extends ChatCommand {

    @Override
    public void execute(Session client, String[] message) {
        if (!client.getPlayer().getEntity().getRoom().getRights().hasRights(client.getPlayer().getId()) && !client.getPlayer().getPermissions().getRank().roomFullControl())
            return;

        Room room = client.getPlayer().getEntity().getRoom();
        int sizeX = room.getMapping().getModel().getSizeX();
        int sizeY = room.getMapping().getModel().getSizeY();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < sizeY; i++) {
            StringBuilder text2 = new StringBuilder();
            for (int j = 0; j < sizeX; j++) {
                if (!room.getMapping().getTile(j, i).hasItems()) {
                    text2.append("x");
                } else {
                    text2.append(this.parseInvers(room.getMapping().getTile(j, i).getTileHeight()));
                }
            }
            text.append(text2);
            text.append((char) 13);
        }

        final CustomFloorMapData floorMapData = new CustomFloorMapData(room.getModel().getDoorX(), room.getModel().getDoorY(), room.getModel().getDoorRotation(), text.toString().trim(), room.getModel().getRoomModelData().getWallHeight());

        room.getData().setHeightmap(JsonUtil.getInstance().toJson(floorMapData));
        GameContext.getCurrent().getRoomService().saveRoomData(room.getData());
        room.getItems().commit();

        final RoomReloadListener reloadListener = new RoomReloadListener(room, (players, newRoom) -> {
            for (Player player : players) {
                if (player.getEntity() == null) {
                    player.getSession().send(new NotificationMessageComposer("furni_placement_error", Locale.get("command.floor.complete")));
                    player.getSession().send(new RoomForwardMessageComposer(newRoom.getId()));
                }
            }
        });

        RoomManager.getInstance().addReloadListener(room.getId(), reloadListener);
        room.reload();
    }

    @Override
    public String getPermission() {
        return "autofloor_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.autofloor.description");
    }

    private char parseInvers(double input)
    {
        char result;
        if (input != 0.0)
        {
            if (input != 1.0)
            {
                if (input != 2.0)
                {
                    if (input != 3.0)
                    {
                        if (input != 4.0)
                        {
                            if (input != 5.0)
                            {
                                if (input != 6.0)
                                {
                                    if (input != 7.0)
                                    {
                                        if (input != 8.0)
                                        {
                                            if (input != 9.0)
                                            {
                                                if (input != 10.0)
                                                {
                                                    if (input != 11.0)
                                                    {
                                                        if (input != 12.0)
                                                        {
                                                            if (input != 13.0)
                                                            {
                                                                if (input != 14.0)
                                                                {
                                                                    if (input != 15.0)
                                                                    {
                                                                        if (input != 16.0)
                                                                        {
                                                                            if (input != 17.0)
                                                                            {
                                                                                if (input != 18.0)
                                                                                {
                                                                                    if (input != 19.0)
                                                                                    {
                                                                                        if (input != 20.0)
                                                                                        {
                                                                                            if (input != 21.0)
                                                                                            {
                                                                                                if (input != 22.0)
                                                                                                {
                                                                                                    if (input != 23.0)
                                                                                                    {
                                                                                                        if (input != 24.0)
                                                                                                        {
                                                                                                            if (input != 25.0)
                                                                                                            {
                                                                                                                if (input != 26.0)
                                                                                                                {
                                                                                                                    if (input != 27.0)
                                                                                                                    {
                                                                                                                        if (input != 28.0)
                                                                                                                        {
                                                                                                                            if (input != 29.0)
                                                                                                                            {
                                                                                                                                if (input != 30.0)
                                                                                                                                {
                                                                                                                                    if (input != 31.0)
                                                                                                                                    {
                                                                                                                                        if (input != 32.0)
                                                                                                                                        {
                                                                                                                                            result = 'x';
                                                                                                                                        }
                                                                                                                                        else
                                                                                                                                        {
                                                                                                                                            result = 'w';
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                    else
                                                                                                                                    {
                                                                                                                                        result = 'v';
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                else
                                                                                                                                {
                                                                                                                                    result = 'u';
                                                                                                                                }
                                                                                                                            }
                                                                                                                            else
                                                                                                                            {
                                                                                                                                result = 't';
                                                                                                                            }
                                                                                                                        }
                                                                                                                        else
                                                                                                                        {
                                                                                                                            result = 's';
                                                                                                                        }
                                                                                                                    }
                                                                                                                    else
                                                                                                                    {
                                                                                                                        result = 'r';
                                                                                                                    }
                                                                                                                }
                                                                                                                else
                                                                                                                {
                                                                                                                    result = 'q';
                                                                                                                }
                                                                                                            }
                                                                                                            else
                                                                                                            {
                                                                                                                result = 'p';
                                                                                                            }
                                                                                                        }
                                                                                                        else
                                                                                                        {
                                                                                                            result = 'o';
                                                                                                        }
                                                                                                    }
                                                                                                    else
                                                                                                    {
                                                                                                        result = 'n';
                                                                                                    }
                                                                                                }
                                                                                                else
                                                                                                {
                                                                                                    result = 'm';
                                                                                                }
                                                                                            }
                                                                                            else
                                                                                            {
                                                                                                result = 'l';
                                                                                            }
                                                                                        }
                                                                                        else
                                                                                        {
                                                                                            result = 'k';
                                                                                        }
                                                                                    }
                                                                                    else
                                                                                    {
                                                                                        result = 'j';
                                                                                    }
                                                                                }
                                                                                else
                                                                                {
                                                                                    result = 'i';
                                                                                }
                                                                            }
                                                                            else
                                                                            {
                                                                                result = 'h';
                                                                            }
                                                                        }
                                                                        else
                                                                        {
                                                                            result = 'g';
                                                                        }
                                                                    }
                                                                    else
                                                                    {
                                                                        result = 'f';
                                                                    }
                                                                }
                                                                else
                                                                {
                                                                    result = 'e';
                                                                }
                                                            }
                                                            else
                                                            {
                                                                result = 'd';
                                                            }
                                                        }
                                                        else
                                                        {
                                                            result = 'c';
                                                        }
                                                    }
                                                    else
                                                    {
                                                        result = 'b';
                                                    }
                                                }
                                                else
                                                {
                                                    result = 'a';
                                                }
                                            }
                                            else
                                            {
                                                result = '9';
                                            }
                                        }
                                        else
                                        {
                                            result = '8';
                                        }
                                    }
                                    else
                                    {
                                        result = '7';
                                    }
                                }
                                else
                                {
                                    result = '6';
                                }
                            }
                            else
                            {
                                result = '5';
                            }
                        }
                        else
                        {
                            result = '4';
                        }
                    }
                    else
                    {
                        result = '3';
                    }
                }
                else
                {
                    result = '2';
                }
            }
            else
            {
                result = '1';
            }
        }
        else
        {
            result = '0';
        }
        return result;
    }

}