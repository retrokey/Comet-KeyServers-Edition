package com.cometproject.server.game.commands.user.room;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.storage.queries.rooms.RoomDao;

public class SetIdleTimerCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if(params.length < 1) return;
        int ticks;
        Room room = client.getPlayer().getEntity().getRoom();
        if (client.getPlayer().getEntity() != null && room != null) {
            if (!room.getRights().hasRights(client.getPlayer().getId()) && !client.getPlayer().getPermissions().getRank().roomFullControl()) {
                sendNotif(Locale.getOrDefault("command.need.rights", "You need rights to use this command!"), client);
                return;
            }
            try {
                ticks = Integer.parseInt(params[0]);
            } catch (Exception e) {
                ticks = 600;
            }
            if(ticks < 0) ticks = 600;

            room.getData().setUserIdleTicks(ticks * 2); // testing
            sendNotif(Locale.get("command.setidletimer.set").replace("%seconds%", "" + ticks), client);

            RoomDao.updateRoomIdleTicks(ticks, room.getId());
        }
    }

    @Override
    public String getPermission() {
        return "setidletimer_command";
    }

    @Override
    public String getParameter() {
        return "%seconds%";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.setidletimer.description");
    }
}

