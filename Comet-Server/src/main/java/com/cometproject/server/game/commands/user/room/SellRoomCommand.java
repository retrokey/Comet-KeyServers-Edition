package com.cometproject.server.game.commands.user.room;

import com.cometproject.api.game.quests.QuestType;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.objects.entities.effects.PlayerEffect;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.storage.api.data.rooms.RoomData;

import java.text.NumberFormat;

public class SellRoomCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if(params.length == 0){
            SellRoomCommand.sendNotif("Uso del comando incorrecto. :vendersala cantidad", client);
            return;
        }

        Room room = client.getPlayer().getEntity().getRoom();
        if(params[0] == "cancelar" || params[0].contains("cancelar")){
            if(room.getData().getOwnerId() != client.getPlayer().getId()){
                SellRoomCommand.sendNotif("Necesitas ser el dueño de esta sala para ejecutar este comando.", client);
                return;
            }
            RoomData roomData = (RoomData) room.getData();
            if(roomData.isOnSale){
                roomData.roomPrice = 0;
                roomData.isOnSale = false;

                SellRoomCommand.sendNotif("Has cancelado la venta de esta sala.", client);
                String messagePlayer = "Se ha cancelado la venta de esta sala.";
                client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new WhisperMessageComposer(client.getPlayer().getEntity().getId(), messagePlayer, 5));
            }
            else SellRoomCommand.sendNotif("Esta sala no está en venta.", client);
        }

        if(room.getData().getOwnerId() == client.getPlayer().getId()){
            try{
                RoomData roomData = (RoomData) room.getData();
                int price = Integer.parseInt(params[0]);
                if(price > 0 && price < 9999){
                    roomData.roomPrice = price;
                    roomData.isOnSale = true;

                    SellRoomCommand.sendNotif("Sala en venta. Para cancelar usa :vendersala cancelar", client);
                    String messagePlayer = "Esta sala se encuentra en venta. Precio: " + price + " asteroides. Usa :comprarsala si quieres comprarla.";
                    client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new WhisperMessageComposer(client.getPlayer().getEntity().getId(), messagePlayer, 5));
                }
                else SellRoomCommand.sendNotif("La cantidad debe ser entre 0 y 9999.", client);
            }catch (NumberFormatException e){
                SellRoomCommand.sendNotif("Cantidad incorrecta.", client);
            }
        }
        else SellRoomCommand.sendNotif("Necesitas ser el dueño de esta sala para ejecutar este comando.", client);
    }

    @Override
    public String getPermission() {
        return "sellroom_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault(null, "precio");
    }

    @Override
    public String getDescription() {
        return Locale.get("command.sellroom.description");
    }
}

