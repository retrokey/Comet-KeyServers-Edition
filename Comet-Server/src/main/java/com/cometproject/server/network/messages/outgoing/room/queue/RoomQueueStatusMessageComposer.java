package com.cometproject.server.network.messages.outgoing.room.queue;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class RoomQueueStatusMessageComposer extends MessageComposer {

    private final int playersWaiting;

    public RoomQueueStatusMessageComposer(int playersWaiting) {
        this.playersWaiting = playersWaiting;
    }

    @Override
    public short getId() {
        return Composers.RoomQueueStatusMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(2);
        {
            msg.writeString("visitors");
            msg.writeInt(2);

            msg.writeInt(1);
            {
                msg.writeString("visitors");
                msg.writeInt(this.playersWaiting);
            }

            msg.writeString("spectators");
            msg.writeInt(1);

            msg.writeInt(1);
            {
                msg.writeString("spectators");
                msg.writeInt(0);
            }
        }

    }
}
