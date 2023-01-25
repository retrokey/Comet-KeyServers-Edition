package com.cometproject.games.snowwar.tasks;

import com.cometproject.games.snowwar.SnowWar;
import com.cometproject.games.snowwar.SnowWarRoom;
import com.cometproject.games.snowwar.gameobjects.HumanGameObject;
import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.AccountGameStatusComposer;
import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.GameEndingComposer;

public class SnowArenaEnd {
    public static void exec(SnowWarRoom room) {
        room.Winner = 0;
        int blueScore = 0;
        int redScore = 0;
        for (int teamId : SnowWar.TEAMS) {
            if (teamId == 1) {
                blueScore += room.teamScore[teamId - 1];
            } else {
                redScore += room.teamScore[teamId - 1];
            }
        }

        if (blueScore > redScore) {
            room.Winner = 1;
            room.Result = 1;
        } else if (redScore > blueScore) {
            room.Winner = 2;
            room.Result = 1;
        } else {
            room.Result = 2;
        }

        for (HumanGameObject player : room.players.values()) {
            if (room.MostHits == null) {
                room.MostHits = player;
            }
            if (room.MostKills == null) {
                room.MostKills = player;
            }
            if (player.hits > room.MostHits.hits) {
                room.MostHits = player;
            }
            if (player.kills > room.MostKills.kills) {
                room.MostKills = player;
            }

            if (player.team == room.Winner) {
                //player.cn.getPlayer().increaseXP(50); continue;
            }
            //player.cn.getPlayer().increaseXP(25);
        }
        room.broadcast(new AccountGameStatusComposer(2));
        room.broadcast(new GameEndingComposer(room));
    }
}