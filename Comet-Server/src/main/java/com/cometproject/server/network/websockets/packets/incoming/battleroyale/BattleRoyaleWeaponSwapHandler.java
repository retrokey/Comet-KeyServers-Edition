package com.cometproject.server.network.websockets.packets.incoming.battleroyale;

import com.cometproject.server.game.rooms.types.components.games.RoomGame;
import com.cometproject.server.game.rooms.types.components.games.survival.SurvivalGame;
import com.cometproject.server.game.rooms.types.components.games.survival.types.SurvivalPlayer;
import com.cometproject.server.game.rooms.types.components.games.survival.types.SurvivalPowerUp;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.network.websockets.packets.incoming.AbstractWebSocketHandler;
import io.netty.channel.ChannelHandlerContext;

public class BattleRoyaleWeaponSwapHandler extends AbstractWebSocketHandler<BattleRoyaleWeaponSwapHandler.ASMData> {

    public BattleRoyaleWeaponSwapHandler() {
        super(ASMData.class);
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ASMData eventData) {
        int playerId = Integer.parseInt(eventData.session);
        String weapon = eventData.weapon;
        Session s = NetworkManager.getInstance().getSessions().getByPlayerId(playerId);


        if(s != null){
            if(s.getPlayer().getEntity() != null && s.getPlayer().getEntity().isSurvivalMode()) {
                if(s.getPlayer() == null || s.getPlayer().getEntity() == null)
                    return;

                final RoomGame game = s.getPlayer().getEntity().getRoom().getGame().getInstance();

                if (!(game instanceof SurvivalGame))
                    return;

                final SurvivalGame survivalGame = (SurvivalGame) game;
                final SurvivalPlayer survivalPlayer = survivalGame.survivalPlayer(playerId);

                if(survivalPlayer == null)
                    return;

                if (survivalPlayer.getBullets() < 1) return;

                if(weapon.equals("gun") && !survivalPlayer.getPowerUpList().contains(SurvivalPowerUp.Gun) || weapon.equals("sniper") && !survivalPlayer.getPowerUpList().contains(SurvivalPowerUp.Sniper))
                    return;

                SurvivalPowerUp powerUp = SurvivalPowerUp.None;

                switch (weapon){
                    case "gun":
                        powerUp = SurvivalPowerUp.Gun;
                        break;
                    case "sniper":
                        powerUp = SurvivalPowerUp.Sniper;
                }

                survivalPlayer.powerUp(powerUp, SurvivalPowerUp.None, true);
            }
        }

    }

    class ASMData {
        private String session;
        private String weapon;
    }
}
