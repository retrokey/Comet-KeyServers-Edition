package com.cometproject.server.network.messages.outgoing.messenger;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;


public class FriendNotificationMessageComposer extends MessageComposer {
    private final int toId;
    private final int type;
    private final String data;

    public FriendNotificationMessageComposer(final int toId, final int type, final String data) {
        this.type = type;
        this.toId = toId;
        this.data = data;
    }

    @Override
    public short getId() {
        return Composers.FriendNotificationMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeString(this.toId);
        msg.writeInt(this.type);
        msg.writeString(this.data);
            /*_-1xC[3] = "${messenger.error.receivermuted}";
            _-1xC[4] = "${messenger.error.sendermuted}";
            _-1xC[5] = "${messenger.error.offline}";
            _-1xC[6] = "${messenger.error.notfriend}";
            _-1xC[7] = "${messenger.error.busy}";
            _-1xC[8] = "${messenger.error.receiverhasnochat}";
            _-1xC[9] = "${messenger.error.senderhasnochat}";
            _-1xC[10] = "${messenger.error.offline_failed}";*/

    }
}
