package com.cometproject.server.network.messages.outgoing.user.club;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.game.players.components.SubscriptionComponent;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;
import java.util.Calendar;

public class ClubDataMessageComposer
        extends MessageComposer {
    private final SubscriptionComponent subscriptionComponent;
    private final int windowId;

    public ClubDataMessageComposer(SubscriptionComponent subscriptionComponent, int windowId) {
        this.subscriptionComponent = subscriptionComponent;
        this.windowId = windowId;
    }

    public short getId() {
        return Composers.ClubDataMessageComposer;
    }

    public void compose(IComposer msg) {
        long seconds;
        msg.writeInt(1);
        msg.writeInt(0);
        msg.writeString("DEAL_VIP_1");
        msg.writeBoolean(Boolean.FALSE);
        msg.writeInt(0);
        msg.writeInt(240);
        msg.writeInt(5);
        msg.writeBoolean(Boolean.TRUE);
        long secondsTotal = seconds = 2678400L;
        int totalYears = (int)Math.floor((int)seconds / 86400 * 31 * 12);
        int totalMonths = (int)Math.floor((int)(seconds -= (long)(totalYears * 86400 * 31 * 12)) / 86400 * 31);
        int totalDays = (int)Math.floor((int)(seconds -= (long)(totalMonths * 86400 * 31)) / 86400);
        msg.writeInt((int)secondsTotal / 86400 / 31);
        msg.writeInt((int)(seconds -= (long)(totalDays * 86400L)));
        msg.writeBoolean(Boolean.FALSE);
        msg.writeInt((int)seconds);
        int endTimestamp = this.subscriptionComponent.getExpire();
        if (endTimestamp < (int)Comet.getTime()) {
            endTimestamp = (int)Comet.getTime();
        }
        endTimestamp = (int)((long)endTimestamp + secondsTotal);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis((long)endTimestamp * 1000L);
        msg.writeInt(cal.get(1));
        msg.writeInt(cal.get(2) + 1);
        msg.writeInt(cal.get(5));
        msg.writeInt(this.windowId);
    }
}
