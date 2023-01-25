package com.cometproject.server.network.messages.outgoing.landing;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class BonusBagMessageComposer extends MessageComposer {

    private final int xp;
    private final int level;
    private String reward = "";
    private final int spriteId;
    private int flush;

    public BonusBagMessageComposer(String config, int xp, int level) {
        String[] c = config.split(",");
        this.reward = c[0];
        this.spriteId = Integer.parseInt(c[1]);
        this.xp = xp;
        this.level = level;
    }


    @Override
    public short getId() {
        return Composers.BonusBagMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        int coefficient = 1500 * this.level;
        int resting = coefficient - this.xp;
        msg.writeString(this.reward);
        msg.writeInt(this.spriteId);
        msg.writeInt(coefficient);
        msg.writeInt(Math.max(resting, 0));
    }
}
