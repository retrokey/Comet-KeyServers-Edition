package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.games.snowwar.RoomQueue;
import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse.SerializeGame2;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class GameLongDataComposer extends MessageComposer {
    private final RoomQueue lobby;

    public GameLongDataComposer(RoomQueue lobby) {
        this.lobby = lobby;
    }

    @Override
    public void compose(IComposer msg) {
        SerializeGame2.parse(msg, this.lobby);
    }

    @Override
    public short getId() {
        return Composers.GameSerializationMessageComposer;
    }
}