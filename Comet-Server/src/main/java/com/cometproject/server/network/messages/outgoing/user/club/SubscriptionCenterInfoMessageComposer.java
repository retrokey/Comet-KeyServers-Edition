package com.cometproject.server.network.messages.outgoing.user.club;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.game.players.components.SubscriptionComponent;
import com.cometproject.server.protocol.messages.MessageComposer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SubscriptionCenterInfoMessageComposer
        extends MessageComposer {
    private final SubscriptionComponent subscriptionComponent;

    public SubscriptionCenterInfoMessageComposer(SubscriptionComponent subscriptionComponent) {
        this.subscriptionComponent = subscriptionComponent;
    }

    public short getId() {
        return 3277;
    }

    public void compose(IComposer msg) {
        int timeLeft = this.subscriptionComponent.getExpire() - (int)Comet.getTime();
        long timeLeftLong = (this.subscriptionComponent.getExpire() - (int)Comet.getTime()) * 1000L;
        int days = timeLeft / 86400;
        msg.writeInt(days);
        msg.writeString(new SimpleDateFormat("dd-MM-yyyy").format(new Date((long)this.subscriptionComponent.getStart() * 1000L)));
        msg.writeDouble(5.0);
        msg.writeInt(0);
        msg.writeInt(0);
        msg.writeInt(0);
        msg.writeInt(0);
        msg.writeInt(0);
        msg.writeInt(0);
        msg.writeInt(0);
    }
}
