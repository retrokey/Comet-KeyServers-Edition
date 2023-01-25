package com.cometproject.server.network.messages.outgoing.user.club;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.game.players.components.SubscriptionComponent;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;


public class ClubStatusMessageComposer extends MessageComposer {
    private final SubscriptionComponent subscriptionComponent;

    public ClubStatusMessageComposer(final SubscriptionComponent subscriptionComponent) {
        this.subscriptionComponent = subscriptionComponent;
    }

    @Override
    public short getId() {
        return Composers.ScrSendUserInfoMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        int timeLeft = 0;
        int days = 0;
        int months = 0;

        if (subscriptionComponent.isValid()) {
            timeLeft = subscriptionComponent.getExpire() - (int) Comet.getTime();
            days = (int) Math.ceil(timeLeft / 86400);
            months = days / 31;
        } else {
            if (subscriptionComponent.exists()) {
                subscriptionComponent.delete();
            }
        }

        msg.writeString("habbo_club");

        msg.writeInt(subscriptionComponent.isValid() ? days : 1);
        msg.writeInt(2);
        msg.writeInt(subscriptionComponent.isValid() ? months : 1);
        msg.writeInt(1);
        msg.writeBoolean(subscriptionComponent.isValid());
        msg.writeBoolean(true);
        msg.writeInt(0);
        msg.writeInt(subscriptionComponent.isValid() ? days : 1);
        msg.writeInt(subscriptionComponent.isValid() ? days : 1);
    }
}
