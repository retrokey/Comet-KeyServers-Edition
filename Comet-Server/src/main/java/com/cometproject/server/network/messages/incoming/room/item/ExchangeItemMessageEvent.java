package com.cometproject.server.network.messages.incoming.room.item;

import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;
import com.cometproject.server.storage.queries.player.PlayerDao;


public class ExchangeItemMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) {
        int virtualId = msg.readInt();

        long itemId = ItemManager.getInstance().getItemIdByVirtualId(virtualId);

        if (client.getPlayer().getEntity() == null) {
            return;
        }

        Room room = client.getPlayer().getEntity().getRoom();

        if (room == null || (!room.getRights().hasRights(client.getPlayer().getId()) && !client.getPlayer().getPermissions().getRank().roomFullControl())) {
            return;
        }


        RoomItemFloor item = room.getItems().getFloorItem(itemId);

        if (item == null) {
            return;
        }

        if (item.getItemData().getOwnerId() != client.getPlayer().getId()) {
            return;
        }

        int value;
        boolean isDiamond = false;
        boolean isDuckets = false;

        if(!item.getDefinition().getItemName().startsWith("CF_") && !item.getDefinition().getItemName().startsWith("CFC_") && !item.getDefinition().getItemName().startsWith("DIA_")) {
            return;
        }

        if (item.getDefinition().getItemName().contains("DIA_")) {
            isDiamond = true;
            value = Integer.parseInt(item.getDefinition().getItemName().split("_")[1]);
        } else if(item.getDefinition().getItemName().contains("CFC_")){
            isDuckets = true;
            value = Integer.parseInt(item.getDefinition().getItemName().split("_")[1]);
        } else {
            value = Integer.parseInt(item.getDefinition().getItemName().split("_")[1]);
        }

        room.getItems().removeItem(item, client, false, true);
        String exchangeValue;

        if (isDiamond) {
            client.getPlayer().getData().increaseVipPoints(value);
            exchangeValue = "Diamonds: " + client.getPlayer().getData().getVipPoints();
        } else if(isDuckets){
            client.getPlayer().getData().increaseActivityPoints(value);
            exchangeValue = "Duckets: " + client.getPlayer().getData().getActivityPoints();
        } else {
            client.getPlayer().getData().increaseCredits(value);
            exchangeValue = "Credits: " + client.getPlayer().getData().getCredits();
        }

        PlayerDao.saveExchangeLog(client.getPlayer().getData().getId(), itemId, item.getDefinition().getId(), exchangeValue);

        client.getPlayer().sendBalance();
        client.getPlayer().getData().save();
    }
}
