package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.games.snowwar.SnowWar;
import com.cometproject.games.snowwar.SnowWarRoom;
import com.cometproject.games.snowwar.gameobjects.HumanGameObject;
import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse.SerializeArena;
import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse.SerializeGame2PlayerData;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class EnterArenaComposer extends MessageComposer {
    private final SnowWarRoom arena;

    public EnterArenaComposer(SnowWarRoom room) {
        this.arena = room;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(0);
        msg.writeInt(this.arena.ArenaType.ArenaType);
        msg.writeInt(SnowWar.TEAMS.length);
        msg.writeInt(this.arena.players.size());
        for (HumanGameObject Player : this.arena.players.values()) {
            SerializeGame2PlayerData.parse(msg, Player);
        }
        SerializeArena.parse(msg, this.arena);
    }

    public short getId() {
        return Composers.SnowEnterArenaMessageComposer;
    }
}