package com.cometproject.server.game.players.login.queue;

import com.cometproject.server.tasks.CometThreadManager;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class PlayerLoginQueueManager {
    private static final int WAIT_TIME = 100;

    private final PlayerLoginQueue loginQueue;
    private ScheduledFuture future;

    public PlayerLoginQueueManager(boolean autoStart, CometThreadManager threadMgr) {
        this.loginQueue = new PlayerLoginQueue();
        if (autoStart) {
            this.start(threadMgr);
        }
    }

    private void start(CometThreadManager threadMgr) {
        if (this.future != null) {
            return;
        }
        this.future = threadMgr.executePeriodic(this.loginQueue, WAIT_TIME, WAIT_TIME, TimeUnit.MILLISECONDS);
    }

    public boolean queue(PlayerLoginQueueEntry entry) {
        return this.loginQueue.queue(entry);
    }
}
