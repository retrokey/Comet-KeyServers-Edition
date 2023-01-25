package com.cometproject.server.network.messages.incoming.catalog.pets;

import com.cometproject.api.game.pets.IPetRace;
import com.cometproject.api.networking.messages.IMessageComposer;
import com.cometproject.server.composers.catalog.pets.PetRacesMessageComposer;
import com.cometproject.server.game.pets.PetManager;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class PetRacesMessageEvent
implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) {
        String petRace = msg.readString();
        String[] splitRace = petRace.split("a0 pet");
        if (splitRace.length < 2 || !StringUtils.isNumeric((String)splitRace[1])) {
            return;
        }
        int raceId = Integer.parseInt(splitRace[1]);
        List<IPetRace> races = PetManager.getInstance().getRacesByRaceId(raceId);
        client.send(new PetRacesMessageComposer(petRace, races));
    }
}

