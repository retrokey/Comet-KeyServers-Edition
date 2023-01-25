package com.cometproject.server.network.messages.incoming.crafting;

import com.cometproject.server.game.items.crafting.CraftingMachine;
import com.cometproject.server.game.items.crafting.CraftingRecipe;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.crafting.CraftableProductsToGetResultMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class ViewCraftingRecipeMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        CraftingMachine machine = client.getPlayer().getLastCraftingMachine();
        if(machine == null) { return; }

        final String result = msg.readString();

        for(CraftingRecipe recipeX :  machine.getPublicRecipes()){
            if(recipeX.getResultProductData().equals(result)) {
                client.send(new CraftableProductsToGetResultMessageComposer(recipeX));
                return;
            }
        }
    }
}
