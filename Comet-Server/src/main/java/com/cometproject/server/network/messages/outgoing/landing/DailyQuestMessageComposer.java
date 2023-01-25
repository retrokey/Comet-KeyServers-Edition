package com.cometproject.server.network.messages.outgoing.landing;

import com.cometproject.api.game.players.IPlayer;
import com.cometproject.api.game.quests.IQuest;
import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class DailyQuestMessageComposer extends MessageComposer {

    private final int campaignString;
    private final int campaignName;
    private final IPlayer player;
    private final IQuest quest;

    public DailyQuestMessageComposer(int campaignString, int campaignName, IQuest quest, IPlayer player) {
        this.campaignString = campaignString;
        this.campaignName = campaignName;
        this.quest = quest;
        this.player = player;
    }

    @Override
    public short getId() {
        return Composers.DailyQuestMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        this.quest.compose(player, msg);

        msg.writeInt(this.campaignString);
        msg.writeInt(this.campaignName);
    }
}
