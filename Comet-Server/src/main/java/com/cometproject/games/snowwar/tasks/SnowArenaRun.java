package com.cometproject.games.snowwar.tasks;

import com.cometproject.games.snowwar.MessageWriter;
import com.cometproject.games.snowwar.SnowWarRoom;
import com.cometproject.games.snowwar.gameobjects.GameItemObject;
import com.cometproject.games.snowwar.gameobjects.HumanGameObject;
import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.FullGameStatusComposer;
import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.GameStatusComposer;
import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.StageEndingComposer;
import io.netty.channel.ChannelHandlerContext;
import java.util.ArrayList;
import java.util.List;

public class SnowArenaRun {
    public static void exec(SnowWarRoom room) {
        List<ChannelHandlerContext> filter;
        MessageWriter writer;
        if (room.players.isEmpty()) {
            room.STATUS = 0;
            return;
        }

        synchronized (room.gameEvents) {
            synchronized (room.fullGameStatusQueue) {
                filter = room.fullGameStatusQueue;
                room.fullGameStatusQueue = new ArrayList();
            }
            room.checksum = 0;
            for (GameItemObject gameItemObject : room.gameObjects.values()) {
                gameItemObject.GenerateCHECKSUM(room, 1);
            }
            for (ChannelHandlerContext socket : filter) {
                socket.writeAndFlush(new FullGameStatusComposer(room));
            }
            writer = GameStatusComposer.compose(room);
            room.gameEvents.clear();
        }
        for (HumanGameObject player : room.players.values()) {
            if (player.currentSnowWar == null || !filter.isEmpty() && filter.contains(player.cn.getChannel())) {
                continue;
            }
            try {
                player.cn.getChannel().writeAndFlush(writer.getMessage(0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        room.subturn();
        room.subturn();
        room.subturn();

        if (++room.Turn >= 800) {
            room.STATUS = 6;
            room.broadcast(new StageEndingComposer());
        }
    }
}