package com.cometproject.server.network.messages.incoming.user.details;

import com.cometproject.server.game.achievements.AchievementManager;
import com.cometproject.server.game.guides.GuideManager;
import com.cometproject.server.game.guides.types.HelperSession;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.help.guides.GuideToolsMessageComposer;
import com.cometproject.server.network.messages.outgoing.messenger.MessengerConfigMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.achievements.AchievementPointsMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.achievements.AchievementRequirementsMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.buildersclub.BuildersClubMembershipMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.details.UserObjectMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.inventory.BadgeInventoryMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.permissions.AllowancesMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.wardrobe.FigureSetIdsMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;


public class InfoRetrieveMessageEvent implements Event {
    public void handle(Session client, MessageEvent msg) {

        client.sendQueue(client.getPlayer().composeCreditBalance());
        client.sendQueue(client.getPlayer().composeCurrenciesBalance());
        client.sendQueue(new UserObjectMessageComposer(client.getPlayer()));
        client.sendQueue(new BuildersClubMembershipMessageComposer());
        client.sendQueue(new AllowancesMessageComposer(client.getPlayer().getData().getRank()));
        client.sendQueue(new AchievementPointsMessageComposer(client.getPlayer().getData().getAchievementPoints(), client.getPlayer().getStats().getLevel()));
        client.sendQueue(new MessengerConfigMessageComposer());
        client.sendQueue(new BadgeInventoryMessageComposer(client.getPlayer().getInventory().getBadges()));
        client.sendQueue(new AchievementRequirementsMessageComposer(AchievementManager.getInstance().getAchievementGroups().values()));

        client.sendQueue(new FigureSetIdsMessageComposer(client.getPlayer().getWardrobe().getClothing()));
        client.getPlayer().getMessenger().sendStatus(true, client.getPlayer().getEntity() != null);

        if(client.getPlayer().getPermissions().getRank().alfaTool()){
            final HelperSession helperSession = new HelperSession(client.getPlayer().getId(), true, true, true);
            client.getPlayer().setHelperSession(helperSession);
            GuideManager.getInstance().startPlayerDuty(client.getPlayer().getHelperSession());
            client.sendQueue(new GuideToolsMessageComposer(true));
        }

        client.flush();
    }
}
