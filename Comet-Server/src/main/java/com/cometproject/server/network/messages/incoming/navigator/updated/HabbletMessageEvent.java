package com.cometproject.server.network.messages.incoming.navigator.updated;

import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.incoming.sockets.minigames.MinigameHandler;
import com.cometproject.server.network.messages.incoming.sockets.player.*;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;
import com.google.gson.JsonObject;
import org.apache.commons.codec.binary.Base64;
import java.util.regex.Pattern;

public class HabbletMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        if(client.getPlayer().antiSpam(this.getClass().getName(), 0.5))
            return;

        final String data = msg.readString();


        if (data.equals("")) {
            return;
        }

        try {
            byte[] byteArray = Base64.decodeBase64(data.getBytes());
            String decodedString = new String(byteArray);

            String[] message = decodedString.split(Pattern.quote("?"), -1);
            if (message.length > 1) {
                String[] params = message[1].split(Pattern.quote("&"), -1);
                String[] param;

                JsonObject jsonMessage = new JsonObject();
                for (String s : params) {
                    param = s.split(Pattern.quote("="), -1);
                    if (param.length > 1) {
                        jsonMessage.addProperty(param[0], param[1]);
                    }
                }

                switch (jsonMessage.get("id").getAsString()) {
                    case "builderSync":
                        new BuildHandler(client, jsonMessage);
                        break;
                    case "acceptMinigame":
                        new MinigameHandler(client, jsonMessage);
                        break;
                    case "asyncroMove":
                        new MovementHandler(client, jsonMessage);
                        break;
                }
            }
        } catch (Exception ignored) {
        }

    }
}