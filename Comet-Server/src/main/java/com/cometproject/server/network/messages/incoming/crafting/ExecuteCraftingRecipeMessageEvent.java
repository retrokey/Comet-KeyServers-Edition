package com.cometproject.server.network.messages.incoming.crafting;

import com.cometproject.api.game.furniture.types.FurnitureDefinition;
import com.cometproject.api.game.players.IPlayer;
import com.cometproject.api.game.players.data.components.inventory.PlayerItem;
import com.cometproject.api.game.rooms.entities.PlayerRoomEntity;
import com.cometproject.server.composers.catalog.UnseenItemsMessageComposer;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.items.crafting.CraftingMachine;
import com.cometproject.server.game.items.crafting.CraftingRecipe;
import com.cometproject.server.game.players.components.types.inventory.InventoryItem;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.crafting.CraftingFinalResultMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.inventory.UpdateInventoryMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;
import com.cometproject.server.storage.queries.rooms.RoomItemDao;
import com.cometproject.storage.api.StorageContext;
import com.cometproject.storage.api.data.Data;
import com.google.common.collect.Sets;

public class ExecuteCraftingRecipeMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        CraftingMachine machine = client.getPlayer().getLastCraftingMachine();
        if(machine == null) { return; }

        final int machineId = msg.readInt();
        final String result = msg.readString();
        CraftingRecipe recipe = machine.getRecipeByProductData(result);

        if(recipe == null) { return; }

        for(Integer elementId : recipe.getComponents()) {
            for(PlayerItem item : client.getPlayer().getInventory().getInventoryItems().values()) {
                if(item.getBaseId() == elementId) {
                    client.getPlayer().getInventory().removeItem(item.getId());
                    RoomItemDao.deleteItem(item.getId());
                    break;
                }
            }
        }

        int nIb = recipe.getResultBaseId();
        FurnitureDefinition itemDefinition = ItemManager.getInstance().getDefinition(nIb);
        IPlayer e = client.getPlayer();

        if (itemDefinition != null) {
            final Data<Long> newItem = Data.createEmpty();
            StorageContext.getCurrentContext().getRoomItemRepository().createItem(e.getData().getId(), nIb, "", newItem::set);

            PlayerItem playerItem = new InventoryItem(newItem.get(), nIb, "");

            client.getPlayer().getInventory().addItem(playerItem);

            e.getSession().send(new UpdateInventoryMessageComposer());
            e.getSession().send(new UnseenItemsMessageComposer(Sets.newHashSet(playerItem), ItemManager.getInstance()));
        }

        if(recipe.getAchievement() != null) { client.getPlayer().getAchievements().progressAchievement(recipe.getAchievement(), 1); }
        if(recipe.getBadge() != null) { client.getPlayer().getInventory().addBadge(recipe.getBadge(), true); }

        client.send(new CraftingFinalResultMessageComposer(true, recipe));
    }
}
