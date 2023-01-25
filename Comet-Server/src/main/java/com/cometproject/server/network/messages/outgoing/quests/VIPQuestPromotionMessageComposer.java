package com.cometproject.server.network.messages.outgoing.quests;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class VIPQuestPromotionMessageComposer extends MessageComposer {
    @Override
    public short getId() {
        return Composers.VIPQuestPromotionMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
    }
}
