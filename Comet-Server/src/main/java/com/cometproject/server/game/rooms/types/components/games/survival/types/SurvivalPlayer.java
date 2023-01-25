package com.cometproject.server.game.rooms.types.components.games.survival.types;
import com.cometproject.api.config.SurvivalSettings;
import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.rooms.objects.entities.effects.PlayerEffect;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.network.messages.outgoing.notification.MassEventMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.*;
import com.cometproject.server.network.messages.outgoing.room.freeze.UpdateFreezeLivesMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.google.common.collect.Lists;

import java.util.List;

public class SurvivalPlayer {
    private final Session session;
    private SurvivalPowerUp powerUp = SurvivalPowerUp.None;
    private final List<SurvivalPowerUp> powerUpList = Lists.newArrayList();
    private int lives = 100;
    private int bullets = 0;
    private int kills = 0;
    private int shield = 0;
    private int speedTime = 0;
    private int lastShootTimer = 0;

    public SurvivalPlayer(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public void powerUp(SurvivalPowerUp powerUp, SurvivalPowerUp oldPowerUp, boolean isSwap) {
        boolean needsHold = false;
        int entityId = this.session.getPlayer().getEntity().getId();
        PlayerEntity entity = this.session.getPlayer().getEntity();
        switch (powerUp) {
            case Life:
                if(this.lives > 51){
                    this.lives = 100;
                } else this.lives += 50;

                this.session.send(new WhisperMessageComposer(entityId, Locale.getOrDefault("survival.health.recieved", "You've just taken stamina from the chest! You actually have %life% HP.").replace("%life%", this.getLives() + ""), 0));
                this.session.send(new UpdateFreezeLivesMessageComposer(entityId, this.lives));
                needsHold = true;
                break;

            case Shield:
                if(this.shield < 100) {
                    this.shield = 100;
                } //else this.shield += 10;
                this.session.send(new WhisperMessageComposer(entityId, Locale.getOrDefault("survival.shield.recieved", "You've just taken shield power from the chest! You actually have %shield% PWR.").replace("%shield%", this.getShield() + ""), 0));
                needsHold = true;
                break;

            case Sniper:
                if(!isSwap) {
                    this.session.send(new WhisperMessageComposer(entityId, Locale.getOrDefault("survival.sniper.recieved", "You've just taken a sniper from the chest!").replace("%bullets%", this.getBullets() + ""), 0));
                    this.bullets += SurvivalSettings.sniperBullets;
                }

                entity.applyEffect(new PlayerEffect(587, 0));
                break;

            case Gun:
                if(!isSwap) {
                    this.session.send(new WhisperMessageComposer(entityId, Locale.getOrDefault("survival.shotgun.recieved", "You've just taken a revolver from the chest!").replace("%bullets%", this.getBullets() + ""), 0));
                    this.bullets += SurvivalSettings.gunBullets;
                }
                entity.applyEffect(new PlayerEffect(585, 0));
                break;

            case Bullets:
                this.bullets += SurvivalSettings.chestBullets;
                this.session.send(new WhisperMessageComposer(entityId, Locale.getOrDefault("survival.bullets.recieved", "You've just taken 10 bullets from the chest, you have a total of %bullets% now!").replace("%bullets%", this.getBullets() + ""), 0));
                this.session.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_104, 1);
                needsHold = true;
                break;

            case Speed:
                this.session.getPlayer().getEntity().setFastWalkEnabled(true);
                this.session.send(new WhisperMessageComposer(entityId, Locale.getOrDefault("survival.speed.recieved", "You walk faster during some seconds."), 0));
                this.setSpeedTime(SurvivalSettings.speedBoostTime);
                needsHold = true;
                break;
        }

        if(needsHold) {
            this.powerUp = oldPowerUp;
        } else {
            this.powerUp = powerUp;
            if(!this.powerUpList.contains(SurvivalPowerUp.Gun) && powerUp == SurvivalPowerUp.Gun)
                this.powerUpList.add(powerUp);

            if(!this.powerUpList.contains(SurvivalPowerUp.Sniper) && powerUp == SurvivalPowerUp.Sniper)
                this.powerUpList.add(powerUp);
        }

        String sound;

        if(powerUp == SurvivalPowerUp.Shield){
            sound = "shield";
        } else if(powerUp == SurvivalPowerUp.Life) {
            sound = "bandage";
        } else {
            sound = "br_walk";
        }

        this.session.send(new MassEventMessageComposer("habblet/open/playSound?sound="+ sound + ""));
        this.session.send(new MassEventMessageComposer("habblet/open/survivalHP?damage=0&shield=" + this.getShield() + "&health=" + this.getLives()));
        this.session.send(new MassEventMessageComposer("habblet/open/survivalPower?gun=" + (this.getPowerUpList().contains(SurvivalPowerUp.Gun) ? "yes" : "no") + "&sniper=" + (this.getPowerUpList().contains(SurvivalPowerUp.Sniper) ? "yes" : "no") + "&bandage=no"));
        this.session.send(new MassEventMessageComposer("habblet/open/survivalSync?figure=" + session.getPlayer().getData().getFigure() + "&kills=" + this.getKills() + "&bullets=" + this.getBullets()));
    }

    public PlayerEntity getEntity() {
        return this.session.getPlayer().getEntity();
    }
    public SurvivalPowerUp getPowerUp() {
        return powerUp;
    }
    public int getLives() {
        return lives;
    }
    public void setLives(int lives) {
        this.lives = lives;
    }
    public void decrementLives(int lives) {
        this.lives -= lives;
    }
    public int getBullets() {
        return bullets;
    }
    public int getShield() {
        return shield;
    }
    public void decrementShield(int shield) {
        this.shield -= shield;
    }
    public int getKills() {
        return kills;
    }
    public void incrementKills() {
        this.kills++;
    }
    public void resetShield() { this.shield = 0; }
    public void incrementBullets() {
        this.bullets += 1;
    }
    public void incrementShield(){
        if(this.shield > 51){
            this.shield = 100;
        } else this.shield = 50;
    }
    public void incrementBullets(int bullets) {
        this.bullets += bullets;
    }
    public void setBullets(int bullets) {
        this.bullets = bullets;
    }
    public int getSpeedTime() {
        return speedTime;
    }
    public int getLastShootTimer() {
        return lastShootTimer;
    }
    public void setLastShootTimer(int time) {
        this.lastShootTimer = time;
    }
    public void setSpeedTime(int survivalTimer) {
        this.speedTime = survivalTimer;
    }
    public void decrementSpeedTime() {
        this.speedTime--;
    }

    public List<SurvivalPowerUp> getPowerUpList(){
        return this.powerUpList;
    }
}
