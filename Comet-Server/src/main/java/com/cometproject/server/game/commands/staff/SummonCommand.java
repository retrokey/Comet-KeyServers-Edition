package com.cometproject.server.game.commands.staff;

import com.cometproject.api.networking.sessions.ISession;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.players.PlayerManager;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.engine.RoomForwardMessageComposer;
import com.cometproject.server.network.sessions.Session;

public class SummonCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if (params.length != 1) {
            sendNotif(Locale.getOrDefault("command.summon.none", "Who do you want to follow you?"), client);
            return;
        }

        final String username = params[0];

        if(username.equals("everyone")){
            for(ISession session : NetworkManager.getInstance().getSessions().getSessions().values()){
                if(session != null){
                    if(session.getPlayer().getEntity() != null && session.getPlayer().getEntity().getRoom() == client.getPlayer().getEntity().getRoom())
                        continue;

                    session.send(new NotificationMessageComposer(Locale.getOrDefault("command.summon.icon", "trade_block"), Locale.get("command.summon.summoned").replace("%summoner%", client.getPlayer().getData().getUsername())));
                    session.send(new RoomForwardMessageComposer(client.getPlayer().getEntity().getRoom().getId()));
                    session.getPlayer().bypassRoomAuth(true);
                }
            }
            isExecuted(client);
            return;
        }

        if(username.equals("all_ghosts")){
            for(ISession session : NetworkManager.getInstance().getSessions().getSessions().values()){
                if(session != null && session.getPlayer().getEntity() == null){
                    session.send(new NotificationMessageComposer(Locale.getOrDefault("command.summon.icon", "trade_block"), Locale.get("command.summon.summoned").replace("%summoner%", client.getPlayer().getData().getUsername())));
                    session.send(new RoomForwardMessageComposer(client.getPlayer().getEntity().getRoom().getId()));
                    session.getPlayer().bypassRoomAuth(true);
                }
            }
            isExecuted(client);
            return;
        }

        if (!PlayerManager.getInstance().isOnline(username)) {
            sendNotif(Locale.getOrDefault("command.user.offline", "This user is offline!"), client);
            return;
        }

        Session session = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);

        if (session == null) {
            sendNotif(Locale.getOrDefault("command.user.offline", "This user is offline!"), client);
            return;
        }

        session.send(new NotificationMessageComposer(Locale.getOrDefault("command.summon.icon", "trade_block"), Locale.get("command.summon.summoned").replace("%summoner%", client.getPlayer().getData().getUsername())));
        session.send(new RoomForwardMessageComposer(client.getPlayer().getEntity().getRoom().getId()));

        session.getPlayer().bypassRoomAuth(true);
        isExecuted(client);
    }

    @Override
    public String getPermission() {
        return "summon_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.parameter.username", "%username%");
    }

    @Override
    public String getDescription() {
        return Locale.get("command.summon.description");
    }
}
