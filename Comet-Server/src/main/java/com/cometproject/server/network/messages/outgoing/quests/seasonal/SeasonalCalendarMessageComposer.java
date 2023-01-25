package com.cometproject.server.network.messages.outgoing.quests.seasonal;

import com.cometproject.api.game.players.IPlayer;
import com.cometproject.api.game.quests.IQuest;
import com.cometproject.api.game.quests.QuestReward;
import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.game.quests.QuestManager;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class SeasonalCalendarMessageComposer extends MessageComposer {
    private int type;

    public SeasonalCalendarMessageComposer(int type) {
        this.type = type;
    }

    @Override
    public short getId() {
        return Composers.SeasonalCalendarMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {

        msg.writeInt(1);

        msg.writeString("1");
        msg.writeInt(this.type); // status
        msg.writeInt(1); // status Additional string type?
        msg.writeInt(103); // Currency
        msg.writeInt(1); // ID
        msg.writeBoolean(false); // Accepted / Not
        msg.writeString("test"); // Accepted / Not
        msg.writeString("photo"); // Thumbnail quest
        msg.writeInt(50); // Amount of the prize.
        msg.writeString("addition"); // Thumbnail addition on status change?
        msg.writeInt(1); // Current day
        msg.writeInt(1); // Total days
        msg.writeInt(1); // Sort order

        msg.writeString("catalog"); // Catalog page name.
        msg.writeString("catalog"); // Quest name?.
        msg.writeBoolean(false); // Has hint.
        msg.writeInt(0); // Timestamp.
    }
}
