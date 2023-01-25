package com.cometproject.server.game.commands.user;

import com.cometproject.api.game.rooms.entities.RoomEntityStatus;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.network.sessions.Session;


public class LayCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        PlayerEntity playerEntity = client.getPlayer().getEntity();
        if (playerEntity.hasStatus(RoomEntityStatus.LAY)) {
            playerEntity.removeStatus(RoomEntityStatus.LAY);
            playerEntity.markNeedsUpdate();
            isExecuted(client);
        } else if (playerEntity.hasStatus(RoomEntityStatus.SIT)) {
            playerEntity.removeStatus(RoomEntityStatus.SIT);
            playerEntity.addStatus(RoomEntityStatus.LAY, "0.5");
            playerEntity.markNeedsUpdate();
            isExecuted(client);
        } else {
            playerEntity.addStatus(RoomEntityStatus.LAY, "0.5");
            playerEntity.markNeedsUpdate();
            isExecuted(client);
        }
    }

    @Override
    public String getPermission() {
        return "lay_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.lay.description");
    }

    @Override
    public boolean canDisable() {
        return true;
    }
}
