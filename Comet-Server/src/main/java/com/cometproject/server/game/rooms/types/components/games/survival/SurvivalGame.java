package com.cometproject.server.game.rooms.types.components.games.survival;

import com.cometproject.api.config.SurvivalSettings;
import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.rooms.objects.entities.effects.PlayerEffect;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.types.floor.survival.MunitionBoxFloorItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.survival.SurvivalBlockFloorItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.survival.SurvivalExitFloorItem;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.components.games.GameType;
import com.cometproject.server.game.rooms.types.components.games.RoomGame;
import com.cometproject.server.game.rooms.types.components.games.survival.types.SurvivalPlayer;
import com.cometproject.server.game.rooms.types.components.games.survival.types.SurvivalPowerUp;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.MassEventMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.*;
import com.cometproject.server.network.messages.outgoing.room.freeze.UpdateFreezeLivesMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.permissions.YouArePlayingGameMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.google.common.collect.Maps;
import java.util.Map;

public class SurvivalGame extends RoomGame {
    private int variableDamage = 0;
    private int lastSize;
    private final Map<Integer, SurvivalPlayer> players = Maps.newConcurrentMap();

    public SurvivalGame(Room room) {
        super(room, GameType.SURVIVAL);
    }

    @Override
    public void tick() {
        boolean needsRecheck = false;

        for (SurvivalPlayer survivalPlayer : this.players.values()) {
            if(survivalPlayer == null)
                continue;

            if (survivalPlayer.getSpeedTime() > 0) {
                survivalPlayer.decrementSpeedTime();
            }

            if(this.lastSize > this.players.size()) {
                syncGame(survivalPlayer.getSession(), "remaining", "");
                needsRecheck = true;
            }

            if(survivalPlayer.getSpeedTime() <= 1){
                survivalPlayer.getEntity().setFastWalkEnabled(false);
                survivalPlayer.setSpeedTime(0);
            }
        }

        if(needsRecheck){
            this.lastSize = this.players.size();
        }
    }

    public void playerLost(SurvivalPlayer survivalPlayer) {
        final SurvivalExitFloorItem exitItem = this.getExitTile();
        if(exitItem != null) {
            survivalPlayer.getEntity().teleportToItem(exitItem);
        }

        syncGame(survivalPlayer.getSession(), "stop", "reset");
        survivalPlayer.getPowerUpList().clear();

        this.players.remove(survivalPlayer.getEntity().getPlayerId());
        int playersRemaining = this.players.size();

        if(playersRemaining == 1){
            this.gameComplete();
        }
    }

    public void playerLeaves(int playerId, boolean isDisconnect) {
        Session session = NetworkManager.getInstance().getSessions().getByPlayerId(playerId);

        if(session != null){
            syncGame(session, "stop", "reset");
            syncGame(session, "show_ui", "");
        }

        System.out.print("Player " + playerId + " left a Survival Game.\n");
        this.players.remove(playerId);
        int playersRemaining = this.players.size();

        if(playersRemaining == 1){
            for(SurvivalPlayer player : this.players.values()){
                if(player.getSession() != null)
                player.getSession().getPlayer().getAchievements().progressAchievement(AchievementType.ACH_128, 1);
            }
            this.gameComplete();
        }
    }

    private SurvivalExitFloorItem getExitTile() {
        for (SurvivalExitFloorItem exitItem : this.room.getItems().getByClass(SurvivalExitFloorItem.class)) {
            return exitItem;
        }

        return null;
    }

    public void gameComplete() {
        for (PlayerEntity playerEntity : this.room.getEntities().getPlayerEntities()) {
            if(playerEntity == null || playerEntity.getPlayer() == null || playerEntity.getPlayer().getSession() == null)
                continue;

            syncGame(playerEntity.getPlayer().getSession(), "stop", "reset");
            syncGame(playerEntity.getPlayer().getSession(), "show_ui", "reset");
            playerEntity.setSurvivalMode(false);
            playerEntity.setFastWalkEnabled(false);
            playerEntity.setCanWalk(true);
            playerEntity.getPlayer().getSession().send(new YouArePlayingGameMessageComposer(false));
        }

        this.onGameEnds();
    }


    public void playerShots(SurvivalPlayer player, SurvivalPlayer enemy) {
        if(player == null || enemy == null || player == enemy)
            return;

        if(player.getPowerUp() == SurvivalPowerUp.None)
            return;

        if(player.getBullets() <= 0) {

            player.getEntity().getPlayer().sendNotif(Locale.getOrDefault("survival.bullet.icon", "survival_bullet"), Locale.getOrDefault("survival.missing.bullets", "No tienes munición, abre cofres y consigue balas."));
            player.getSession().send(new MassEventMessageComposer("habblet/open/playSound?sound=no_amo"));
            return;
        }

        if(((int) Comet.getTime()) < player.getLastShootTimer())
            return;

        player.setBullets(player.getBullets() - 1);

        player.getEntity().cancelWalk();

        syncGame(player.getSession(), "general", "");

        Position enemyPos = new Position(enemy.getEntity().getPosition().getX(), enemy.getEntity().getPosition().getY(), enemy.getEntity().getPosition().getZ());

        // TODO: Validate distance from void.
        // TODO: 3 ACH types depending on the kill type.
        switch (player.getPowerUp()){
            case Gun:
                if(!isProperDistance(player, enemy, enemyPos, SurvivalSettings.gunDistance, "gun")){
                    return;
                }

                variableDamage = SurvivalSettings.gunDamage;
                validateDamage(variableDamage, enemy);
                player.setLastShootTimer((int)Comet.getTime() + SurvivalSettings.gunCooldown);
                player.getSession().send(new MassEventMessageComposer("habblet/open/playSound?sound=gun_shot"));

                break;
            case Sniper:
                if(!isProperDistance(player, enemy, enemyPos, SurvivalSettings.sniperDistance, "sniper")){
                    return;
                }

                variableDamage = SurvivalSettings.sniperDamage;
                validateDamage(variableDamage, enemy);
                player.setLastShootTimer((int)Comet.getTime() + SurvivalSettings.sniperCooldown);
                player.getSession().send(new MassEventMessageComposer("habblet/open/playSound?sound=sniper"));

                break;

            case None:
                if(!isProperDistance(player, enemy, enemyPos, SurvivalSettings.meleeDistance, "melee")){
                    return;
                }

                variableDamage = SurvivalSettings.meleeDamage;
                validateDamage(variableDamage, enemy);
                player.setLastShootTimer((int)Comet.getTime() + SurvivalSettings.meleeCooldown);
                player.getSession().send(new MassEventMessageComposer("habblet/open/playSound?sound=sniper"));
                break;
        }

        if(enemy.getLives() <= 0){
            if(players.size() == 2){
                player.getEntity().getPlayer().getAchievements().progressAchievement(AchievementType.ACH_128, 1);
            }

            enemy.getSession().send(new MassEventMessageComposer("habblet/open/playSound?sound=br_dead"));

            player.getEntity().getPlayer().getAchievements().progressAchievement(AchievementType.ACH_106, 1);

            player.getEntity().getPlayer().sendBubble(Locale.getOrDefault("survival.kill.icon", "survival_kill"), Locale.getOrDefault("survival.kill.message", "¡Has asesinado a " + enemy.getEntity().getUsername() + "! Recibes %bullets% balas por el asesinato" + (SurvivalSettings.shieldPerKill ? " y recuperas parte de tu escudo." : ".")).replace("%user%", enemy.getEntity().getUsername()).replace("%bullets%", SurvivalSettings.bulletsPerKill + ""));
            //TODO: Handle PowerUps by name and tell the nemesis weapon.

            enemy.getEntity().getPlayer().sendBubble(Locale.getOrDefault("survival.death.icon", "survival_kill"), Locale.getOrDefault("survival.death.message", "¡" + player.getEntity().getUsername() + " acaba de matarte!\n\nThe North remembers.").replace("%user%", player.getEntity().getUsername()));
            enemy.getEntity().applyEffect(new PlayerEffect(602, 2));
            player.incrementKills();
            player.incrementBullets(SurvivalSettings.bulletsPerKill);

            if(SurvivalSettings.shieldPerKill) {
                player.incrementShield();
                syncGame(player.getSession(), "shield", "0");
            }

            syncGame(player.getSession(), "general", "");
            playerLost(enemy);
            return;
        }

        enemy.getEntity().getPlayer().getSession().send(new UpdateFreezeLivesMessageComposer(enemy.getEntity().getId(), enemy.getLives()));

        if(enemy.getShield() > 0) {
            enemy.getEntity().getPlayer().getSession().send(new WhisperMessageComposer(-1, Locale.getOrDefault("survival.shield.message", "<b>%enemy%</b> - <i>te disparó</i>. [<b>%damage%</b>] daño recibido. [<b>%shield%</b>] Puntos de Escudo Restantes\"").replace("%shield%", enemy.getShield() + "").replace("%enemy%", player.getEntity().getUsername()).replace("%damage%", variableDamage + ""), 1));
            player.getEntity().getPlayer().getSession().send(new WhisperMessageComposer(-1, Locale.getOrDefault("survival.shoot.shield", "[<b>%damage%</b>] daño inflingido a <b>%enemy%</b> <i>(tiene escudo)</i>. Balas: <b>%bullets%</b>.").replace("%bullets%", player.getBullets() + "").replace("%enemy%", enemy.getEntity().getUsername()).replace("%damage%", variableDamage + ""), 1));
            syncGame(enemy.getSession(), "shield", variableDamage + "");
            return;
        }

        player.getEntity().getPlayer().getSession().send(new WhisperMessageComposer(-1, Locale.getOrDefault("survival.shoot", "[<b>%damage%</b>] daño inflingido a <b>%enemy%</b>. Balas: <b>%bullets%</b>.").replace("%bullets%", player.getBullets() + "").replace("%enemy%", enemy.getEntity().getUsername()).replace("%damage%", variableDamage + ""), 1));
        enemy.getEntity().getPlayer().getSession().send(new WhisperMessageComposer(-1, Locale.getOrDefault("survival.live.message", "<b>%enemy%</b> - <i>te disparó</i>. [<b>%damage%</b>] daño recibido. [<b>%life%</b>] Puntos de Vida Restantes").replace("%life%", enemy.getLives() + "").replace("%enemy%", player.getEntity().getUsername()).replace("%damage%", variableDamage + ""), 1));
        syncGame(enemy.getSession(), "health", variableDamage + "");
    }

    private boolean isProperDistance(SurvivalPlayer player, SurvivalPlayer enemy, Position enemyPosition, int distance, String gun){
        if(enemyPosition.distanceTo(player.getEntity().getPosition()) > distance) {
            player.getEntity().getPlayer().getSession().send(new TalkMessageComposer(-1, Locale.getOrDefault("survival.distance." + gun, "Debes estar " + distance + " casillas cerca de " + enemy.getEntity().getUsername() + " in order to shot at him with the sniper.").replace("%user%",enemy.getEntity().getUsername()).replace("%distance%", distance + ""), ChatEmotion.NONE, 1));
            return false;
        }
        return true;
    }

    private void validateDamage(int damage, SurvivalPlayer enemy){
        enemy.getSession().send(new MassEventMessageComposer("habblet/open/playSound?sound=br_hit"));

        if(enemy.getShield() == 0){
            enemy.decrementLives(damage);
            return;
        } else if(enemy.getShield() > 0) {
            enemy.decrementShield(damage);
        } if(enemy.getShield() < 0)
            enemy.resetShield();
    }

    @Override
    public void onGameStarts() {
        for (PlayerEntity playerEntity : this.room.getEntities().getPlayerEntities()) {
            this.players.put(playerEntity.getPlayer().getData().getId(), new SurvivalPlayer(playerEntity.getPlayer().getSession()));
            syncGame(playerEntity.getPlayer().getSession(), "general", "");
            syncGame(playerEntity.getPlayer().getSession(), "hide_ui", "");
            syncGame(playerEntity.getPlayer().getSession(), "health", "");
            playerEntity.setSurvivalMode(true);
            playerEntity.getPlayer().getSession().send(new YouArePlayingGameMessageComposer(true));
        }

        for (SurvivalBlockFloorItem blockItem : this.room.getItems().getByClass(SurvivalBlockFloorItem.class)) {
            blockItem.reset();
        }

        for (MunitionBoxFloorItem munitionItem : this.room.getItems().getByClass(MunitionBoxFloorItem.class)) {
            munitionItem.reset();
        }

        for (SurvivalExitFloorItem exitItem : this.room.getItems().getByClass(SurvivalExitFloorItem.class)) {
            exitItem.getItemData().setData("1");
            exitItem.sendUpdate();
        }

        for(SurvivalPlayer player : this.players.values()){
            syncGame(player.getSession(), "remaining", "");
        }

        this.lastSize = this.players.size();
    }

    private void syncGame(Session session, String syncType, String extraValue) {
        if(session == null || session.getPlayer() == null || session.getPlayer().getEntity() == null || session.getPlayer().getData() == null || this.players == null) {
            System.out.print("Survival Sync failed for a player.\n");
            return;
        }

        SurvivalPlayer survivalPlayer = this.players.get(session.getPlayer().getData().getId());

        if(survivalPlayer == null)
            return;

        String packet = "";

        switch (syncType){
            case "general":
                packet = "survivalSync?figure=" + session.getPlayer().getData().getFigure() + "&kills=" + survivalPlayer.getKills() + "&bullets=" + survivalPlayer.getBullets() + "&remaining=" + this.players.size();
                break;
            case "remaining":
                packet = "survivalPlayers?remaining=" + this.players.size();
                break;
            case "health":
                packet = "survivalHP?damage=" + extraValue + "&shield=" + survivalPlayer.getShield() + "&health=" + survivalPlayer.getLives();
                break;
            case "shield":
                packet = "survivalShield?damage=" + extraValue + "&shield=" + survivalPlayer.getShield() + "&health=" + survivalPlayer.getLives();
                break;
            case "stop":
                packet = "survivalStop?none=" + "1s";
                break;
            case "hide_ui":
                packet = "survivalHide?none=" + "1s";
                break;
            case "show_ui":
                packet = "survivalShow?none=" + "1s";
                break;
            case "weapon-sync":
                packet = "survivalPower?gun=" + (survivalPlayer.getPowerUpList().contains(SurvivalPowerUp.Gun) ? "yes" : "no") + "&sniper=" + (survivalPlayer.getPowerUpList().contains(SurvivalPowerUp.Sniper) ? "yes" : "no") + "&bandage=no";
                break;
        }

        session.send(new MassEventMessageComposer("habblet/open/" + packet));
    }

    public SurvivalPlayer survivalPlayer(final int playerId) {
        return this.players.get(playerId);
    }

    @Override
    public void onGameEnds() {
        for (SurvivalBlockFloorItem blockItem : this.room.getItems().getByClass(SurvivalBlockFloorItem.class)) {
            blockItem.reset();
        }

        for (PlayerEntity playerEntity : this.room.getEntities().getPlayerEntities()) {
            if(playerEntity == null)
                continue;

            playerEntity.applyEffect(new PlayerEffect(0));
        }

        this.players.clear();
        this.room.getGame().stop();
    }
}