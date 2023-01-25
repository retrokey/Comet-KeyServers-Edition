package com.cometproject.server.network.messages.outgoing.user.newyear;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class NewYearResolutionMessageComposer extends MessageComposer {
    private final int winStreak;
    public NewYearResolutionMessageComposer(int winStreak){
        this.winStreak = winStreak;
    }
    @Override
    public short getId() {
        return Composers.NewYearResolutionMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(230); // time
        msg.writeInt(40); // size to foreach

        for(int i = 0; i< 41; i++) {
            msg.writeInt(i); // ID
            msg.writeInt(this.winStreak); // ???
            msg.writeString(i + "BET"); // Name
            msg.writeInt(0); // ???
            msg.writeInt(0); // ???
        }

        msg.writeInt(1000); // ???
    }
}
