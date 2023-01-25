package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.games.snowwar.SnowWar;
import com.cometproject.games.snowwar.SnowWarRoom;
import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse.SerializeGame2GameObjects;
import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse.SerializeGameStatus;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class FullGameStatusComposer extends MessageComposer {
    private final SnowWarRoom arena;

    public FullGameStatusComposer(SnowWarRoom arena) {
        this.arena = arena;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(0);
        msg.writeInt(0);
        msg.writeInt(0);
        SerializeGame2GameObjects.parse(msg, this.arena);
        msg.writeInt(0);
        msg.writeInt(SnowWar.TEAMS.length);
        SerializeGameStatus.parse(msg, this.arena, true);
    }

    @Override
    public short getId() {
        return Composers.SnowStormFullGameStatusComposer;
    }
}