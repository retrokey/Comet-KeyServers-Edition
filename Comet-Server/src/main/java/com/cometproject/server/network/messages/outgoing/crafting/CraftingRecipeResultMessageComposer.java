package com.cometproject.server.network.messages.outgoing.crafting;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.messages.MessageComposer;
import com.cometproject.server.protocol.headers.Composers;

public class CraftingRecipeResultMessageComposer extends MessageComposer {
    private final int recipes;
    private final boolean found;

    public CraftingRecipeResultMessageComposer(Integer recipes, boolean found) {
        this.recipes = recipes;
        this.found = found;
    }

    @Override
    public short getId() {
        return Composers.CraftingRecipeResultMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(recipes);
        msg.writeBoolean(found);
    }
}
