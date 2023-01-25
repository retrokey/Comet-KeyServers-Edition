package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.games.snowwar.SnowWarRoom;
import com.cometproject.games.snowwar.gameobjects.GameItemObject;
import com.cometproject.games.snowwar.gameobjects.HumanGameObject;

public class SerializeGame2GameObjects {
    public static void parse(IComposer msg, SnowWarRoom arena) {
        synchronized (arena.gameObjects) {
            msg.writeInt(arena.gameObjects.size());
            for (GameItemObject gameItemObject : arena.gameObjects.values()) {
                for (int i = 0; i < gameItemObject.variablesCount; i++) {
                    msg.writeInt(gameItemObject.getVariable(i));
                }

                if (gameItemObject.getVariable(0) == 5) {
                    HumanGameObject Player = (HumanGameObject) gameItemObject;
                    msg.writeString(Player.userName);
                    msg.writeString(Player.motto);
                    msg.writeString(Player.look);
                    msg.writeString(Player.sex);
                }
            }
        }
    }
}