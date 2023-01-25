package com.cometproject.server.network.messages.incoming.room.item;

import com.cometproject.api.game.catalog.types.ICatalogItem;
import com.cometproject.api.game.catalog.types.ICatalogPage;
import com.cometproject.api.game.furniture.types.FurnitureDefinition;
import com.cometproject.api.game.quests.QuestType;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.catalog.CatalogManager;
import com.cometproject.server.game.catalog.types.CatalogItem;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.rooms.objects.entities.pathfinding.AffectedTile;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.triggers.WiredTriggerStateChanged;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.mapping.RoomTile;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.notification.AdvancedAlertMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.nuxs.EmailVerificationWindowMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.engine.UpdateStackMapMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

import java.util.ArrayList;
import java.util.List;


public class ChangeFloorItemStateMessageEvent implements Event {
    public void handle(Session client, MessageEvent msg) {
        if(client.getPlayer().getPermissions().getRank().modTool() && !client.getPlayer().getSettings().isPinSuccess()) {
            client.getPlayer().sendBubble("pincode", Locale.getOrDefault("pin.code.required", "Debes verificar tu PIN antes de realizar cualquier acción."));
            client.send(new EmailVerificationWindowMessageComposer(1,1));
            return;
        }

        int virtualId = msg.readInt();

        Long itemId = ItemManager.getInstance().getItemIdByVirtualId(virtualId);

        if (itemId == null) {
            return;
        }

        if (client.getPlayer().getEntity() == null || client.getPlayer().getEntity().getRoom() == null) {
            return;
        }

        if (!client.getPlayer().getEntity().isVisible()) {
            return;
        }

        Room room = client.getPlayer().getEntity().getRoom();

        RoomItemFloor item = room.getItems().getFloorItem(itemId);

        if (item == null) {
            return;
        }


        if(client.getPlayer().isDeveloping()){
            FurnitureDefinition itemData = item.getDefinition();
            ICatalogItem catalogItem = CatalogManager.getInstance().getCatalogItemByItemId(itemData.getId());
            ICatalogPage page = CatalogManager.getInstance().getPage(catalogItem == null ? 0 : catalogItem.getPageId());


            final String devInfo = "<b>Información del objeto base:</b>\r\r" +
            "<b>ID Base:</b> " + itemData.getId() + "\r" + "<b>Nombre:</b>" + itemData.getItemName() + "\r" +
            "<b>Sprite ID:</b> " + itemData.getSpriteId() + "\r" +
            "<b>Interacción:</b> " + itemData.getInteraction() + "\r" +
            "<b>Cycle count:</b> " + itemData.getInteractionCycleCount() + "\r\r" +
            "<b>Información del producto en catálogo:</b>\r\r" +
            "<b>ID Catálogo:</b> " + (catalogItem == null ? "ID no encontrada." : catalogItem.getId()) + "\r" +
            "<b>Nombre:</b> " + (catalogItem == null ? "Nombre no encontrado." : catalogItem.getDisplayName()) + "\r" +
            "<b>ID Página:</b> " + (page == null ? "Página no encontrada." : page.getId());

            client.send(new AdvancedAlertMessageComposer("Información de " + itemData.getItemName(), devInfo, "Abrir página del catálogo", "event:catalog/open/" + (page == null ? "" : page.getLinkName()), ""));
            return;
        }

        client.getPlayer().getQuests().progressQuest(QuestType.EXPLORE_FIND_ITEM, item.getDefinition().getSpriteId());



        if (item.onInteract(client.getPlayer().getEntity(), msg.readInt(), false)) {
            List<Position> tilesToUpdate = new ArrayList<>();
            tilesToUpdate.add(new Position(item.getPosition().getX(), item.getPosition().getY(), 0d));

            for (AffectedTile tile : AffectedTile.getAffectedTilesAt(item.getDefinition().getLength(), item.getDefinition().getWidth(), item.getPosition().getX(), item.getPosition().getY(), item.getRotation())) {
                room.getEntities().getEntitiesAt(new Position(tile.x, tile.y));
                tilesToUpdate.add(new Position(tile.x, tile.y, 0d));
            }

            for (Position tileToUpdate : tilesToUpdate) {
                RoomTile tile = room.getMapping().getTile(tileToUpdate);

                if (tile != null) {
                    tile.reload();

                    room.getEntities().broadcastMessage(new UpdateStackMapMessageComposer(tile));
                }
            }
        }

        WiredTriggerStateChanged.executeTriggers(client.getPlayer().getEntity(), item);
    }
}
