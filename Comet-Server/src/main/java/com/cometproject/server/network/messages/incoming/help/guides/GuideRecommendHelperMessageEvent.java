package com.cometproject.server.network.messages.incoming.help.guides;

import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.game.guides.GuideManager;
import com.cometproject.server.game.guides.types.HelpRequest;
import com.cometproject.server.logging.database.queries.LogQueries;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.help.guides.GuideSessionDettachedMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.AlertMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;


public class GuideRecommendHelperMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        final HelpRequest helpRequest = client.getPlayer().getHelpRequest();

        if(client.getPlayer().getData().getRank() > 2) {

            // Recaching all data.
            helpRequest.setIsStop();
            helpRequest.getGuideSession().getPlayer().setHelpRequest(null);
            client.send(new GuideSessionDettachedMessageComposer());
            return;
        }

        final boolean vote = msg.readBoolean();

        if(vote){
            LogQueries.putRecommendation(client.getPlayer().getId(), helpRequest.guideId, (int)Comet.getTime());
            if(helpRequest.getGuideSession() != null)
            helpRequest.getGuideSession().getPlayer().getAchievements().progressAchievement(AchievementType.ACH_127, 1);
        }

        client.send(new GuideSessionDettachedMessageComposer());
        client.getPlayer().setHelpRequest(null);
        GuideManager.getInstance().requestHelp(helpRequest);
    }
}
