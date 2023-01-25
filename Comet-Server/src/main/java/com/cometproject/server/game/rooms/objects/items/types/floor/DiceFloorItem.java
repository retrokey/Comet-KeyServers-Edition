package com.cometproject.server.game.rooms.objects.items.types.floor;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFactory;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.messages.outgoing.notification.MassEventMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.sessions.Session;

import java.util.Arrays;
import java.util.Random;


public class DiceFloorItem extends RoomItemFloor {
    private boolean isInUse = false;
    private RoomEntity r = null;
    private int rigNumber = -1;

    public Session client = null;
    public DiceFloorItem(RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    @Override
    public boolean onInteract(RoomEntity entity, int requestData, boolean isWiredTrigger) {
        if (!isWiredTrigger) {
            if (!this.getPosition().touching(entity.getPosition())) {
                entity.moveTo(this.getPosition().squareInFront(this.getRotation()).getX(), this.getPosition().squareBehind(this.getRotation()).getY());
                return false;
            }
        }

        if (this.isInUse) {
            return false;
        }

        this.r = entity;

        if(entity instanceof PlayerEntity){
            if(Arrays.asList(this.getRoom().getData().getTags()).contains("69")){
                if(((PlayerEntity) entity).getPlayer().getData().itemOnBet != this){
                    ((PlayerEntity)this.r).getPlayer().getData().itemOnBet = this;
                    ((PlayerEntity)this.r).getPlayer().getData().hasPaidBet = false;
                    ((PlayerEntity) entity).getPlayer().getSession().send(new MassEventMessageComposer("casino/dice"));
                    return false;
                }

                if(((PlayerEntity) entity).getPlayer().getData().itemOnBet == this && !((PlayerEntity) entity).getPlayer().getData().hasPaidBet)
                    return false;
            }
        }

        if (requestData >= 0) {
            if (!"-1".equals(this.getItemData().getData())) {
                this.getItemData().setData("-1");
                this.sendUpdate();

                this.isInUse = true;

                if (entity != null) {
                    if (entity.hasAttribute("diceRoll")) {
                        this.rigNumber = (int) entity.getAttribute("diceRoll");
                        entity.removeAttribute("diceRoll");
                    }
                }

                this.setTicks(RoomItemFactory.getProcessTime(1.5));
            }
        } else {
            this.getItemData().setData("0");
            this.sendUpdate();

            this.saveData();
        }

        return true;
    }

    @Override
    public void onPlaced() {
        if (!"0".equals(this.getItemData().getData())) {
            this.getItemData().setData("0");
        }
    }

    @Override
    public void onPickup() {
        this.cancelTicks();
    }

    @Override
    public void onTickComplete() {
        int num = new Random().nextInt(6) + 1;

        this.getItemData().setData(Integer.toString(this.rigNumber == -1 ? num : this.rigNumber));
        this.sendUpdate();

        this.saveData();

        if (this.rigNumber != -1) this.rigNumber = -1;

        if(Arrays.asList(this.getRoom().getData().getTags()).contains("21")) verifyCount(num);
        if(Arrays.asList(this.getRoom().getData().getTags()).contains("69")) contadorCasino(num);

        this.isInUse = false;
        this.r = null;
    }

    public void verifyCount(int c){
        this.r.addDiceCount(c);

        String messInfo = "Llevo <b>" + this.r.getDiceCount() + "</b> en mi tirada. <i>( " + (this.r.getDiceCount() - Integer.parseInt(this.getDataObject())) + " + " + Integer.parseInt(this.getDataObject()) + "</i> )";

        if(this.r.getDiceCount() > 21){
            messInfo = "<font color='#cc1445'>Saqué <b>" + this.r.getDiceCount() + "</b> en mi tirada, y yo volé de él, y el dado voló hacia la arbolada.</font>";
            this.r.resetDiceCount();
        }

        if(this.r.getDiceCount() == 21){
            messInfo = "<font color='#13cc60'>Saqué <b>" + this.r.getDiceCount() + "</b> en mi tirada.</font>";
            this.r.resetDiceCount();
        }

        this.getRoom().getEntities().broadcastMessage(new TalkMessageComposer(this.r.getId(), messInfo, ChatEmotion.NONE, 26));
    }

    public void contadorCasino(int num){
        if(this.client != null){
            PlayerEntity pEntity = client.getPlayer().getEntity();
            String message = "";
            if(pEntity.getPlayer().getData().betNumber == num){
                int priceWin = pEntity.getPlayer().getData().betMoney * 3;
                if(pEntity.getPlayer().getData().coinOnBet == 1){
                    message = "¡Felicidades! Has ganado " + priceWin + " créditos.";
                    pEntity.getPlayer().getData().increaseCredits(priceWin);
                    pEntity.getPlayer().sendBalance();
                }
                else if(pEntity.getPlayer().getData().coinOnBet == 2){
                    message = "¡Felicidades! Has ganado " + priceWin + " cometas.";
                    pEntity.getPlayer().getData().increaseActivityPoints(priceWin);
                    pEntity.getPlayer().sendBalance();
                }
                else if(pEntity.getPlayer().getData().coinOnBet == 3){
                    message = "¡Felicidades! Has ganado " + priceWin + " estrellas.";
                    pEntity.getPlayer().getData().increaseVipPoints(priceWin);
                    pEntity.getPlayer().sendBalance();
                }
            }
            else message = "Has perdido la apuesta. ¡Vuelve a intentarlo!";

            pEntity.getPlayer().getData().itemOnBet = null;
            pEntity.getPlayer().getData().betMoney = 0;
            pEntity.getPlayer().getData().coinOnBet = 0;
            pEntity.getPlayer().getData().betNumber = 0;
            pEntity.getPlayer().getData().hasPaidBet = false;

            this.getRoom().getEntities().broadcastMessage(new TalkMessageComposer(this.r.getId(), message, ChatEmotion.NONE, 26));
        }
    }
}
