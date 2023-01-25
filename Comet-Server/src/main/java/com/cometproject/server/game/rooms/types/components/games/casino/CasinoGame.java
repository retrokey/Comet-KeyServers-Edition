package com.cometproject.server.game.rooms.types.components.games.casino;

import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.players.types.Player;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.highscore.HighscoreClassicFloorItem;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.components.games.GameType;
import com.cometproject.server.game.rooms.types.components.games.RoomGame;
import com.cometproject.server.game.rooms.types.components.games.casino.types.CasinoBets;
import com.cometproject.server.network.messages.outgoing.notification.MassEventMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.purse.UpdateActivityPointsMessageComposer;
import com.cometproject.server.storage.queries.catalog.BetDao;
import com.cometproject.server.utilities.RandomUtil;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class CasinoGame extends RoomGame {
    public final List<CasinoBets> bets = Lists.newArrayList();
    public final List<String> winners = Lists.newArrayList();
    private static final int[] notificationTimer = {110, 85, 50, 20};
    private static final int[] redNumbers = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
    private static final int[] blackNumbers = {2,4,6,8,10,11,13,15,17,20,22,24,26,28,29,31,33,35};
    private boolean allowBets = true;
    private int betCount = 0;

    public CasinoGame(Room room) {
        super(room, GameType.CASINO);
    }

    public enum ResultResponse{
        BET_VALID,
        BET_SAME_VALUE,
        BET_HAS_COLOR
    }

    @Override
    public void tick() {
        if (this.timer == 55) {
            this.room.getEntities().broadcastMessage(new NotificationMessageComposer("inters", Locale.getOrDefault("roullette.winners", "No va más.")));
            allowBets = false;
        }

        boolean needsNotification = IntStream.of(notificationTimer).anyMatch(x -> x == this.timer);

        if (this.timer == 50 || this.timer == 30 || this.timer == 10) {
            int realTime = 0;

            switch (this.timer){
                case 50:
                    realTime = 10;
                    break;
                case 30:
                    realTime = 30;
                    break;
                case 10:
                    realTime = 50;
                    break;
            }

            this.room.getEntities().broadcastMessage(new NotificationMessageComposer("inters", Locale.getOrDefault("roullette.winners", "Quedan " + realTime + " segundos para el final de la ronda.")));

        }

        if(this.timer == 46) {
            this.room.getEntities().broadcastMessage(new MassEventMessageComposer("habblet/open/playSound?sound=end-roulette"));
        }
    }


    @Override
    public void onGameStarts() {
        this.bets.clear();
        this.winners.clear();
        this.betCount = 0;
    }

    private int getResult(){
        Random result = new Random();
        result.setSeed(Comet.getTime());

        return result.nextInt(37);
    }

    private void handleWinners(String name){
        if(this.winners.contains(name))
            return;

        this.winners.add(name);
    }

    public ResultResponse isValidBet(int playerId, int bet){
        boolean hasBlack = false;
        boolean hasRed = false;

        for (CasinoBets betUnit : this.bets) {
            if(betUnit.getPlayer().getData().getId() == playerId){
                if(betUnit.getBet() == bet)
                    return ResultResponse.BET_SAME_VALUE;

                if(betUnit.getBet() == 37)
                    hasBlack = true;

                if(betUnit.getBet() == 38)
                    hasRed = true;
            }
        }

        if((hasBlack && bet == 38) || (hasRed && bet == 37))
            return ResultResponse.BET_HAS_COLOR;

        return ResultResponse.BET_VALID;
    }

    @Override
    public void onGameEnds() {
        int result = this.getResult();
        long time = Comet.getTime();
        BetDao.insertRoulletteResult(result + "", time + "");

        double multiplier = 2.0;
        int prize = 0;
        boolean hasWon;
        String username;
        String color = "#212121";

        for (CasinoBets bet : this.bets) {
            if(bet.getPlayer() == null || bet.getPlayer().getData() == null || bet.getPlayer().getEntity() == null)
                continue;

            username = bet.getPlayer().getData().getUsername();
            hasWon = false;

            if(bet.isPaid())
                continue;

            if (result == bet.getBet()) {
                if (result == 0) {
                    color = "#22c944";
                    bet.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_118, 1);
                }

                multiplier = 35;
                prize = (int)(multiplier * bet.getAmount());
                hasWon = true;

                this.handleWinners(username);

                bet.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_117, 1);
            } else if (bet.getBet() == 38) { // 37 IMPARES
                if(IntStream.of(blackNumbers).anyMatch(x -> x == result)) {
                    multiplier = 2.0;
                    prize = (int) (multiplier * bet.getAmount());
                    hasWon = true;
                    this.handleWinners(username);
                    bet.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_114, 1);
                }
            } else if (bet.getBet() == 37) { // 38 PARES
                if(IntStream.of(redNumbers).anyMatch(x -> x == result)) {
                    color = "#c92241";
                    multiplier = 2.0;
                    prize = (int) (multiplier * bet.getAmount());
                    hasWon = true;
                    this.handleWinners(username);
                    bet.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_114, 1);
                }
            }

            BetDao.insertBet(bet.getPlayer().getData().getId(), "roullette", bet.getAmount() + " to " + (bet.getBet() == 37 ? "RED" : bet.getBet() == 38 ? "BLACK" : bet.getBet() + ""), time + "", hasWon ? "win" : "share");

            if(hasWon){
                bet.getPlayer().getData().increaseBlackMoney(prize);
                bet.getPlayer().getSession().send(new UpdateActivityPointsMessageComposer(bet.getPlayer().getData().getBlackMoney(), prize, 105));
                bet.getPlayer().getData().save();
                bet.getPlayer().getEntity().incrementBetRow();
                bet.setPaid();
                bet.getPlayer().getSession().send(new MassEventMessageComposer("habblet/open/playSound?sound=win"));

                if(bet.getPlayer().getEntity().getBetRow() >= 2) {
                    bet.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_116, 1);
                }

                bet.getPlayer().getSession().send(new WhisperMessageComposer(bet.getPlayer().getEntity().getId(), "<font color='" + color + "'><b>" + "Has ganado " + multiplier * bet.getAmount() + "</b> Tokens. <i>( " + multiplier + " x " + bet.getAmount() + " )</i>", 1));
            } else {
                bet.getPlayer().getEntity().setBetRow(0);
            }
            //this.betCount++;
        }

        if(IntStream.of(redNumbers).anyMatch(x -> x == result)) color = "#c92241";
        if(IntStream.of(blackNumbers).anyMatch(x -> x == result)) color = "#212121";

        if(winners.size() > 0)
            this.room.getEntities().broadcastMessage(new NotificationMessageComposer("award", Locale.getOrDefault("roullette.winners", "Los ganadores de esta ronda han sido: " + winners.toString() + " felicidades.")));

        this.room.getEntities().broadcastMessage(new WhisperMessageComposer(-1, Locale.getOrDefault("roullette.winners", "<font color='" + color + "'><b>" + result + "</b>, ¡gracias por confiar en la ruleta!</font>"), 1));

        this.bets.clear();
        this.winners.clear();

        final List<HighscoreClassicFloorItem> scoreboards = this.room.getItems().getByClass(HighscoreClassicFloorItem.class);

        if (scoreboards.size() != 0) {
            for (HighscoreClassicFloorItem scoreboard : scoreboards) {
                scoreboard.resetScoreboard();
            }
        }
    }

    public void addPlayerBet(Player p, int bet, int amount){
        String betText = bet == 37 ? "Rojo" : bet == 38 ? "Negro" : bet + "";

        if(this.bets.size() > 100){
            p.sendBubble("casino_full", Locale.getOrDefault("casino.full", "Ahora mismo hay 100 apuestas en la ruleta, espera a la siguiente ronda para hacer más."));
            return;
        }

        this.bets.add(new CasinoBets(p, bet, amount, false));

        if(this.bets.size() <= 50) {
            final List<HighscoreClassicFloorItem> scoreboards = this.room.getItems().getByClass(HighscoreClassicFloorItem.class);

            for (HighscoreClassicFloorItem scoreboard : scoreboards) {
                scoreboard.addPoint(p.getData().getUsername() + " al " + betText, amount);
                scoreboard.sendUpdate();
            }
        }

    }

    public List<CasinoBets> getBets() {
        return bets;
    }

    public boolean betsAllowed() {
        return this.allowBets;
    }

    public int gameTimer(){
        return this.timer;
    }
}
