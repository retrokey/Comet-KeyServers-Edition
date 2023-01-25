package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse.SerializeGame2Player;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class UserJoinedGameComposer extends MessageComposer {
    private final Session session;

    public UserJoinedGameComposer(Session session) {
        this.session = session;
    }

    @Override
    public void compose(IComposer msg) {
        SerializeGame2Player.parse(msg, this.session);
        msg.writeBoolean(false);
    }

    @Override
    public short getId() {
        return Composers.SnowStormQueuePlayerAddedComposer;
    }
}
