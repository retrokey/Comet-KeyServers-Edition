package com.cometproject.server.network.messages.incoming.room.item;

import com.cometproject.api.game.quests.QuestType;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.objects.items.RoomItemWall;
import com.cometproject.server.game.rooms.objects.items.types.wall.PostItWallItem;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.nuxs.EmailVerificationWindowMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.RemoveWallItemMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;


public class PickUpItemMessageEvent implements Event {
    public void handle(Session client, MessageEvent msg) {
        if (client == null || client.getPlayer() == null || client.getPlayer().getEntity() == null) {
            return;
        }

        if(client.getPlayer().getPermissions().getRank().modTool() && !client.getPlayer().getSettings().isPinSuccess()) {
            client.getPlayer().sendBubble("pincode", Locale.getOrDefault("pin.code.required", "Debes verificar tu PIN antes de realizar cualquier acción."));
            client.send(new EmailVerificationWindowMessageComposer(1,1));
            return;
        }

        boolean isFloorItem = msg.readInt() == 2;

        Long id = ItemManager.getInstance().getItemIdByVirtualId(msg.readInt());

        if (id == null) {
            return;
        }

        Room room = client.getPlayer().getEntity().getRoom();

        if (room == null) {
            return;
        }

        boolean eject = false;

        RoomItemFloor item = room.getItems().getFloorItem(id);

        if(item != null && this.isOwner(item, client.getPlayer().getData().getId())){
            room.getItems().removeItem(item, client);
            return;
        }

        if (!room.getRights().hasRights(client.getPlayer().getId()) && !client.getPlayer().getPermissions().getRank().roomFullControl()) {
            return;
        }

        if (item == null) {
            RoomItemWall wItem = room.getItems().getWallItem(id);

            if (wItem == null || wItem instanceof PostItWallItem) {
                return;
            }

            if (wItem.getItemData().getOwnerId() != client.getPlayer().getId() && !client.getPlayer().getPermissions().getRank().roomFullControl()) {
                if (wItem.getRoom().getData().getOwnerId() != client.getPlayer().getId() && !client.getPlayer().getPermissions().getRank().roomFullControl())
                    return;

                eject = true;
            }

            if (!eject) {
                room.getItems().removeItem(wItem, client.getPlayer().getId(), client);
            } else {
                Session owner = NetworkManager.getInstance().getSessions().getByPlayerId(wItem.getItemData().getOwnerId());
                room.getItems().removeItem(wItem, wItem.getItemData().getOwnerId(), owner);
            }

            client.send(new RemoveWallItemMessageComposer(wItem.getVirtualId(), client.getPlayer().getId()));
            return;
        }

        if (item.getItemData().getOwnerId() != client.getPlayer().getId() && !client.getPlayer().getPermissions().getRank().roomFullControl()) {
            if (item.getRoom().getData().getOwnerId() != client.getPlayer().getId() && !client.getPlayer().getPermissions().getRank().roomFullControl())
                return;

            eject = true;
        }

        item.onPickup();

        if (!eject) {
            room.getItems().removeItem(item, client);
        } else {
            Session owner = NetworkManager.getInstance().getSessions().getByPlayerId(item.getItemData().getOwnerId());
            room.getItems().removeItem(item, owner);
        }

        client.getPlayer().getQuests().progressQuest(QuestType.FURNI_PICK);
    }

    private boolean isOwner(RoomItemFloor item, int playerId){
        return item.getItemData().getOwnerId() == playerId;
    }
}
