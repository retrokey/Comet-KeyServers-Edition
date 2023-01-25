package com.cometproject.server.network.messages.incoming.catalog.pets;

import com.cometproject.api.networking.messages.IMessageComposer;
import com.cometproject.server.composers.catalog.pets.ValidatePetNameMessageComposer;
import com.cometproject.server.game.pets.PetManager;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class ValidatePetNameMessageEvent
implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        String name = msg.readString();
        int errorCode = PetManager.getInstance().validatePetName(name);
        String data = null;
        switch (errorCode) {
            case 1: {
                data = "We expect a maximum of 16 characters!";
                break;
            }
            case 2: {
                data = "16";
                break;
            }
            case 3: {
                break;
            }
        }
        client.send(new ValidatePetNameMessageComposer(errorCode, data));
    }
}

