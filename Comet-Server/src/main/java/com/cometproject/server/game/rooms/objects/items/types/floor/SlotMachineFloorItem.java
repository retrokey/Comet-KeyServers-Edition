package com.cometproject.server.game.rooms.objects.items.types.floor;

import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFactory;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.outgoing.notification.MassEventMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.nuxs.NuxGiftEmailViewMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.storage.queries.catalog.BetDao;
import com.cometproject.server.utilities.RandomUtil;

import java.util.Random;


public class SlotMachineFloorItem extends RoomItemFloor {
    private boolean isInUse = false;

    public SlotMachineFloorItem(RoomItemData itemData, Room room) {
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

        entity.cancelWalk();
        entity.lookTo(this.getPosition().squareInFront(this.getRotation()).getX() - 1, this.getPosition().squareBehind(this.getRotation()).getY() - 1);


        if (this.isInUse) {
            ((PlayerEntity) entity).getPlayer().getSession().send(new WhisperMessageComposer(this.getVirtualId(), "Esta máquina está siendo usada, por favor espera.", 34));
            return false;
        }

        int bet = ((PlayerEntity) entity).getBetAmount();

        if(((PlayerEntity) entity).getPlayer().getData().getBlackMoney() < bet || bet == 0){
            ((PlayerEntity) entity).getPlayer().getSession().send(new MassEventMessageComposer("habbopages/users/slotmachine.txt?" + Comet.getTime()));
            ((PlayerEntity) entity).getPlayer().getSession().send(new NuxGiftEmailViewMessageComposer(6 + "", 0, true, false, true));
            //((PlayerEntity) entity).getPlayer().getSession().send(new WhisperMessageComposer(this.getVirtualId(), "No dispones de la cantidad que quieres apostar o tu apuesta es de 0. Ajusta tu apuesta con :setbet {CANTIDAD}", 34));
            return false;
        }

        this.isInUse = true;
        boolean isWin = false;

        // Remove currency from the bet.
        ((PlayerEntity) entity).getPlayer().getData().decreaseBlackMoney(bet);
        ((PlayerEntity) entity).getPlayer().getData().save();

        String rand1 = "";
        String rand2 = "";
        String rand3 = "";

        int random1 = RandomUtil.getRandomInt(1, 3);
        int random2 = RandomUtil.getRandomInt(1, 3);
        int random3 = RandomUtil.getRandomInt(1, 3);

        if(random2 == random3 && random1 == random3) {
            int multiplier = 0;
            isWin = true;
            String image = "";

            switch (random1) {
                case 1:
                    ((PlayerEntity) entity).getPlayer().getAchievements().progressAchievement(AchievementType.ACH_64, 1);
                    multiplier = 10;
                    image = "bet_star";
                    break;
                case 2:
                    ((PlayerEntity) entity).getPlayer().getAchievements().progressAchievement(AchievementType.ACH_30, 1);
                    multiplier = 5;
                    image = "bet_heart";
                    break;
                case 3:
                    ((PlayerEntity) entity).getPlayer().getAchievements().progressAchievement(AchievementType.ACH_63, 1);
                    multiplier = 2;
                    image = "bet_skull";
                    break;
            }

            ((PlayerEntity) entity).getPlayer().getSession().send(new NotificationMessageComposer(image, Locale.getOrDefault("", "Has ganado %q Tokens con la tragaperras.\n\n(%b x %m)")
                    .replace("%q", bet * multiplier + "")
                    .replace("%b", bet + "")
                    .replace("%m", multiplier + "")));

            ((PlayerEntity) entity).getPlayer().getAchievements().progressAchievement(AchievementType.ACH_24, 1);
            ((PlayerEntity) entity).getPlayer().getData().increaseBlackMoney(bet * multiplier);
            ((PlayerEntity) entity).getPlayer().getData().save();

        }

        rand1 = getString(rand1, random1);
        rand2 = getString(rand2, random2);
        rand3 = getString(rand3, random3);

        ((PlayerEntity) entity).getPlayer().getSession().send(new WhisperMessageComposer(this.getVirtualId(), "Has sacado " + rand1 + " " + rand2 + " " + rand3 + ".", 34));
        ((PlayerEntity) entity).getPlayer().sendBalance();

        this.setTicks(RoomItemFactory.getProcessTime(1));

        this.getItemData().setData("1");
        this.sendUpdate();

        this.saveData();

        BetDao.insertBet(((PlayerEntity) entity).getPlayer().getData().getId(), "slot_machine", ((PlayerEntity) entity).getBetAmount() + "", Comet.getTime() + "", isWin ? "win" : "share");
        return true;
    }

    private String getString(String rand3, int random3) {
        switch (random3){
            case 1: rand3="¥"; break;
            case 2: rand3="|"; break;
            case 3: rand3="ª"; break;
        }
        return rand3;
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
        this.isInUse = false;

        this.getItemData().setData("0");
        this.sendUpdate();

        this.saveData();
    }
}
