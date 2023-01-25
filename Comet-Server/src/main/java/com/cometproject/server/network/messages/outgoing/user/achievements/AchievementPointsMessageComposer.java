package com.cometproject.server.network.messages.outgoing.user.achievements;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;


public class AchievementPointsMessageComposer extends MessageComposer {
    private final int points;
    private final int level;

    public AchievementPointsMessageComposer(final int points, final int level) {
        this.points = points;
        this.level = level;
    }

    @Override
    public short getId() {
        return Composers.AchievementScoreMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(this.points);
        msg.writeInt(this.level); // level
    }
}
