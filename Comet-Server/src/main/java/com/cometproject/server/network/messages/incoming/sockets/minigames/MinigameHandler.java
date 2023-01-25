package com.cometproject.server.network.messages.incoming.sockets.minigames;

import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.rooms.objects.entities.effects.PlayerEffect;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.MassEventMessageComposer;
import com.cometproject.server.network.messages.outgoing.nuxs.NuxGiftSelectionViewMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.ActionMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.utilities.RandomUtil;
import com.google.gson.JsonObject;

public class MinigameHandler {
    public MinigameHandler(Session client, JsonObject msg) {
        if (client.getPlayer().getEntity() == null || client.getPlayer().getEntity().getRoom() == null)
            return;

        final long currentTimeMs = System.currentTimeMillis();
        final long timeSinceLastUpdate = currentTimeMs - client.getPlayer().getLastDuel();

        if (timeSinceLastUpdate < 10000)
            return;

        if (msg.get("type").getAsString() != null || !msg.get("type").getAsString().equals("") || msg.get("value").getAsString() != null || !msg.get("value").getAsString().equals("")) {
            String type = msg.get("type").getAsString();
            String username = msg.get("value").getAsString();

            Session enemy = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);

            if(enemy == null || enemy.getPlayer() == null || enemy.getPlayer().getEntity() == null) {
                client.getPlayer().sendBubble("games", "Tu enemigo no está disponible en estos instantes.");
                return;
            }

            switch (type){
                case "acceptDuel":
                    this.acceptDuel(client, enemy);
                    break;
                case "acceptRPS":
                    this.acceptRPS(client, enemy, username);
                    break;
            }
        }
    }

    private void acceptDuel(Session client, Session target){
        Session winner;
        Session looser;
        int random = RandomUtil.getRandomInt(1, 2);

        if(random == 1){
            winner = client;
            looser = target;
        } else {
            winner = target;
            looser = client;
        }

        winner.getPlayer().getEntity().applyEffect(new PlayerEffect(101, 10));
        winner.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new ActionMessageComposer(winner.getPlayer().getEntity().getId(), 1));
        looser.getPlayer().getEntity().applyEffect(new PlayerEffect(602, 5));

        winner.send(new MassEventMessageComposer("habblet/open/playSound?sound=winchester"));
        looser.send(new MassEventMessageComposer("habblet/open/playSound?sound=winchester"));

        winner.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_129, 1);

        client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new TalkMessageComposer(-1,
        Locale.getOrDefault("duel.won", "<b>%user%</b> acaba de ganar a  <font color='#ce1244'>%target%</font> en un Duelo de Revólver.").replace("%user%", winner.getPlayer().getData().getUsername()).replace("%target%", looser.getPlayer().getData().getUsername()),
        ChatEmotion.NONE, 1));

        client.getPlayer().setLastDuel(System.currentTimeMillis());
        target.getPlayer().setLastDuel(System.currentTimeMillis());
    }

    private void acceptRPS(Session client, Session target, String username){
        client.getPlayer().setRPSRival(username);

        if(target == null || target.getPlayer() == null || target.getPlayer().getEntity() == null || target.getPlayer().getRPSRival().equals("")){
            client.getPlayer().resetRPS();
            client.getPlayer().sendBubble("games", "Tu enemigo no está disponible en estos momentos.");
            return;
        }

        if (target.getPlayer().getRPSRival().contains(client.getPlayer().getData().getUsername()) && client.getPlayer().getRPSRival().contains(username)){
            client.send(new NuxGiftSelectionViewMessageComposer(4));
            target.send(new NuxGiftSelectionViewMessageComposer(4));
        }
    }
}