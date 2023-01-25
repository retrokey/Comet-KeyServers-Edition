package com.cometproject.server.network.messages.incoming.sockets.remake.player;

import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.network.sessions.Session;
import com.google.gson.JsonObject;

public class MovementHandler {
    public MovementHandler(Session client, JsonObject msg) {
        if (client.getPlayer().getEntity() == null || client.getPlayer().getEntity().getRoom() == null)
            return;

        if (msg.get("type").getAsString() != null || !msg.get("type").getAsString().equals("")) {
            String direction = msg.get("type").getAsString();

            PlayerEntity player = client.getPlayer().getEntity();

            switch (direction){
                case "37":
                    player.moveTo(player.getPosition().getX() - 1, player.getPosition().getY());
                    break;
                case "38":
                    player.moveTo(player.getPosition().getX(), player.getPosition().getY() - 1);
                    break;
                case "39":
                    player.moveTo(player.getPosition().getX() + 1, player.getPosition().getY());
                    break;
                case "40":
                    player.moveTo(player.getPosition().getX(), player.getPosition().getY() + 1);
                    break;
            }
        }
    }
}