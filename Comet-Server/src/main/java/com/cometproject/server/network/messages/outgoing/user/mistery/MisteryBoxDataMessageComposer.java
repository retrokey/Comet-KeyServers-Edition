package com.cometproject.server.network.messages.outgoing.user.mistery;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.players.types.MisteryComponent;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

import java.util.List;

public class MisteryBoxDataMessageComposer extends MessageComposer {

    private final MisteryComponent mistery;

    public MisteryBoxDataMessageComposer(final MisteryComponent mistery) {
        this.mistery = mistery;
    }

    @Override
    public short getId() {
        return Composers.MisteryBoxDataMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeString(mistery.getMisteryBox());
        msg.writeString(mistery.getMisteryKey());
    }
}
