package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.games.snowwar.gameobjects.HumanGameObject;
public class SerializeGame2PlayerData {
    public static void parse(IComposer msg, HumanGameObject Player) {
        msg.writeInt(Player.userId);
        msg.writeString(Player.userName);
        msg.writeString(Player.look);
        msg.writeString(Player.sex);
        msg.writeInt(Player.team);
    }
}