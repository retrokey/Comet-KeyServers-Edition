package com.cometproject.server.network.messages.outgoing.room.polls;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class GetInfobusPollResultsMessageComposer extends MessageComposer {
    final Room room;
    public GetInfobusPollResultsMessageComposer(Room room) {
        this.room = room;
    }

    @Override
    public short getId() {
        return Composers.GetInfobusPollResultsMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        int totalVotes = this.room.getInfobusChoice1().size() + this.room.getInfobusChoice2().size() + this.room.getInfobusChoice3().size();
        msg.writeString("Test"); // Question
        msg.writeInt(3); // Amount of answers

        msg.writeInt(0); // Answer Id
        msg.writeString(""); // Answer Value
        msg.writeInt(this.room.getInfobusChoice1().size());

        msg.writeInt(0);
        msg.writeString("");
        msg.writeInt(this.room.getInfobusChoice2().size());

        msg.writeInt(0);
        msg.writeString("");
        msg.writeInt(this.room.getInfobusChoice3().size());

        msg.writeInt(totalVotes); // Total Votes
    }
}
