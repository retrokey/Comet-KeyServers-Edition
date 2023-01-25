package com.cometproject.server.network.messages.incoming.crafting;

import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.crafting.CraftableProductsMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class GetCraftingItemMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        int itemId = msg.readInt();

        client.getPlayer().getSession().send(new CraftableProductsMessageComposer(client.getPlayer().getLastCraftingMachine()));
    }
}
