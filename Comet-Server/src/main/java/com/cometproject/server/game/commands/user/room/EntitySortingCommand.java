package com.cometproject.server.game.commands.user.room;

import com.cometproject.api.game.GameContext;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.WiredFloorItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.actions.WiredActionRandomEffect;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.addons.WiredAddonUnseenEffect;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.outgoing.room.items.RemoveFloorItemMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.SendFloorItemMessageComposer;
import com.cometproject.server.network.sessions.Session;

public class EntitySortingCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        final Room room = client.getPlayer().getEntity().getRoom();

        if(room == null || room.getData() == null)
            return;

        if (!client.getPlayer().getPermissions().getRank().roomFullControl() && !client.getPlayer().getEntity().hasRights()) {
            return;
        }

        String msg;

        if (client.getPlayer().getEntity().getRoom().getData().hasSorting()) {
            // show wireds
            room.getData().setHasEntitySort(false);
            msg = Locale.getOrDefault("command.entity.shown", "Has desactivado la V.");
        } else {
            // hide wireds
            room.getData().setHasEntitySort(true);
            msg = Locale.getOrDefault("command.entity.hidden", "Has activado la V.");
        }

        sendNotif(msg, client);
        GameContext.getCurrent().getRoomService().saveRoomData(room.getData());
    }

    @Override
    public String getPermission() {
        return "sorting_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.sorting.description");
    }
}
