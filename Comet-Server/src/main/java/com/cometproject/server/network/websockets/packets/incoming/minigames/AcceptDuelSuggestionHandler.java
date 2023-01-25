package com.cometproject.server.network.websockets.packets.incoming.minigames;

import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.api.game.quests.QuestType;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.rooms.objects.entities.effects.PlayerEffect;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.room.avatar.ActionMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.network.websockets.WebSocketSessionManager;
import com.cometproject.server.network.websockets.packets.incoming.AbstractWebSocketHandler;
import com.cometproject.server.network.websockets.packets.outgoing.SurvivalSoundEffectWebPacket;
import com.cometproject.server.utilities.RandomUtil;
import io.netty.channel.ChannelHandlerContext;

public class AcceptDuelSuggestionHandler extends AbstractWebSocketHandler<AcceptDuelSuggestionHandler.ASMData> {

    public AcceptDuelSuggestionHandler() {
        super(ASMData.class);
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ASMData eventData) {
        Session s = NetworkManager.getInstance().getSessions().getByPlayerId(Integer.parseInt(eventData.session));
        String enemyName = eventData.username;

        if(s == null || s.getPlayer() == null || enemyName.equals("")) {
            return;
        }

        if(s.getPlayer().antiSpam(getClass().getName(), 0.5))
            return;

        final long currentTimeMs = System.currentTimeMillis();
        final long timeSinceLastUpdate = currentTimeMs - s.getPlayer().getLastDuel();

        if (timeSinceLastUpdate < 10000)
            return;

        Session enemy = NetworkManager.getInstance().getSessions().getByPlayerUsername(enemyName);

        if(enemy == null || enemy.getPlayer() == null){
            s.getPlayer().sendBubble("games", "Tu enemigo no está disponible en estos instantes.");
            return;
        }

        int random = RandomUtil.getRandomInt(1, 2);

        if(random == 1){
            s.getPlayer().getEntity().applyEffect(new PlayerEffect(101, 10));
            s.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new ActionMessageComposer(s.getPlayer().getEntity().getId(), 1));
            enemy.getPlayer().getEntity().applyEffect(new PlayerEffect(602, 5));

            if(s.getPlayer().getData().getUsername().contains("Heisenberg")){
                s.getPlayer().getQuests().progressQuest(QuestType.EXPLORE_1, 1);
            }

            WebSocketSessionManager.getInstance().sendMessage(s.getPlayer().getSession().getWsChannel(),
                    new SurvivalSoundEffectWebPacket("survivalSound", "winchester"));
            WebSocketSessionManager.getInstance().sendMessage(enemy.getPlayer().getSession().getWsChannel(),
                    new SurvivalSoundEffectWebPacket("survivalSound", "winchester"));

            s.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_129, 1);

            s.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new TalkMessageComposer(-1,
                    Locale.getOrDefault("action.candy.recieved", "<b>%user%</b> acaba de ganar a  <font color='#ce1244'>%target%</font> en un Duelo de Revólver.").replace("%user%", s.getPlayer().getData().getUsername()).replace("%target%", enemy.getPlayer().getData().getUsername()),
                    ChatEmotion.NONE, 1));

        } else {
            enemy.getPlayer().getEntity().applyEffect(new PlayerEffect(101, 10));
            enemy.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new ActionMessageComposer(enemy.getPlayer().getEntity().getId(), 1));
            s.getPlayer().getEntity().applyEffect(new PlayerEffect(602, 5));

            if(s.getPlayer().getData().getUsername().contains("Heisenberg")){
                enemy.getPlayer().getQuests().progressQuest(QuestType.EXPLORE_1, 1);
            }

            WebSocketSessionManager.getInstance().sendMessage(enemy.getPlayer().getSession().getWsChannel(),
                    new SurvivalSoundEffectWebPacket("survivalSound", "winchester"));

            WebSocketSessionManager.getInstance().sendMessage(s.getWsChannel(),
                    new SurvivalSoundEffectWebPacket("survivalSound", "winchester"));

            enemy.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_129, 1);
            s.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new TalkMessageComposer(-1,
                    Locale.getOrDefault("action.candy.recieved", "<b>%user%</b> acaba de ganar a  <font color='#ce1244'>%target%</font> en un Duelo de Revólver.").replace("%user%", enemy.getPlayer().getData().getUsername()).replace("%target%", s.getPlayer().getData().getUsername()),
                    ChatEmotion.NONE, 1));

        }

        s.getPlayer().setLastDuel(System.currentTimeMillis());
    }

    class ASMData {
        private String username;
        private String session;
    }
}
