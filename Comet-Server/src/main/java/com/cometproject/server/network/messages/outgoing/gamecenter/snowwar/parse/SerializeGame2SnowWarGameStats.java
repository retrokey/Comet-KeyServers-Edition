package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.games.snowwar.SnowWarRoom;

public class SerializeGame2SnowWarGameStats {
    public static void parse(IComposer msg, SnowWarRoom arena) {
        msg.writeInt(arena.MostKills.userId);
        msg.writeInt(arena.MostHits.userId);
    }
}