package com.cometproject.server.network.sessions;

import java.util.concurrent.atomic.AtomicInteger;

public class SessionAccessLog {
    private final AtomicInteger counter = new AtomicInteger(1);
    private long lastConnection = System.currentTimeMillis();

    public void incrementCounter() {
        this.counter.incrementAndGet();
        this.lastConnection = System.currentTimeMillis();
    }

    public void resetCounter() {
        this.counter.set(0);
        this.lastConnection = 0;
    }

    public boolean isSuspicious() {
        return this.counter.get() >= 1000 && ((System.currentTimeMillis() - this.lastConnection) < 2000); // Value 1000 TESTED and wont down
    }
}
