package com.cometproject.server.game.commands.user;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.RoomManager;
import com.cometproject.server.game.rooms.objects.entities.RoomEntityType;
import com.cometproject.server.game.rooms.objects.entities.effects.PlayerEffect;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.UpdateInfoMessageComposer;
import com.cometproject.server.network.sessions.Session;


public class IdleCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if (params.length != 0) return;

        PlayerEntity u = client.getPlayer().getEntity();

        if(client.getPlayer() == null || u == null)
            return;

        if(u.isForcedIdle()){
            u.unIdle();
            u.getPlayer().getData().setMotto(client.getPlayer().getData().getLegacyMotto());
            u.getRoom().getEntities().broadcastMessage(new UpdateInfoMessageComposer(client.getPlayer().getEntity()));
            client.send(new UpdateInfoMessageComposer(-1, client.getPlayer().getEntity()));
            u.applyEffect(new PlayerEffect(0));
            u.getPlayer().sendNotif("idle", "Ya no estás ausente.");
            return;
        }

        u.resetIdleTime();
        u.setIdleStatus(true);
        u.applyEffect(new PlayerEffect(903));

    }

    @Override
    public String getPermission() {
        return "idle_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.idle.description");
    }
}
