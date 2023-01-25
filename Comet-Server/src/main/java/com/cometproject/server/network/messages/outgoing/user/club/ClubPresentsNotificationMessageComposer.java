package com.cometproject.server.network.messages.outgoing.user.club;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;


public class ClubPresentsNotificationMessageComposer extends MessageComposer {
    private final int notificationType;

    public ClubPresentsNotificationMessageComposer(final int notificationType){
        this.notificationType = notificationType;
    }
    @Override
    public short getId() {
        return Composers.ClubPresentsNotificationMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(this.notificationType);
    }
}
