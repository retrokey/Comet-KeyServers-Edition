package com.cometproject.server.game.commands.vip;

import com.cometproject.api.game.players.data.components.inventory.PlayerItem;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.messages.outgoing.user.inventory.UpdateInventoryMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.storage.api.StorageContext;
import com.google.common.collect.Lists;

import java.util.List;


public class RedeemCreditsCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        int coinsToGive = 0;
        int diamondsToGive = 0;

        List<Long> itemsToRemove = Lists.newArrayList();

        if (!client.getPlayer().getInventory().itemsLoaded()) {
            sendNotif(Locale.getOrDefault("command.redeemcredits.inventory", "Please open your inventory before executing this command!"), client);
            return;
        }

        for (PlayerItem playerItem : client.getPlayer().getInventory().getInventoryItems().values()) {
            if (playerItem == null || playerItem.getDefinition() == null) continue;

            String itemName = playerItem.getDefinition().getItemName();

            if (itemName.startsWith("CF_") || itemName.startsWith("CFC_") || itemName.startsWith("DIA_")) {
                try {
                    if (itemName.contains("DIA_")) {
                        diamondsToGive += Integer.parseInt(itemName.split("_")[1]);
                    } else {
                        coinsToGive += Integer.parseInt(itemName.split("_")[1]);
                    }

                    itemsToRemove.add(playerItem.getId());

                    StorageContext.getCurrentContext().getRoomItemRepository().deleteItem(playerItem.getId());
                } catch (Exception ignored) {

                }
            }
        }

        if (itemsToRemove.size() == 0) {
            return;
        }

        for (long itemId : itemsToRemove) {
            client.getPlayer().getInventory().removeItem(itemId);
        }

        itemsToRemove.clear();

        client.send(new UpdateInventoryMessageComposer());

        if (diamondsToGive > 0) {
            client.getPlayer().getData().increaseVipPoints(diamondsToGive);
        }

        if (coinsToGive > 0) {
            client.getPlayer().getData().increaseCredits(coinsToGive);
        }

        if (diamondsToGive > 0 || coinsToGive > 0) {
            client.getPlayer().sendBalance();
            client.getPlayer().getData().save();
        }
    }


    @Override
    public String getPermission() {
        return "redeemcredits_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.redeemcredits.description");
    }
}
