package com.cometproject.server.game.rooms.types.components.games;

import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.components.GameComponent;
import com.cometproject.server.tasks.CometTask;
import com.cometproject.server.tasks.CometThreadManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public abstract class RoomGame implements CometTask {
    protected int timer;
    protected int gameLength;
    protected boolean active = false;
    protected boolean finished = false;
    protected Room room;
    private GameType type;
    private ScheduledFuture future;

    private Logger LOGGER;

    public RoomGame(Room room, GameType gameType) {
        this.type = gameType;
        this.LOGGER = LoggerFactory.getLogger("RoomGame [" + room.getData().getName() + "][" + room.getData().getId() + "][" + this.type + "]");
        this.room = room;
    }

    @Override
    public void run() {
        try {
            if (timer == 0) {
                this.active = true;
                /*final List<WiredAddonBlob> blobs = room.getItems().getByClass(WiredAddonBlob.class);
                Collections.shuffle(blobs);

                for (WiredAddonBlob blob : blobs) {
                    blob.onGameStarted();
                }*/

                onGameStarts();
            }

            try {
                /*if (this.getGameComponent().getBlobCounter().get() < 2) {
                    if (RandomUtil.getRandomBool(0.1)) {
                        final List<WiredAddonBlob> blobs = room.getItems().getByClass(WiredAddonBlob.class);
                        Collections.shuffle(blobs);

                        for (WiredAddonBlob blob : blobs) {
                            blob.onGameStarted();
                        }
                    }
                }*/

                tick();
            } catch (Exception e) {
                LOGGER.error("Failed to process game tick", e);
            }

            if (timer >= gameLength) {
                onGameEnds();
                room.getGame().stop();
                this.stop();
            }

            timer++;
        } catch (Exception e) {
            LOGGER.error("Error during game process", e);
        }
    }

    public void stop() {
        /*for (WiredAddonBlob blob : room.getItems().getByClass(WiredAddonBlob.class)) {
            blob.hideBlob();
        }*/

        if (this.active && this.future != null) {
            this.future.cancel(false);

            this.active = false;
            this.gameLength = 0;
            this.timer = 0;
        }
    }

    public void startTimer(int amount) {
        if (this.active && this.future != null) {
            this.future.cancel(false);
        }

        this.future = CometThreadManager.getInstance().executePeriodic(this, 0, 1, TimeUnit.SECONDS);

        this.gameLength = amount;
        this.active = true;

        LOGGER.debug("Game active for " + amount + " seconds");
    }

    protected GameComponent getGameComponent() {
        return this.room.getGame();
    }

    public abstract void tick();

    public abstract void onGameEnds();

    public abstract void onGameStarts();

    public GameType getType() {
        return this.type;
    }

    public Logger getLog() {
        return this.LOGGER;
    }

    public boolean isActive() {
        return active;
    }
}
