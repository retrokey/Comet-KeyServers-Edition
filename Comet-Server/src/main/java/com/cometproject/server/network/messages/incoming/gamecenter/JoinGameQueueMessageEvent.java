package com.cometproject.server.network.messages.incoming.gamecenter;

import com.cometproject.games.snowwar.SnowPlayerQueue;
import com.cometproject.server.composers.gamecenter.GameStatusMessageComposer;
import com.cometproject.server.composers.gamecenter.LoadGameMessageComposer;
import com.cometproject.server.game.players.PlayerManager;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

import java.util.UUID;

public class JoinGameQueueMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        int gameId = msg.readInt();
        client.getPlayer().sendBubble("", "GameID: " + gameId);
        switch (gameId){
            case 1:
                final UUID sessionId = UUID.randomUUID();

                PlayerManager.getInstance().getSsoTicketToPlayerId().put(client.getPlayer().getId() + sessionId.toString(), client.getPlayer().getId());

                client.send(new LoadGameMessageComposer(gameId, "http://localhost/url2/swf/games/gamecenter_basejump/BaseJump.swf", client.getPlayer().getId() + sessionId.toString(), "localhost", "30010", "30010", "http://localhost/url2/swf/games/gamecenter_basejump/BasicAssets.swf"));
                break;
            case 2:
                SnowPlayerQueue.addPlayerInQueue(client);
                break;
            case 3:
                break;
        }

        client.send(new GameStatusMessageComposer(gameId, 0));
    }
}
