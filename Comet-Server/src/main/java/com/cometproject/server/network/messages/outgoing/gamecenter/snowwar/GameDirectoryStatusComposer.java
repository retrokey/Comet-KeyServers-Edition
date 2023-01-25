package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class GameDirectoryStatusComposer extends MessageComposer {
    public static final int ENABLED = 0;
    public static final int UNKNOW1 = 1;
    public static final int UNKNOW2 = 2;
    public static final int UNKNOW3 = 3;
    private final int state;
    private final int timeTillNextGame;
    private final int userId;

    public GameDirectoryStatusComposer(int state, int timeTillNextGame, int userId) {
        this.state = state;
        this.timeTillNextGame = timeTillNextGame;
        this.userId = userId;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(this.state);
        msg.writeInt(this.timeTillNextGame);
        msg.writeInt(this.userId);
        msg.writeInt(-1);
    }

    @Override
    public short getId() {
        return Composers.SnowStormGamesInformationComposer;
    }
}
