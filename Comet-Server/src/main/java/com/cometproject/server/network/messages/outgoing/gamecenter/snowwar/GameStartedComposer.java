package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.games.snowwar.RoomQueue;
import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse.SerializeGame2;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class GameStartedComposer extends MessageComposer {
private final RoomQueue queue;

public GameStartedComposer(RoomQueue queue) {
    this.queue = queue;
}

@Override
public void compose(IComposer msg) {
/* 27 */     SerializeGame2.parse(msg, this.queue);
/*    */   }

@Override
public short getId() {
/* 32 */     return Composers.SnowStormGameStartedComposer;
/*    */   }
}