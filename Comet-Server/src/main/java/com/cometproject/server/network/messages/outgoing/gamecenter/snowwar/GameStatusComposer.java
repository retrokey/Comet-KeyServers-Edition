package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.games.snowwar.ComposerShit;
import com.cometproject.games.snowwar.MessageWriter;
import com.cometproject.games.snowwar.SnowWarRoom;
import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse.SerializeGameStatus;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class GameStatusComposer extends MessageComposer {
    private final SnowWarRoom arena;

    public static MessageWriter compose(SnowWarRoom arena) {
        MessageWriter ClientMessage = new MessageWriter(100 + arena.gameEvents.size() * 50);
        ComposerShit.initPacket(Composers.SnowGameStatusMessageComposer, ClientMessage);
        SerializeGameStatus.parseNew(ClientMessage, arena, false);
        ComposerShit.endPacket(ClientMessage);
        return ClientMessage;
    }

    public GameStatusComposer(SnowWarRoom arena) {
        this.arena = arena;
    }

    @Override
    public void compose(IComposer msg) {
        SerializeGameStatus.parse(msg, this.arena, false);
    }

    @Override
    public short getId() {
        return Composers.SnowGameStatusMessageComposer;
    }
}