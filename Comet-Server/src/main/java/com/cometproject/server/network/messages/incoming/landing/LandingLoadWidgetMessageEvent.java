package com.cometproject.server.network.messages.incoming.landing;

import com.cometproject.api.config.CometSettings;
import com.cometproject.api.game.quests.IQuest;
import com.cometproject.server.game.landing.LandingManager;
import com.cometproject.server.game.quests.QuestManager;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.landing.*;
import com.cometproject.server.network.messages.outgoing.landing.calendar.CampaignCalendarDataMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.MassEventMessageComposer;
import com.cometproject.server.network.messages.outgoing.quests.VIPQuestPromotionMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;
import org.eclipse.jetty.util.StringUtil;


public class LandingLoadWidgetMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        final String text = msg.readString();

        //client.send(new MassEventMessageComposer("openView/calendar"));

        if (CometSettings.bonusBagEnabled) {
            client.send(new BonusBagMessageComposer(CometSettings.bonusBagConfiguration, client.getPlayer().getData().getAchievementPoints(), client.getPlayer().getStats().getLevel()));
        }

        //client.send(new VIPQuestPromotionMessageComposer());

        /*if(text.contains("dailyquest")){
            final IQuest quest = QuestManager.getInstance().getById(4);

            if (quest == null) {
                return;
            }

            client.send(new DailyQuestMessageComposer(1, 1, quest, client.getPlayer()));
        }*/

        if(text.contains("gamesmaker") && CometSettings.hallOfFameEnabled){
            client.send(new SendHotelViewLooksMessageComposer(CometSettings.hallOfFameTextsKey, LandingManager.getInstance().getHallOfFame()));
            return;
        }

        String campaignName = "";
        String[] parser = text.split(";");

        for(int i = 0; i < parser.length; i++){
            if(parser[i].isEmpty() || parser[i].endsWith(","))
                continue;

            String[] data = parser[i].split(",");
            if (!data[1].isEmpty())
            campaignName = data[1];
        }

        client.send(new HotelViewItemMessageComposer(text, campaignName));
        client.send(new LTDCountdownMessageComposer(text, 1));
    }
}
