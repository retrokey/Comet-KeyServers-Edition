package com.cometproject.games.snowwar.data;

import com.cometproject.games.data.GamePlayerData;
import com.cometproject.games.snowwar.SnowPlayerQueue;
import com.cometproject.games.snowwar.SnowWarRoom;
import com.cometproject.games.snowwar.Tile;
import com.cometproject.games.snowwar.gameevents.BallThrowToHuman;
import com.cometproject.games.snowwar.gameevents.BallThrowToPosition;
import com.cometproject.games.snowwar.gameevents.CreateSnowBall;
import com.cometproject.games.snowwar.gameevents.MakeSnowBall;
import com.cometproject.games.snowwar.gameevents.UserMove;
import com.cometproject.games.snowwar.gameobjects.GameItemObject;
import com.cometproject.games.snowwar.gameobjects.HumanGameObject;
import com.cometproject.games.snowwar.gameobjects.SnowBallGameObject;
import com.cometproject.server.game.players.types.Player;

public class SnowWarPlayerData extends GamePlayerData {
public Player player;
public SnowWarRoom currentSnowWar;
public HumanGameObject humanObject;
public int snowLevel;
public int PointsNeed;

    public SnowWarPlayerData(Player playerData) {
        this.player = playerData;
        this.snowLevel = 1;
        this.rank = 1;
        this.score = 10;
        this.PointsNeed = 100;
    }

    public void setHumanObject(HumanGameObject humanGameObject) {
        if (humanGameObject == null) {
            this.humanObject.snowWarPlayer = null;
            this.humanObject.cn = null;
            this.humanObject.userId = 0;
            this.humanObject = null;
        } else {
            this.humanObject = humanGameObject;
            this.humanObject.snowWarPlayer = this;
            this.humanObject.cn = this.player.getSession();
            this.humanObject.userId = this.player.getData().getId();
            this.humanObject.userName = this.player.getData().getUsername();
            this.humanObject.look = this.player.getData().getFigure();
            this.humanObject.motto = this.player.getData().getMotto();
            this.humanObject.sex = this.player.getData().getGender().toUpperCase();
        }
    }

    public void setRoom(SnowWarRoom room) {
        this.currentSnowWar = room;
    }

    public void userLeft() {
        if (this.humanObject != null && this.currentSnowWar != null) {
            SnowPlayerQueue.playerExit(this.currentSnowWar, this.humanObject);

        }

    }

    public void makeSnowBall() {
        synchronized (this.currentSnowWar.gameEvents) {
            this.currentSnowWar.gameEvents.add(new MakeSnowBall(this.humanObject));

        }

    }

    public void playerMove(int x, int y) {
        synchronized (this.currentSnowWar.gameEvents) {
            this.currentSnowWar.gameEvents.add(new UserMove(this.humanObject, x, y));
        }
    }

    public void throwSnowballFlood(int destX, int destY) {
        SnowBallGameObject ball = new SnowBallGameObject(this.currentSnowWar);
        ball.objectId = this.currentSnowWar.objectIdCounter++;
        SnowBallGameObject ball1 = new SnowBallGameObject(this.currentSnowWar);
        ball1.objectId = this.currentSnowWar.objectIdCounter++;
        SnowBallGameObject ball2 = new SnowBallGameObject(this.currentSnowWar);
        ball2.objectId = this.currentSnowWar.objectIdCounter++;
        SnowBallGameObject ball3 = new SnowBallGameObject(this.currentSnowWar);
        ball3.objectId = this.currentSnowWar.objectIdCounter++;
        SnowBallGameObject ball4 = new SnowBallGameObject(this.currentSnowWar);
        ball4.objectId = this.currentSnowWar.objectIdCounter++;
        SnowBallGameObject ball5 = new SnowBallGameObject(this.currentSnowWar);
        ball5.objectId = this.currentSnowWar.objectIdCounter++;
        SnowBallGameObject ball6 = new SnowBallGameObject(this.currentSnowWar);
        ball6.objectId = this.currentSnowWar.objectIdCounter++;

        synchronized (this.currentSnowWar.gameEvents) {
            this.currentSnowWar.gameEvents.add(new BallThrowToPosition(this.humanObject, destX * Tile.TILE_SIZE, destY * Tile.TILE_SIZE, 3));
            this.currentSnowWar.gameEvents.add(new CreateSnowBall(ball, this.humanObject, destX * Tile.TILE_SIZE, destY * Tile.TILE_SIZE, 3));
            this.currentSnowWar.gameEvents.add(new CreateSnowBall(ball4, this.humanObject, destX * Tile.TILE_SIZE, (destY + 1) * Tile.TILE_SIZE, 3));
            this.currentSnowWar.gameEvents.add(new CreateSnowBall(ball1, this.humanObject, (destX + 1) * Tile.TILE_SIZE, destY * Tile.TILE_SIZE, 3));
            this.currentSnowWar.gameEvents.add(new CreateSnowBall(ball6, this.humanObject, (destX - 1) * Tile.TILE_SIZE, (destY + 1) * Tile.TILE_SIZE, 3));
            this.currentSnowWar.gameEvents.add(new CreateSnowBall(ball2, this.humanObject, (destX - 1) * Tile.TILE_SIZE, (destY - 1) * Tile.TILE_SIZE, 3));
            this.currentSnowWar.gameEvents.add(new CreateSnowBall(ball3, this.humanObject, (destX + 1) * Tile.TILE_SIZE, (destY - 1) * Tile.TILE_SIZE, 3));
            this.currentSnowWar.gameEvents.add(new CreateSnowBall(ball5, this.humanObject, (destX + 1) * Tile.TILE_SIZE, (destY + 1) * Tile.TILE_SIZE, 3));

        }

    }

    public void throwSnowballAtHuman(int victim, int type) {
        if (!this.humanObject.canThrowSnowBall()) {
            return;
        }

        GameItemObject vict = this.currentSnowWar.gameObjects.get(victim);

        if (vict == null) {
            return;
        }

        SnowBallGameObject ball = new SnowBallGameObject(this.currentSnowWar);

        ball.objectId = this.currentSnowWar.objectIdCounter++;

        synchronized (this.currentSnowWar.gameEvents) {
            this.currentSnowWar.gameEvents.add(new CreateSnowBall(ball, this.humanObject, vict.location3D().x(), vict.location3D().y(), type));
            this.currentSnowWar.gameEvents.add(new BallThrowToHuman(this.humanObject, (HumanGameObject) vict, 0));
        }
    }

    public void throwSnowballAtPosition(int destX, int destY, int type) {
        if (!this.humanObject.canThrowSnowBall()) {
            return;
        }

        SnowBallGameObject ball = new SnowBallGameObject(this.currentSnowWar);

        ball.objectId = this.currentSnowWar.objectIdCounter++;

        synchronized (this.currentSnowWar.gameEvents) {
            this.currentSnowWar.gameEvents.add(new CreateSnowBall(ball, this.humanObject, destX, destY, type));
            this.currentSnowWar.gameEvents.add(new BallThrowToPosition(this.humanObject, destX, destY, 0));
        }
    }
}
