package com.cometproject.server.network.messages.outgoing.crafting;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.items.crafting.CraftingRecipe;
import com.cometproject.server.protocol.messages.MessageComposer;
import com.cometproject.server.protocol.headers.Composers;

public class CraftingFinalResultMessageComposer extends MessageComposer {
    private final boolean achieved;
    private final CraftingRecipe craftingRecipe;

    public CraftingFinalResultMessageComposer(boolean achieved, CraftingRecipe recipe) {
        this.achieved = achieved;
        this.craftingRecipe = recipe;
    }

    @Override
    public short getId() {
        return Composers.CraftingFinalResultMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeBoolean(achieved);
        msg.writeString(craftingRecipe.getResultProductData());
        msg.writeString(craftingRecipe.getResultProductData());
    }
}
