package com.cometproject.gamecenter.fastfood.objects;

import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.gamecenter.fastfood.FastFoodGame;
import com.cometproject.gamecenter.fastfood.net.FastFoodGameSession;
import com.cometproject.gamecenter.fastfood.net.composers.ApplyShieldMessageComposer;
import com.cometproject.gamecenter.fastfood.net.composers.DropFoodMessageComposer;
import com.cometproject.gamecenter.fastfood.net.composers.FoodUpdateMessageComposer;
import com.cometproject.gamecenter.fastfood.net.composers.UseBigParachuteMessageComposer;

public class FoodPlate {
    private final int objectId;
    private final int playerId;
    private float location;
    private float speed;
    private float parachuteSpeed;
    private int state;
    private long timeDropped = System.currentTimeMillis();
    private long timeOpened;
    private long dropTime = System.currentTimeMillis() + 100000000;

    private boolean finalized = false;
    private boolean openedParachute = false;
    private boolean hasShield = false;

    /***
     *
     * STATES:
     * 1 - Normal Drop
     * 2 - Parachute
     * 3 - Add Star
     * 4 - Broken
     * 5 - Hidden
     */


    public FoodPlate(int objectId, int playerId) {
        this.objectId = objectId;
        this.playerId = playerId;
        this.location = 1.0f;
        this.speed = 0.25f;
        this.parachuteSpeed = 0.1f;
        this.state = 1;
    }

    public void tick(FastFoodGameSession gameSession, FastFoodGame game) {
        // (System.currentTimeMillis() - this.timeDropped) >= (this.state == 2 ? 4500 : 2500)
        //System.out.print("FoodPlate - ID: " + this.getObjectId() + " - State: " + this.getState() + " - Location: " + this.location + " - Finalized: " + finalized + "\n");
        if(!this.openedParachute && (System.currentTimeMillis() - this.timeDropped) >= 1800 && !this.finalized){
            if(this.hasShield){
                this.state = 3;
                game.broadcast(new FoodUpdateMessageComposer(this.playerId, this.getObjectId(), this.state, 0, 0));
                this.finalized = true;
                return;
            }

            this.state = 4;
            game.broadcast(new FoodUpdateMessageComposer(this.playerId, this.getObjectId(), this.state, 0, 0));
            this.finalized = true;
            return;
        }

        if(System.currentTimeMillis() >= this.dropTime && !this.finalized) {
            if(this.state == 2) { // we have a parachute
                this.state = 3;
                game.broadcast(new FoodUpdateMessageComposer(this.playerId, this.getObjectId(), this.state, 0, 0));
            }

            if(this.state == 3) {
                this.state = 6;
                this.finalized = true;
                game.broadcast(new FoodUpdateMessageComposer(this.playerId, this.getObjectId(), this.state, -1, 1));

                /*final int objectId = game.getCounter().getAndIncrement();
                final FoodPlate foodPlate = new FoodPlate(objectId, gameSession.getPlayerId());

                gameSession.setCurrentPlate(foodPlate);
                game.broadcast(new DropFoodMessageComposer(objectId, foodPlate, true));
                return;*/
            } else{
                this.state = 4;
                game.broadcast(new FoodUpdateMessageComposer(this.playerId, this.getObjectId(), this.state, 0, 0));
            }

            //this.hidePlate(game);
            this.finalized = true;
        }
    }

    public void openParachute(FastFoodGame game) {
        this.state = 2;
        this.openedParachute = true;

        this.timeOpened = System.currentTimeMillis();
        long difference = this.timeOpened - this.timeDropped;

        // DISTANCIA SIN PARACAÍDAS
        this.location = this.location - ((float)(difference / 1000) * (this.speed)); // calculate location
        this.speed = this.parachuteSpeed;

        this.dropTime = (((long)((this.location / this.speed) / 3.1) * 1000) + this.timeOpened);

        //System.out.print("\nLOCATION ON DROP:" + this.location + " - DropTime : " + this.dropTime + " DIFF DROP: " + difference + " \n");

        game.broadcast(new FoodUpdateMessageComposer(this.getPlayerId(), this.getObjectId(), this.getState(), -1, 0));
    }

    public void applyShield(FastFoodGame game) {
        this.hasShield = true;
        game.broadcast(new ApplyShieldMessageComposer(this.getPlayerId(), this.getObjectId(), true));
    }

    public void hidePlate(FastFoodGame game){
        game.broadcast(new FoodUpdateMessageComposer(this.getPlayerId(), this.getObjectId(), 5, 0, 0));
    }

    public int getObjectId() {
        return this.objectId;
    }

    public float getLocation() {
        return this.location;
    }

    public void setLocation(float location) {
        this.location = location;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public long getTimeOpened(){
        return this.timeOpened;
    }
    public boolean isFinalized() {
        return this.finalized;
    }

    public enum plateType{
        Pizza("1"),
        Soap("2"),
        Glass("3"),
        Cake("4"),
        Cake1("5"),
        Cake2("6");

        private String groupName;

        plateType(String groupName) {
            this.groupName = groupName;
        }

        public String getGroupName() {
            return groupName;
        }

        public static plateType getTypeByName(String name) {
            for (plateType type : plateType.values()) {
                if (type.groupName.equals(name)) {
                    return type;
                }
            }

            return null;
        }
    }
}
