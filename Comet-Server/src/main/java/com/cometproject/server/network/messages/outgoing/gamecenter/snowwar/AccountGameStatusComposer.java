package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar;
import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class AccountGameStatusComposer extends MessageComposer {
    private final int gameTypeId;

    public AccountGameStatusComposer(int gameTypeId) {
        this.gameTypeId = gameTypeId;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(this.gameTypeId);
        msg.writeInt(-1);
        msg.writeInt(0);
    }

    @Override
    public short getId() {
        return Composers.GameAccountStatusMessageComposer;
    }
}