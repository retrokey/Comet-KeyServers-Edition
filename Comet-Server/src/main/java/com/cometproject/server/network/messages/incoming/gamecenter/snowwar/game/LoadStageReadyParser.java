package com.cometproject.server.network.messages.incoming.gamecenter.snowwar.game;

import com.cometproject.games.snowwar.data.SnowWarPlayerData;
import com.cometproject.games.snowwar.gameobjects.HumanGameObject;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class LoadStageReadyParser implements Event {
    public void handle(Session client, MessageEvent msg) throws Exception {
        SnowWarPlayerData snowPlayer = client.snowWarPlayerData;
        if (snowPlayer.currentSnowWar == null) {
            return;
        }

        HumanGameObject humanObject = snowPlayer.humanObject;
        if (humanObject == null) {
            return;
        }

        snowPlayer.currentSnowWar.stageLoaded(humanObject);
    }
}