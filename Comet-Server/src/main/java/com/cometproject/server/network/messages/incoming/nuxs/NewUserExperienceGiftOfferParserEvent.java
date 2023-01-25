package com.cometproject.server.network.messages.incoming.nuxs;

import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.server.game.catalog.CatalogManager;
import com.cometproject.server.game.nuxs.NuxGift;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.nuxs.NuxGiftSelectionViewMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class NewUserExperienceGiftOfferParserEvent implements Event {

    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        if(client == null)
            return;

        final int int1 = msg.readInt();
        final int int2 = msg.readInt();
        final int int3 = msg.readInt();
        final int giftId = msg.readInt();

        client.getPlayer().setRPSselection(giftId + 1);
        Session enemy = NetworkManager.getInstance().getSessions().getByPlayerUsername(client.getPlayer().getRPSRival());

        if(enemy != null && client.getPlayer().getRPSRival().contains(enemy.getPlayer().getData().getUsername())){
            int user1commit = client.getPlayer().getRPSselection();
            int user2commit = enemy.getPlayer().getRPSselection();

            if (client.getPlayer().getRPSselection() > 0 && enemy.getPlayer().getRPSselection() > 0) {
                if (user1commit == user2commit) {
                    client.send(new NuxGiftSelectionViewMessageComposer(4));
                    client.send(new WhisperMessageComposer(-1, "Vaya, parece que ha habido un empate, volved a tirar.", 1));
                    client.getPlayer().setRPSselection(0);

                    enemy.send(new NuxGiftSelectionViewMessageComposer(4));
                    enemy.send(new WhisperMessageComposer(-1, "Vaya, parece que ha habido un empate, volved a tirar.", 1));
                    enemy.getPlayer().setRPSselection(0);
                } else if (user1commit == 1) {
                    if (user2commit == 3)
                        verifyWinner(client, enemy, "piedra");
                    else if (user2commit == 2)
                        verifyWinner(enemy, client, "papel");
                } else if (user1commit == 2) {
                    if (user2commit == 3)
                        verifyWinner(enemy, client, "tijeras");
                    else if (user2commit == 1)
                        verifyWinner(client, enemy, "papel");
                } else if (user1commit == 3) {
                    if (user2commit == 2)
                        verifyWinner(client, enemy, "tijeras");
                    else if (user2commit == 1)
                        verifyWinner(enemy, client, "piedra");
                }
            }
            return;
        }

        NuxGift selectedGift = CatalogManager.getInstance().getNuxGifts().get(giftId);

        switch (selectedGift.getType()) {
            case DIAMONDS:
                int diamonds = Integer.parseInt(selectedGift.getRandomData());
                client.getPlayer().getData().increaseVipPoints(diamonds);
                client.getPlayer().sendBalance();
                break;
            case SEASONAL:
                int seasonal = Integer.parseInt(selectedGift.getRandomData());
                client.getPlayer().getData().increaseSeasonalPoints(seasonal);
                client.getPlayer().sendBalance();
                break;
            case BADGE:
                client.getPlayer().getInventory().addBadge(selectedGift.getRandomData(), true);
                break;
        }
    }

    private void verifyWinner(Session winner, Session loser, String type){
        int loserAmount = loser.getPlayer().getRPSamount();
        int winnerAmount = winner.getPlayer().getRPSamount();

        if(loserAmount != winnerAmount)
            return;

        /*if(loserAmount > 0) {
            loser.getPlayer().getData().decreaseCredits(winnerAmount);
            winner.getPlayer().getData().increaseCredits(winnerAmount);
            winner.getPlayer().composeCurrenciesBalance();
            loser.getPlayer().composeCurrenciesBalance();
        }*/

        winner.getPlayer().resetRPS();
        loser.getPlayer().resetRPS();

        winner.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_129, 1);
        winner.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new TalkMessageComposer(-1, "<b>%u1%</b> ha ganado a <b>%u2%</b> en Piedra, Papel o Tijera sacando <b>%r</b>.".replace("%u1%", winner.getPlayer().getData().getUsername()).replace("%u2%", loser.getPlayer().getData().getUsername()).replace("%r", type), ChatEmotion.ANGRY, 1));
        }
}