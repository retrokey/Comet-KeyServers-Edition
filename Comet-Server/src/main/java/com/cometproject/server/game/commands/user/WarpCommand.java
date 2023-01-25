package com.cometproject.server.game.commands.user;

import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.objects.entities.RoomEntityType;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.sessions.Session;

public class WarpCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if(client == null || client.getPlayer() == null || client.getPlayer().getEntity() == null || client.getPlayer().getEntity().getRoom() == null) { return; }

        if (!client.getPlayer().getEntity().getRoom().getRights().hasRights(client.getPlayer().getId()) && !client.getPlayer().getPermissions().getRank().roomFullControl()) {
            client.send(new NotificationMessageComposer("time_error", Locale.getOrDefault("needs_rights", "No tienes permisos en la sala para realizar esta acción.")));
            return;
        }

        Position positionToWarp = client.getPlayer().getEntity().getPosition();
        String myUsername = client.getPlayer().getData().getUsername();

        if(params.length > 0) {
            PlayerEntity target = (PlayerEntity) client.getPlayer().getEntity().getRoom().getEntities().getEntityByName(params[0], RoomEntityType.PLAYER);

            if(target == null || target.getUsername().equals(myUsername)) return;

            target.getPlayer().getSession().send(new WhisperMessageComposer(target.getId(), Locale.getOrDefault("warp.received", "Has sido atraído por %username%.").replace("%username%", myUsername), 1));
            target.cancelWalk();
            target.warp(positionToWarp);
            target.markNeedsUpdate();

            return;
        }

        for(PlayerEntity pEntity : client.getPlayer().getEntity().getRoom().getEntities().getPlayerEntities()) {
            if(pEntity.getUsername().equals(myUsername)) continue;

            pEntity.getPlayer().getSession().send(new WhisperMessageComposer(pEntity.getId(), Locale.getOrDefault("warp.received", "Has sido atraído por %username%.").replace("%username%", myUsername), 1));
            pEntity.cancelWalk();
            pEntity.warp(positionToWarp);
            pEntity.markNeedsUpdate();
        }
    }

    @Override
    public String getPermission() {
        return "warp_command";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.warp.description");
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.parameter.colour", "%user%");
    }

}
