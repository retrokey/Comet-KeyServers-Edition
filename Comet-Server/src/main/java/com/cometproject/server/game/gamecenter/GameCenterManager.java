package com.cometproject.server.game.gamecenter;

import com.cometproject.api.utilities.Initialisable;
import com.cometproject.server.game.players.data.GamePlayer;
import com.cometproject.server.storage.queries.catalog.BetDao;
import com.cometproject.server.tasks.CometThreadManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.TimeUnit;


public class GameCenterManager implements Initialisable {
    private static GameCenterManager gameCenterManagerInstance;
    private static int gameId;
    private List<GamePlayer> currentWeek;
    private List<GamePlayer> lastWeek;
    private Logger LOGGER = LoggerFactory.getLogger(GameCenterManager.class.getName());

    private List<GameCenterInfo> gamesList;

    public GameCenterManager() {
    }

    @Override
    public void initialize() {
        this.gamesList = new ArrayList<>();

        this.loadLeaderboards();
        this.loadGameCenterList();

        LOGGER.info("GameCenter initialized.");
    }

    private void loadLeaderboards() {
        if (this.currentWeek != null) {
            this.currentWeek.clear();
        }

        if (this.lastWeek != null) {
            this.lastWeek.clear();
        }

        this.currentWeek = BetDao.getLeaderBoard(1, 0, false, false);
        this.lastWeek = BetDao.getLeaderBoard(1, 0, true, false);

        // Queue it to be refreshed again in 5 minutes.
        CometThreadManager.getInstance().executeSchedule(this::loadLeaderboards,1, TimeUnit.MINUTES);
    }

    public List<GamePlayer> getLeaderboardByWeek(boolean isCurrent){
        if(isCurrent)
        return this.currentWeek;
        else return lastWeek;
    }

    public static GameCenterManager getInstance() {
        if (gameCenterManagerInstance == null)
            gameCenterManagerInstance = new GameCenterManager();

        return gameCenterManagerInstance;
    }

    public void loadGameCenterList() {
        if(!this.gamesList.isEmpty()) {
            this.gamesList.clear();
        }

        this.gamesList = BetDao.getGames();
    }

    public GameCenterInfo getGameById(int gameId){

        GameCenterInfo gameInfo = null;
        for(GameCenterInfo infoGame : this.gamesList){
            if(infoGame.getGameId() == gameId){
                gameInfo = infoGame;
                break;
            }
        }
        return gameInfo;
    }

    public List<GameCenterInfo> getGamesList() {
        return this.gamesList;
    }
}
