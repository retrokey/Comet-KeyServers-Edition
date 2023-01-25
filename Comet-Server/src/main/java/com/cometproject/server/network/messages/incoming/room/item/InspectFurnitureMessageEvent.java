package com.cometproject.server.network.messages.incoming.room.item;

import com.cometproject.server.composers.catalog.CatalogIndexMessageComposer;
import com.cometproject.server.composers.catalog.pets.CatalogModeComposer;
import com.cometproject.server.game.catalog.CatalogManager;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class InspectFurnitureMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        String MODE = msg.readString();
        client.send(new CatalogModeComposer(MODE.equalsIgnoreCase("normal") ? 0 : 1));
        client.send(new CatalogIndexMessageComposer(CatalogManager.getInstance(), ItemManager.getInstance(), client.getPlayer().getData().getRank(), MODE));
    }
}