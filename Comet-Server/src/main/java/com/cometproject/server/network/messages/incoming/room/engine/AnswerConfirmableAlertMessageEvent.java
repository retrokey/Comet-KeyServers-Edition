package com.cometproject.server.network.messages.incoming.room.engine;

import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.nuxs.NuxGiftSelectionViewMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class AnswerConfirmableAlertMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        if(client == null || client.getPlayer() == null || client.getPlayer().getEntity() == null)
            return;

        if(client.getPlayer().antiSpam("ConfirmableAlert", 1))
            return;

        final boolean answer = msg.readBoolean();
        final String username = msg.readString();
        final int type = msg.readInt();
        final boolean isWiredTrigger = msg.readBoolean();

        if(answer){
            switch (type) {
                case 1: /* MINIGAMES */
                    // RPS
                    client.getPlayer().setRPSRival(username);
                    Session rival = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);

                    if (rival == null || rival.getPlayer() == null || rival.getPlayer().getEntity() == null)
                        return;

                    if (rival.getPlayer().getRPSRival().contains(client.getPlayer().getData().getUsername()) && rival.getPlayer().getRPSamount() == client.getPlayer().getRPSamount()) {
                        client.send(new NuxGiftSelectionViewMessageComposer(4));
                        rival.send(new NuxGiftSelectionViewMessageComposer(4));
                    }
                    break;
            }
        }
    }
}
