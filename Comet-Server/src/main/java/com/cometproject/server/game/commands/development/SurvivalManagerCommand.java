package com.cometproject.server.game.commands.development;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.types.components.games.GameType;
import com.cometproject.server.game.rooms.types.components.games.RoomGame;
import com.cometproject.server.game.rooms.types.components.games.survival.SurvivalGame;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.network.websockets.WebSocketSessionManager;
import com.cometproject.server.network.websockets.packets.outgoing.battleroyale.BattleRoyaleSyncWebPacket;

public class SurvivalManagerCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if(client == null || params.length != 1)
            return;

        PlayerEntity player = client.getPlayer().getEntity();

        if(player == null)
            return;

        switch (params[0]){
            case "start":
                if (player.getRoom().getGame().getInstance() == null) {
                    player.getRoom().getGame().createNew(GameType.SURVIVAL);
                    player.getRoom().getGame().getInstance().startTimer(600);
                }
                break;
            case "stop":
                final RoomGame game = player.getRoom().getGame().getInstance();

                if (!(game instanceof SurvivalGame))
                    return;

                ((SurvivalGame) game).gameComplete();
                break;

            case "debug":
                if(client.getWsChannel() == null) {
                    client.getPlayer().sendBubble("newuser", "Dude I'm fucking null what's going on Morty.");
                    return;
                }

                WebSocketSessionManager.getInstance().sendMessage(client.getWsChannel(), new BattleRoyaleSyncWebPacket("br_sync", player.getFigure(), 50 + "", 75 + "", 95 + "", 3 + "", client.getPlayer().getEntity().getRoom().getEntities().getPlayerEntities().size() + "", ""));
            break;

            case "list":
                final RoomGame games = player.getRoom().getGame().getInstance();

                if (!(games instanceof SurvivalGame))
                    return;

                //client.send(new AlertMessageComposer(((SurvivalGame) games).().toString()));

                break;
        }

    }

    @Override
    public String getPermission() {
        return "betsystem_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.betsystem.description");
    }
}
