package com.cometproject.server.game.rooms.objects.items.types.floor.survival;

import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.game.players.types.Player;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFactory;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.components.games.RoomGame;
import com.cometproject.server.game.rooms.types.components.games.survival.SurvivalGame;
import com.cometproject.server.game.rooms.types.components.games.survival.types.SurvivalPlayer;
import com.cometproject.server.game.rooms.types.components.games.survival.types.SurvivalPowerUp;
import com.cometproject.server.network.websockets.WebSocketSessionManager;
import com.cometproject.server.network.websockets.packets.outgoing.SurvivalSoundEffectWebPacket;
import com.cometproject.server.utilities.RandomUtil;

public class MunitionBoxFloorItem extends RoomItemFloor {

    private boolean destroyed = false;

    public MunitionBoxFloorItem(RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    @Override
    public boolean onInteract(RoomEntity entity, int requestData, boolean isWiredTrigger) {
        if (!(entity instanceof PlayerEntity) || !this.getItemData().getData().equals("1")) {
            return false;
        }

        final RoomGame game = this.getRoom().getGame().getInstance();

        if(this.getRoom().getGame().getInstance() == null || !(game instanceof SurvivalGame)) {
            return false;
        }

        double distance = entity.getPosition().distanceTo(this.getPosition());

        if (distance > 2) {
            entity.moveTo(this.getPosition().squareInFront(this.getRotation()));
            return false;
        }

        if(this.isDestroyed())
            return false;

        final SurvivalGame survivalGame = (SurvivalGame) game;

        final SurvivalPlayer survivalPlayer = survivalGame.survivalPlayer(((PlayerEntity) entity).getPlayerId());

        survivalPlayer.incrementBullets(20);

        if(((PlayerEntity) entity).getPlayer().getSession().getWsChannel() != null) {
            WebSocketSessionManager.getInstance().sendMessage(((PlayerEntity) entity).getPlayer().getSession().getWsChannel(),
                    new SurvivalSoundEffectWebPacket("survivalSound", "br_open"));
        }


        ((PlayerEntity) entity).getPlayer().getAchievements().progressAchievement(AchievementType.ACH_104, 1);

        this.updateState(0);
        this.setTicks(RoomItemFactory.getProcessTime(1));
        explode();
        return true;
    }

    @Override
    public void onEntityStepOn(RoomEntity entity) {
    }

    public void reset() {
        this.destroyed = false;

        this.getItemData().setData("1");
        this.sendUpdate();

        this.getTile().reload();
    }

    private void explode() {
        if(this.destroyed) {
            return;
        }

        this.destroyed = true;
    }

    private void updateState(int state) {
        this.getItemData().setData(state);
        this.sendUpdate();
    }

    @Override
    public void onTickComplete() {
        this.updateState(1);
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
