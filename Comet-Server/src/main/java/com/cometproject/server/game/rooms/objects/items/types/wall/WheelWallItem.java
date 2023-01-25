package com.cometproject.server.game.rooms.objects.items.types.wall;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.game.achievements.BattlePassGlobals;
import com.cometproject.server.game.achievements.types.BattlePassMission;
import com.cometproject.server.game.achievements.types.BattlePassMissionEnums;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFactory;
import com.cometproject.server.game.rooms.objects.items.RoomItemWall;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.messages.outgoing.notification.MassEventMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.sessions.Session;

import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class WheelWallItem extends RoomItemWall {
    private final Random r = new Random();
    private boolean isInUse = false;
    public Session client = null;

    public WheelWallItem(RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    @Override
    public boolean onInteract(RoomEntity entity, int requestData, boolean isWiredTrigger) {
        if (this.isInUse) {
            return false;
        }

        if(entity instanceof PlayerEntity){
            if(Arrays.asList(this.getRoom().getData().getTags()).contains("69")){
                if(((PlayerEntity) entity).getPlayer().getData().itemOnBet != this){
                    ((PlayerEntity)entity).getPlayer().getData().itemOnBet = this;
                    ((PlayerEntity)entity).getPlayer().getData().hasPaidBet = false;
                    ((PlayerEntity) entity).getPlayer().getSession().send(new MassEventMessageComposer("casino/rouletteColor"));

                    return false;
                }

                if(((PlayerEntity) entity).getPlayer().getData().itemOnBet == this && !((PlayerEntity) entity).getPlayer().getData().hasPaidBet)
                    return false;
            }
        }

        this.isInUse = true;

        getItemData().setData("-1");
        sendUpdate();

        setTicks(RoomItemFactory.getProcessTime(4.0D));
        return true;
    }

    @Override
    public void onTickComplete() {
        int wheelPos = r.nextInt(10) + 1;

        getItemData().setData(Integer.toString(wheelPos));
        sendUpdate();

        this.isInUse = false;
        if(Arrays.asList(this.getRoom().getData().getTags()).contains("69")) wheelWallCasino(wheelPos);
    }

    @Override
    public void onPickup() {
        cancelTicks();
    }

    @Override
    public void onPlaced() {
        if (!"0".equals(getItemData().getData())) {
            getItemData().setData("0");
        }
    }

    public void wheelWallCasino(int num){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(client != null){
                    PlayerEntity pEntity = client.getPlayer().getEntity();
                    String message = "";
                    if(num == 3 || num == 8){
                        int priceWin = ((int)Math.round(pEntity.getPlayer().getData().betMoney * 1.2));
                        if(pEntity.getPlayer().getData().coinOnBet == 1){
                            message = "¡Felicidades! Has ganado " + priceWin + " créditos.";
                            pEntity.getPlayer().getData().increaseCredits(priceWin);
                            pEntity.getPlayer().sendBalance();
                        }
                        else if(pEntity.getPlayer().getData().coinOnBet == 2){
                            message = "¡Felicidades! Has ganado " + priceWin + " asteroides.";
                            pEntity.getPlayer().getData().increaseActivityPoints(priceWin);
                            pEntity.getPlayer().sendBalance();
                        }
                        else if(pEntity.getPlayer().getData().coinOnBet == 3){
                            message = "¡Felicidades! Has ganado " + priceWin + " cometas.";
                            pEntity.getPlayer().getData().increaseVipPoints(priceWin);
                            pEntity.getPlayer().sendBalance();
                        }

                        BattlePassMission ms = BattlePassGlobals.battlePassMissions.stream().filter(x -> x.type == BattlePassMissionEnums.MissionType.WINWHEEL).findAny().orElse(null);
                        if(ms != null){
                            if(client.getPlayer().getData().battlePass != null) client.getPlayer().getData().battlePass.addExperiencePoint(ms.id);
                        }
                    }
                    else if(num == 4 || num == 9){
                        int priceWin = pEntity.getPlayer().getData().betMoney * 2;
                        if(pEntity.getPlayer().getData().coinOnBet == 1){
                            message = "¡Felicidades! Has ganado " + priceWin + " créditos.";
                            pEntity.getPlayer().getData().increaseCredits(priceWin);
                            pEntity.getPlayer().sendBalance();
                        }
                        else if(pEntity.getPlayer().getData().coinOnBet == 2){
                            message = "¡Felicidades! Has ganado " + priceWin + " asteroides.";
                            pEntity.getPlayer().getData().increaseActivityPoints(priceWin);
                            pEntity.getPlayer().sendBalance();
                        }
                        else if(pEntity.getPlayer().getData().coinOnBet == 3){
                            message = "¡Felicidades! Has ganado " + priceWin + " cometas.";
                            pEntity.getPlayer().getData().increaseVipPoints(priceWin);
                            pEntity.getPlayer().sendBalance();
                        }

                        BattlePassMission ms = BattlePassGlobals.battlePassMissions.stream().filter(x -> x.type == BattlePassMissionEnums.MissionType.WINWHEEL).findAny().orElse(null);
                        if(ms != null){
                            if(client.getPlayer().getData().battlePass != null) client.getPlayer().getData().battlePass.addExperiencePoint(ms.id);
                        }
                    }
                    else message = "Has perdido la apuesta. ¡Vuelve a intentarlo!";

                    pEntity.getPlayer().getData().itemOnBet = null;
                    pEntity.getPlayer().getData().betMoney = 0;
                    pEntity.getPlayer().getData().coinOnBet = 0;
                    pEntity.getPlayer().getData().betNumber = 0;
                    pEntity.getPlayer().getData().hasPaidBet = false;

                    pEntity.getRoom().getEntities().broadcastMessage(new TalkMessageComposer(pEntity.getId(), message, ChatEmotion.NONE, 26));
                }
                isInUse = false;
            }
        }, 4000);
    }
}
