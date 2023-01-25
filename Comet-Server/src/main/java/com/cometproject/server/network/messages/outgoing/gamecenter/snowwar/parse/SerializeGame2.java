package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse;
import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.games.snowwar.RoomQueue;
import com.cometproject.games.snowwar.SnowWar;
import com.cometproject.server.network.sessions.Session;

public class SerializeGame2 {
    public static void parse(IComposer msg, RoomQueue queue) {
        msg.writeInt(queue.room.roomId);
        msg.writeString(queue.room.Name);
        msg.writeInt(0);
        msg.writeInt(queue.room.ArenaType.ArenaType);
        msg.writeInt(SnowWar.TEAMS.length);
        msg.writeInt(10);
        msg.writeString(queue.room.Owner);
        msg.writeInt(14);
        msg.writeInt(queue.players.size());
        for (Session cn : queue.players.values()) {
            SerializeGame2Player.parse(msg, cn);
        }
    }
}