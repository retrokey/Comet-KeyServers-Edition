package com.cometproject.server.network.messages.incoming.gamecenter.snowwar.game;

import com.cometproject.games.snowwar.SnowWarRoom;
import com.cometproject.games.snowwar.data.SnowWarPlayerData;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.GameChatFromPlayerComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class GameChatParser implements Event {
    public void handle(Session client, MessageEvent msg) throws Exception {
        SnowWarPlayerData snowPlayer = client.snowWarPlayerData;
        if (snowPlayer == null) {
            return;
        }

        SnowWarRoom room = snowPlayer.currentSnowWar;
        if (room == null) {
            return;
        }

        final String say = msg.readString();

        room.broadcast(new TalkMessageComposer(snowPlayer.humanObject.objectId, say, ChatEmotion.NONE, snowPlayer.humanObject.team == 1 ? 4 : 3));
    }
}