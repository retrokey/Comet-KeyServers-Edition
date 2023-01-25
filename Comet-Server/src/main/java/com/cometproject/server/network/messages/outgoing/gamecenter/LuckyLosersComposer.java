package com.cometproject.server.network.messages.outgoing.gamecenter;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class LuckyLosersComposer extends MessageComposer {
    private int gameId;

    public LuckyLosersComposer(int gameId) {
        this.gameId = gameId;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(gameId);
        msg.writeInt(4);
        msg.writeString("Avatar2");//Username
        msg.writeString("ch-235-1408.hd-3095-14.lg-3116-85-1408.sh-3115-1408-1408.ca-1805-64.ha-1002-1408");//Figure
        msg.writeString("M");//Gender .ToLower()
        msg.writeInt(1);//Rank
        msg.writeInt(1555);//Score

        msg.writeString("Avatar2");//Username
        msg.writeString("ch-235-1408.hd-3095-14.lg-3116-85-1408.sh-3115-1408-1408.ca-1805-64.ha-1002-1408");//Figure
        msg.writeString("M");//Gender .ToLower()
        msg.writeInt(2);//Rank
        msg.writeInt(1555);//Score

        msg.writeString("Avatar2");//Username
        msg.writeString("ch-235-1408.hd-3095-14.lg-3116-85-1408.sh-3115-1408-1408.ca-1805-64.ha-1002-1408");//Figure
        msg.writeString("M");//Gender .ToLower()
        msg.writeInt(3);//Rank
        msg.writeInt(434343);//Score

        msg.writeString("Avatar2");//Username
        msg.writeString("ch-235-1408.hd-3095-14.lg-3116-85-1408.sh-3115-1408-1408.ca-1805-64.ha-1002-1408");//Figure
        msg.writeString("M");//Gender .ToLower()
        msg.writeInt(-1);//Rank
        msg.writeInt(152255);//Score
    }

    @Override
    public short getId() {
        return Composers.LuckyLosersComposer;
    }
}
