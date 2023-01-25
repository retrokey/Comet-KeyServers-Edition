package com.cometproject.server.composers.group;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.messages.MessageComposer;
import com.cometproject.server.protocol.headers.Composers;

public class GroupEditErrorMessageComposer extends MessageComposer {
    private final int errorId;

    public GroupEditErrorMessageComposer(final int errorId) {
        this.errorId = errorId;
    }

    /**
     * 1 - NONE
     * 2 - VIP Required
     * 3 - Hospital ACCEPT
     * 4 - Hospital DECLINE
     * 5,6,7,8 - Weapons
     * 9 - Taser
     *
     */

    @Override
    public short getId() {
        return Composers.GroupEditErrorMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(this.errorId);
    }
}
