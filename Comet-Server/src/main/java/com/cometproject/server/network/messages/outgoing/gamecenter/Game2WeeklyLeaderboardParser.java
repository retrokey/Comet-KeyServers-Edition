package com.cometproject.server.network.messages.outgoing.gamecenter;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.players.data.GamePlayer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;
import com.cometproject.server.storage.queries.catalog.BetDao;

import java.util.List;

public class Game2WeeklyLeaderboardParser  extends MessageComposer {
    private int gameId;
    private List<GamePlayer> data;

    public Game2WeeklyLeaderboardParser(int gameId, int playerId){
        this.gameId = gameId;
        //this.data = BetDao.getLeaderBoard(gameId, playerId, false, false);
    }

    @Override
    public void compose(IComposer msg) {
        int i = 1;

        msg.writeInt(2012);
        msg.writeInt(0); // week
        msg.writeInt(0); // maxOffSet
        msg.writeInt(0); // Offser
        msg.writeInt(23); // Minutes until reset.

        /*msg.writeInt(this.data.size() > 3 ? 3 : this.data.size());
        for(final GamePlayer player : this.data) {
            msg.writeInt(1);
            msg.writeInt(player.getPoints());
            msg.writeInt(i++);
            msg.writeString(player.getUsername());
            msg.writeString(player.getFigure());
            msg.writeString(player.getGender().toUpperCase());
            if(i == 4) break;
        }

        msg.writeInt(1); // position start or end....
        msg.writeInt(gameId);*/
    }

    @Override
    public short getId() {
        return Composers.Game2WeeklyLeaderboardParser;
    }
}
