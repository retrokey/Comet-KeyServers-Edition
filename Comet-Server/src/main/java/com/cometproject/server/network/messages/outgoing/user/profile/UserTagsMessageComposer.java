package com.cometproject.server.network.messages.outgoing.user.profile;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

import java.util.List;

public class UserTagsMessageComposer extends MessageComposer {
    private final int playerId;
    private final List<String> tags;

    public UserTagsMessageComposer(final int playerId, final List<String> tags) {
        this.playerId = playerId;
        this.tags = tags;
    }

    @Override
    public short getId() {
        return Composers.UserTagsMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(playerId);
        msg.writeInt(tags.size());

        tags.forEach((tag) -> {
            msg.writeString(tag);
        });
    }
}
