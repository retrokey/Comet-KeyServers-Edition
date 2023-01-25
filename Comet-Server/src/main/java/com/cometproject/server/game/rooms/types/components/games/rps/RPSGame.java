/*package com.cometproject.server.game.rooms.types.components.games.rps;

import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.types.components.RPSComponent;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.nuxs.NuxGiftSelectionViewMessageComposer;
import com.cometproject.server.protocol.messages.MessageComposer;

public class RPSGame {

    private PlayerEntity user1, user2;

    private boolean user1commited = false, user2commited = false;
    private int user1commit = 0, user2commit = 0;
    private int bet = 50;
    private RPSComponent RPSComponent;

    public RPSGame(PlayerEntity user1, PlayerEntity user2, int b) {
        this.user1 = user1;
        this.user2 = user2;

        bet = b;

        sendToUsers(new NuxGiftSelectionViewMessageComposer(4));
    }

    public void setCommit(int user, int s){
        if(user == 1){
            user1commit = s;
            user1commited = true;
        } else {
            user2commit = s;
            user2commited = true;
        }

        if(user1commited && user2commited){
            if(user1commit == user2commit){
                System.out.print("\n\nEMPATE\n\n");
            }

            // R 1, P 2, S 3
            else if (user1commit == 1) {
                if (user2commit == 3)
                    verifyWinner(user1.getPlayerId());
                else if (user2commit == 2)
                    verifyWinner(user2.getPlayerId());
            }
            else if (user1commit == 2) {
                if (user2commit == 3)
                    verifyWinner(user2.getPlayerId());
                else if (user2commit == 1)
                    verifyWinner(user1.getPlayerId());
            }
            else if (user1commit == 3) {
                if (user2commit == 2)
                    verifyWinner(user1.getPlayerId());
                else if (user2commit == 1)
                    verifyWinner(user2.getPlayerId());
            }

            user1.getRoom().getRPS().remove(this);
        }
    }

    private void verifyWinner(int u){
        if(u == 1){
            if(bet > 0){
                user2.getPlayer().getData().decreaseCredits(bet);
                user1.getPlayer().getData().increaseCredits(bet);

                user1.getPlayer().composeCreditBalance();
                user2.getPlayer().composeCreditBalance();
                }

            sendToUsers(new NotificationMessageComposer("rps", "%u1% ha ganado a %u2% en piedra, papel o tijera.".replace("%u1%", user1.getPlayer().getData().getUsername()).replace("%u2%", user2.getPlayer().getData().getUsername()), ""));
        }

        else {
            if(bet > 0) {
                user1.getPlayer().getData().decreaseCredits(bet);
                user2.getPlayer().getData().increaseCredits(bet);

                user1.getPlayer().composeCreditBalance();
                user2.getPlayer().composeCreditBalance();
            }

            sendToUsers(new NotificationMessageComposer("rps", "%u1% ha ganado a %u2% en piedra, papel o tijera.".replace("%u1%", user1.getPlayer().getData().getUsername()).replace("%u2%", user2.getPlayer().getData().getUsername()), ""));
        }
    }

    public void sendToUsers(MessageComposer msg) {
        if (user1 != null && user1.getPlayer() != null && user1.getPlayer().getSession() != null) {
            user1.getPlayer().getSession().send(msg);
        }

        if (user2 != null && user2.getPlayer() != null && user2.getPlayer().getSession() != null) {
            user2.getPlayer().getSession().send(msg);
        }
    }


    public int getUserNumber(PlayerEntity user) {
        return (user1 == user) ? 1 : 0;
    }

    public void setRPSComponent(RPSComponent r) {
        this.RPSComponent = r;
    }

    public PlayerEntity getUser1() {
        return this.user1;
    }

    public PlayerEntity getUser2() {
        return this.user2;
    }
}
*/