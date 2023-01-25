package com.cometproject.server.game.commands.user.room;

import com.cometproject.api.game.GameContext;
import com.cometproject.api.game.quests.QuestType;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.players.types.Player;
import com.cometproject.server.game.rooms.RoomManager;
import com.cometproject.server.game.rooms.objects.entities.effects.PlayerEffect;
import com.cometproject.server.game.rooms.objects.items.RoomItem;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.RoomReloadListener;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.engine.RoomForwardMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.storage.queries.items.ItemDao;
import com.cometproject.server.storage.queries.rooms.RoomDao;
import com.cometproject.storage.api.data.rooms.RoomData;

import java.text.NumberFormat;
import java.util.ArrayList;

public class BuyRoomCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        Room room = client.getPlayer().getEntity().getRoom();
        RoomData roomData = (RoomData) room.getData();

        if(roomData.isOnSale){
            if(room.getData().getOwnerId() != client.getPlayer().getId()){
                if(client.getPlayer().getData().getActivityPoints() > roomData.roomPrice){
                    Session roomSeller = NetworkManager.getInstance().getSessions().getByPlayerId(roomData.getOwnerId());
                    if (roomSeller == null) {
                        BuyRoomCommand.sendNotif(Locale.getOrDefault("command.user.offline", "\u00a1El usuario no est\u00e1 en l\u00ednea!"), client);
                        return;
                    }

                    client.getPlayer().getData().decreaseActivityPoints(roomData.roomPrice);
                    client.getPlayer().sendBalance();
                    roomSeller.getPlayer().getData().increaseActivityPoints(roomData.roomPrice);
                    roomSeller.getPlayer().sendBalance();

                    room.getData().setOwnerId(client.getPlayer().getId());
                    room.getData().setOwner(client.getPlayer().getEntity().getUsername());

                    GameContext.getCurrent().getRoomService().saveRoomData(client.getPlayer().getEntity().getRoom().getData());
                    client.getPlayer().getData().save();
                    roomSeller.getPlayer().getData().save();

                    ArrayList<RoomItem> itemsToChange = new ArrayList<RoomItem>();

                    itemsToChange.addAll(room.getItems().getFloorItems().values());
                    itemsToChange.addAll(room.getItems().getWallItems().values());

                    for(RoomItem item : itemsToChange)
                        ItemDao.updateOwnerItem(item.getId(), client.getPlayer().getId());

                    RoomDao.updateOnwerRoom(room.getId(), client.getPlayer().getId(), client.getPlayer().getEntity().getUsername());

                    RoomManager.getInstance().loadRoomsForUser(client.getPlayer());
                    RoomManager.getInstance().loadRoomsForUser(roomSeller.getPlayer());

                    RoomReloadListener reloadListener = new RoomReloadListener(room, (players, newRoom) -> {
                        for (Player player : players) {
                            if (player.getEntity() != null) continue;
                            player.getSession().send(new NotificationMessageComposer("furni_placement_error", Locale.getOrDefault("command.unload.roomReloaded", "The room was reloaded.")));
                            player.getSession().send(new RoomForwardMessageComposer(newRoom.getId()));
                        }
                    });
                    RoomManager.getInstance().addReloadListener(client.getPlayer().getEntity().getRoom().getId(), reloadListener);
                    room.reload();

                    BuyRoomCommand.sendNotif("Has comprado la sala por " + roomData.roomPrice + " asteroides.", client);
                    BuyRoomCommand.sendNotif("Has vendido la sala por " + roomData.roomPrice + " asteroides.", roomSeller);

                    roomData.roomPrice = 0;
                    roomData.isOnSale = false;
                }
                else BuyRoomCommand.sendNotif("No tienes suficiente dinero para comprar esta sala.", client);
            }
            else BuyRoomCommand.sendNotif("No puedes comprar tu propia sala.", client);
        }
        else BuyRoomCommand.sendNotif("Esta sala no está en venta.", client);
    }

    @Override
    public String getPermission() {
        return "buyroom_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault(null, "");
    }

    @Override
    public String getDescription() {
        return Locale.get("command.buyroom.description");
    }
}

