/*    */ package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar;
/*    */ 
import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.games.snowwar.gameobjects.HumanGameObject;
import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse.SerializeGame2PlayerData;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class ArenaEnteredComposer extends MessageComposer {
    private final HumanGameObject player;

    public ArenaEnteredComposer(HumanGameObject player) {
        this.player = player;
    }

    @Override
    public void compose(IComposer msg) {
        SerializeGame2PlayerData.parse(msg, this.player);
    }

    @Override
    public short getId() {
        return Composers.SnowArenaEnteredMessageComposer;
    }
}