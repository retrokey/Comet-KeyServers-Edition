package com.cometproject.server.network.messages.outgoing.crafting;

import com.cometproject.api.game.furniture.types.FurnitureDefinition;
import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.items.crafting.CraftingRecipe;
import com.cometproject.server.protocol.messages.MessageComposer;
import com.cometproject.server.protocol.headers.Composers;
import java.util.*;

public class CraftableProductsToGetResultMessageComposer extends MessageComposer {
    private final CraftingRecipe recipe;

    public CraftableProductsToGetResultMessageComposer(CraftingRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public short getId() {
        return Composers.CraftableProductsToGetResultMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        Set<Integer> uniqueSet = new HashSet<>(this.recipe.getComponents());
        msg.writeInt(uniqueSet.size());

        for (Integer temp : uniqueSet) {
            FurnitureDefinition item = ItemManager.getInstance().getByBaseId(temp);
            msg.writeInt(Collections.frequency(this.recipe.getComponents(), temp));
            msg.writeString(item.getItemName());
        }
    }
}