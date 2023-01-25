package com.cometproject.server.network.messages.incoming.room.item.stickies;

import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.api.game.players.data.components.inventory.PlayerItem;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class PlacePostItMessageEvent implements Event {

    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        if (client.getPlayer() == null || client.getPlayer().getEntity() == null) {
            return;
        }

        final long currentTimeMs = System.currentTimeMillis();
        final long timeSinceLastUpdate = currentTimeMs - client.getPlayer().getLastPostItPlaced();

        if(timeSinceLastUpdate < (client.getPlayer().getData().getRank() > 6 ? 0 : 15000)){
            client.getPlayer().sendBubble("post_it", Locale.getOrDefault("post.it.cooldown", "Debes esperar 15 segundos para volver a colocar una nota."));
            return;
        }

        int virtualId = msg.readInt();

        long itemId = ItemManager.getInstance().getItemIdByVirtualId(virtualId);

        String position = Position.validateWallPosition(msg.readString());

        if (!client.getPlayer().getEntity().getRoom().getRights().hasRights(client.getPlayer().getId())
                && !client.getPlayer().getPermissions().getRank().roomFullControl()) {
            return;
        }

        if (position == null) {
            return;
        }

        PlayerItem item = client.getPlayer().getInventory().getItem(itemId);

        if (item == null) {
            return;
        }

        Session roomOwner = NetworkManager.getInstance().getSessions().getByPlayerId(client.getPlayer().getEntity().getRoom().getData().getOwnerId());

        if(roomOwner != null && roomOwner.getPlayer().getId() != client.getPlayer().getId()) {
            roomOwner.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_73, 1);
        }

        client.getPlayer().getEntity().getRoom().getItems().placeWallItem(item, position, client.getPlayer());
        client.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_74, 1);

        client.getPlayer().setLastPostItPlaced(System.currentTimeMillis());
    }
}
