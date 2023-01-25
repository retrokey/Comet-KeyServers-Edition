package com.cometproject.server.network.messages.incoming.room.item;

import com.cometproject.api.game.quests.QuestType;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.nuxs.EmailVerificationWindowMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.UpdateFloorItemMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class MoveFloorItemMessageEvent implements Event {
    private static final Logger LOGGER = LoggerFactory.getLogger(MoveFloorItemMessageEvent.class);

    public void handle(Session client, MessageEvent msg) {
        if(client.getPlayer().getPermissions().getRank().modTool() && !client.getPlayer().getSettings().isPinSuccess()) {
            client.getPlayer().sendBubble("pincode", Locale.getOrDefault("pin.code.required", "Debes verificar tu PIN antes de realizar cualquier acción."));
            client.send(new EmailVerificationWindowMessageComposer(1,1));
            return;
        }

        Long id = ItemManager.getInstance().getItemIdByVirtualId(msg.readInt());

        if (id == null) {
            return;
        }

        int x = msg.readInt();
        int y = msg.readInt();
        int rot = msg.readInt();

        if (client.getPlayer().getEntity() == null) return;

        Room room = client.getPlayer().getEntity().getRoom();

        if (room == null) return;

        if (!client.getPlayer().getEntity().getRoom().getRights().hasRights(client.getPlayer().getId())
                && !client.getPlayer().getPermissions().getRank().roomFullControl() && !client.getPlayer().getRentable().hasRent()) {
            return;
        }

        RoomItemFloor floorItem = room.getItems().getFloorItem(id);

        if (floorItem != null) {
            if (rot != floorItem.getRotation()) {
                client.getPlayer().getQuests().progressQuest(QuestType.FURNI_ROTATE);
            }

            client.getPlayer().getQuests().progressQuest(QuestType.FURNI_MOVE);
        }

        try {
            if (room.getItems().moveFloorItem(id, new Position(x, y), rot, true, true, client.getPlayer(), client.getPlayer().getRentable().hasRent())) {
                if (floorItem != null && floorItem.getTile().getItems().size() > 1) {
                    client.getPlayer().getQuests().progressQuest(QuestType.FURNI_STACK);
                }
            } else {
                Map<String, String> notificationParams = Maps.newHashMap();

                notificationParams.put("message", "${room.error.cant_set_item}");

                client.send(new NotificationMessageComposer("furni_placement_error", notificationParams));
            }

            if (floorItem != null) {
                room.getEntities().broadcastMessage(new UpdateFloorItemMessageComposer(floorItem));
            }
        } catch (Exception e) {
            LOGGER.error("Error whilst changing floor item position!", e);
        }
    }
}