package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.games.snowwar.ComposerShit;
import com.cometproject.games.snowwar.MessageWriter;
import com.cometproject.games.snowwar.gameevents.BallThrowToHuman;

public class SerializeGame2EventBallThrowToHuman {
    public static void parse(IComposer msg, BallThrowToHuman evt) {
        msg.writeInt(evt.attacker.objectId);
        msg.writeInt(evt.victim.objectId);
        msg.writeInt(evt.type);
    }

    public static void parse(MessageWriter ClientMessage, BallThrowToHuman evt) {
        ComposerShit.add(evt.attacker.objectId, ClientMessage);
        ComposerShit.add(evt.victim.objectId, ClientMessage);
        ComposerShit.add(evt.type, ClientMessage);
    }
}