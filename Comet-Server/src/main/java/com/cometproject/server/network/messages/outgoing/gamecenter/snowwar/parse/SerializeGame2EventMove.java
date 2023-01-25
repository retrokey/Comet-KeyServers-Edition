package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse;
import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.games.snowwar.ComposerShit;
import com.cometproject.games.snowwar.MessageWriter;
import com.cometproject.games.snowwar.gameevents.UserMove;

public class SerializeGame2EventMove {
    public static void parse(IComposer msg, UserMove evt) {
        msg.writeInt(evt.player.objectId);
        msg.writeInt(evt.x);
        msg.writeInt(evt.y);
    }

    public static void parse(MessageWriter ClientMessage, UserMove evt) {
        ComposerShit.add(evt.player.objectId, ClientMessage);
        ComposerShit.add(evt.x, ClientMessage);
        ComposerShit.add(evt.y, ClientMessage);
    }
}