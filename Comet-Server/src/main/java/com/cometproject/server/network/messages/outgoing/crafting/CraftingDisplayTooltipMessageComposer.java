package com.cometproject.server.network.messages.outgoing.crafting;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.messages.MessageComposer;
import com.cometproject.server.protocol.headers.Composers;

public class CraftingDisplayTooltipMessageComposer extends MessageComposer {
    private final int alertId;

    public CraftingDisplayTooltipMessageComposer(int alertId) {
        this.alertId = alertId;
    }

    @Override
    public short getId() {
        return Composers.CraftingDisplayTooltipMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(this.alertId);
    }
}
