package com.cometproject.server.network.messages.incoming.user.newyear;

import com.cometproject.api.config.CometSettings;
import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.rooms.types.components.games.RoomGame;
import com.cometproject.server.game.rooms.types.components.games.casino.CasinoGame;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.notification.AlertMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.purse.UpdateActivityPointsMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;
import com.cometproject.server.utilities.RandomUtil;

public class ResolutionSelectMessageEvent implements Event {
    public void handle(Session client, MessageEvent msg) {
        if(client == null || client.getPlayer() == null || client.getPlayer().getEntity() == null || client.getPlayer().getStats() == null)
            return;

        int userId = msg.readInt();
        int selectionId = msg.readInt();
        String convertedSelection;
        int isRandom = RandomUtil.getRandomInt(0,39);
        boolean isColor = (selectionId == 37 || selectionId == 38);

        final RoomGame game = client.getPlayer().getEntity().getRoom().getGame().getInstance();

        if (!(game instanceof CasinoGame)) { return; }

        final CasinoGame casinoGame = (CasinoGame) game;

        if(casinoGame.getBets().size() > 50){
            client.getPlayer().sendBubble("casino_full", Locale.getOrDefault("casino.full", "Ahora mismo hay 50 apuestas en la ruleta, espera a la siguiente ronda para hacer más."));
            return;
        }

        if(client.getPlayer().getEntity().getBetAmount() > client.getPlayer().getData().getBlackMoney()){
            client.send(new NotificationMessageComposer("generic", Locale.getOrDefault("casino.missing.currency", "¡No tienes suficientes Tokens para realizar la apuesta!\n\nTienes %currency% Tokens y la apuesta en %bet%.").replace("%currency%", client.getPlayer().getData().getBlackMoney() + "").replace("%bet%", client.getPlayer().getEntity().getBetAmount() + "")));
            return;
        }

        if(!casinoGame.betsAllowed()) {
            client.send(new NotificationMessageComposer("generic", Locale.getOrDefault("casino.bets.closed", "¡Las apuestas están cerradas, no va más!")));
            return;
        }

        if(casinoGame.isValidBet(client.getPlayer().getData().getId(), selectionId) != CasinoGame.ResultResponse.BET_VALID) {
            client.send(new NotificationMessageComposer("bet_done", Locale.getOrDefault("casino.bet.done", "Ya has realizado una apuesta a ese número o color, solo puedes apostar una vez al mismo valor.")));
            return;
        }

        /*if(isColor){
            if(client.getPlayer().getStats().getDailyRolls() <= 0){
                client.getPlayer().sendBubble("casino_limit", Locale.getOrDefault("casino.rolls.limit", "Has superado el límite diario de apuestas a color, cada 24 horas recibes 5 tiradas a color."));
                return;
            }
            client.getPlayer().getStats().decrementDailyRolls(1);
            client.getPlayer().sendBubble("casino_limit_progress", Locale.getOrDefault("casino.rolls.progress", "Has utilizado una tirada a color, te quedan %limit%/5.").replace("%limit%", client.getPlayer().getStats().getDailyRolls() + ""));
        }*/

        if(selectionId == 39){
            if(!CometSettings.casinoFreeRolls) {
                client.getPlayer().sendBubble("casino_limit_progress", Locale.getOrDefault("casino.rolls.disabled", "Los tiros de cortesía están desactivados. Vuelve a intentarlo más tarde."));
                return;
            }

            if(client.getPlayer().getStats().getDailyRolls() <= 0){
                client.getPlayer().sendBubble("casino_limit", Locale.getOrDefault("casino.rolls.limit", "Has superado el límite diario de tiros de cortesía, cada 24 horas recibes 3 tiradas."));
                return;
            }

            if(client.getPlayer().getSubscription() != null && client.getPlayer().getSubscription().isValid()) {
                casinoGame.addPlayerBet(client.getPlayer(), isRandom, 10);
                client.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_115, 1);
                client.send(new WhisperMessageComposer(-1, "Has apostado " + 10 + " Tokens con la tirada gratuita al " + isRandom + ".", 1));
                client.getPlayer().getStats().decrementDailyRolls(1);
                client.getPlayer().sendBubble("casino_limit_progress", Locale.getOrDefault("casino.rolls.progress", "Has utilizado una tirada de cortesía, te quedan %limit%/3.").replace("%limit%", client.getPlayer().getStats().getDailyRolls() + ""));
            } else {
                client.getPlayer().sendBubble("casino_limit_progress", Locale.getOrDefault("casino.rolls.vip.required", "No formas parte de la membresía VIP por lo que no puedes utilizar tu tiro de cortesía."));
            }
            return;
        }

        client.getPlayer().getData().decreaseBlackMoney(client.getPlayer().getEntity().getBetAmount());
        client.getPlayer().getData().save();
        client.send(new UpdateActivityPointsMessageComposer(client.getPlayer().getData().getBlackMoney(), -client.getPlayer().getEntity().getBetAmount(), 105));

        casinoGame.addPlayerBet(client.getPlayer(), selectionId, client.getPlayer().getEntity().getBetAmount());

        if(client.getPlayer().getEntity().getBetAmount() >= 100)
            client.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_113, 1);

        if(isColor) {
            convertedSelection = verifyConversion(selectionId);
            client.send(new WhisperMessageComposer(-1, "Has apostado " + client.getPlayer().getEntity().getBetAmount() + " Tokens al " + convertedSelection + ".", 1));
            return;
        }

        client.send(new WhisperMessageComposer(-1, "Has apostado " + client.getPlayer().getEntity().getBetAmount() + " Tokens al " + selectionId + ".", 1));

    }

    private String verifyConversion(int s){
        String convertedSelection = "";
        switch (s){
            case 37:
                convertedSelection = "<font color='#'>rojo</font>";
                break;
            case 38:
                convertedSelection = "<font color='#'>negro</font>";
                break;
        }
        return convertedSelection;
    }
}

