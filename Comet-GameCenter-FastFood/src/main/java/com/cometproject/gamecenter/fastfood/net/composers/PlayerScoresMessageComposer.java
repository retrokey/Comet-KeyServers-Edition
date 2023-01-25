package com.cometproject.gamecenter.fastfood.net.composers;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.gamecenter.fastfood.net.FastFoodGameSession;
import com.cometproject.server.protocol.messages.MessageComposer;

public class PlayerScoresMessageComposer extends MessageComposer {

    private final int gameSession;

    public PlayerScoresMessageComposer(final int gameSession) {
        this.gameSession = gameSession;
    }

    @Override
    public short getId() {
        return 18;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(this.gameSession);
        msg.writeInt(1);
    }
}
