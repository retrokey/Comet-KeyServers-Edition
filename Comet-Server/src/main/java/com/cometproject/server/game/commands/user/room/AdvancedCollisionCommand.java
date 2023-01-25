package com.cometproject.server.game.commands.user.room;
import com.cometproject.api.game.GameContext;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.sessions.Session;

public class AdvancedCollisionCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        final Room room = client.getPlayer().getEntity().getRoom();

        if(room == null || room.getData() == null)
            return;

        if (!client.getPlayer().getPermissions().getRank().roomFullControl() && !client.getPlayer().getEntity().hasRights()) {
            return;
        }

        String msg;

        if (client.getPlayer().getEntity().getRoom().getData().advancedCollision()) {
            // show wireds
            room.getData().setAdvancedCollision(false);
            msg = Locale.getOrDefault("command.collision.inactive", "Has desactivado la colisión avanzada.");
        } else {
            // hide wireds
            room.getData().setAdvancedCollision(true);
            msg = Locale.getOrDefault("command.collision.active", "Has activado la colisión avanzada.");
        }

        sendNotif(msg, client);
        GameContext.getCurrent().getRoomService().saveRoomData(room.getData());
    }

    @Override
    public String getPermission() {
        return "collision_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.collision.description");
    }
}
