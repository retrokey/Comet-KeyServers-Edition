package com.cometproject.server.network.messages.outgoing.user.purse;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class UpdateActivityPointsMessageComposer extends MessageComposer {

    private int activityPoints;
    private int change;
    private int type;

    public UpdateActivityPointsMessageComposer(int activityPoints, int change, int type) {
        this.activityPoints = activityPoints;
        this.change = change;
        this.type = type;
    }

    @Override
    public short getId() {
        return Composers.HabboActivityPointNotificationMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(this.activityPoints);
        msg.writeInt(this.change);
        msg.writeInt(this.type);
    }
}
