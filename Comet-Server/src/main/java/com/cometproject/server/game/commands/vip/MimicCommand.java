package com.cometproject.server.game.commands.vip;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.user.details.AvatarAspectUpdateMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.storage.queries.player.PlayerDao;


public class MimicCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if (params.length < 1) {
            MimicCommand.sendNotif(Locale.getOrDefault("command.user.invalid", "Invalid username!"), client);
            return;
        }
        String username = params[0];
        Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
        if (user != null) {
            if (username.equals(client.getPlayer().getData().getUsername())) {
                return;
            }
            if (!user.getPlayer().getSettings().getAllowMimic() && client.getPlayer().getData().getRank() < 3) {
                MimicCommand.sendNotif(Locale.getOrDefault("command.mimic.disabled", "You can't steal the look of this user."), client);
                return;
            }
            PlayerEntity playerEntity = client.getPlayer().getEntity();
            playerEntity.getPlayer().getData().setFigure(user.getPlayer().getData().getFigure());
            playerEntity.getPlayer().getData().setGender(user.getPlayer().getData().getGender());
            playerEntity.getPlayer().getData().save();
            playerEntity.getPlayer().poof();
            client.send(new AvatarAspectUpdateMessageComposer(user.getPlayer().getData().getFigure(), user.getPlayer().getData().getGender()));
            MimicCommand.isExecuted(client);
        }
        else{
            String userFigure = PlayerDao.getUserLook(username);
            String userGenre = PlayerDao.getUserGender(username);
            if(userFigure == null || userGenre == null){
                MimicCommand.sendNotif("Este usuario no existe.", client);
                return;
            }

            PlayerEntity playerEntity = client.getPlayer().getEntity();
            playerEntity.getPlayer().getData().setFigure(userFigure);
            playerEntity.getPlayer().getData().setGender(userGenre);
            playerEntity.getPlayer().getData().save();
            playerEntity.getPlayer().poof();
            client.send(new AvatarAspectUpdateMessageComposer(user.getPlayer().getData().getFigure(), user.getPlayer().getData().getGender()));
            MimicCommand.isExecuted(client);
        }
    }

    @Override
    public String getPermission() {
        return "mimic_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.parameter.username", "%username%");
    }

    @Override
    public String getDescription() {
        return Locale.get("command.mimic.description");
    }
}
