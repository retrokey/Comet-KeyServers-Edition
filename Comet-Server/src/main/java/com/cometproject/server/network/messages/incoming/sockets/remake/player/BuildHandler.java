package com.cometproject.server.network.messages.incoming.sockets.remake.player;

import com.cometproject.server.network.sessions.Session;
import com.google.gson.JsonObject;

public class BuildHandler {
    public BuildHandler(Session client, JsonObject msg) {
        if (client.getPlayer().getEntity() == null || client.getPlayer().getEntity().getRoom() == null)
            return;

        if (msg.get("type").getAsString() != null || !msg.get("type").getAsString().equals("") || msg.get("value").getAsString() != null || !msg.get("value").getAsString().equals("")) {
            String type = msg.get("type").getAsString();
            String value = msg.get("value").getAsString();

            switch (type){
                case "rotation":
                    client.getPlayer().setItemPlacementRotation(Integer.parseInt(value));
                    break;
                case "hauteur":
                    client.getPlayer().setItemPlacementHeight(Double.parseDouble(value));
                    break;
                case "etat":
                    client.getPlayer().setItemPlacementState(Integer.parseInt(value));
                    break;
            }
        }
    }

    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}