package com.cometproject.server.network.messages.incoming.gamecenter.snowwar.game;

import com.cometproject.games.snowwar.SnowWarRoom;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.PlayerRematchesComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class PlayAgainParser implements Event {
  public void handle(Session client, MessageEvent msg) throws Exception {
    SnowWarRoom room = client.snowWarPlayerData.currentSnowWar;

    if (room == null) {
      return;
    }

    room.broadcast(new PlayerRematchesComposer(client.getPlayer().getId()));
  }
}