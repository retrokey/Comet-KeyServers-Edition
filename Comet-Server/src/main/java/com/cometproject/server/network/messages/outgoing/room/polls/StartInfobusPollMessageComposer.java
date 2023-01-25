package com.cometproject.server.network.messages.outgoing.room.polls;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.polls.types.Poll;
import com.cometproject.server.game.polls.types.PollQuestion;
import com.cometproject.server.game.polls.types.questions.MultipleChoiceQuestion;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

import java.util.Map;

public class StartInfobusPollMessageComposer extends MessageComposer {
    final private Poll poll;
    public StartInfobusPollMessageComposer(Poll poll) {
        this.poll = poll;
    }

    @Override
    public short getId() {
        return Composers.StartInfobusPollMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {

        for (Map.Entry<Integer, PollQuestion> pollQuestion : this.poll.getPollQuestions().entrySet()) {
            msg.writeString(pollQuestion.getValue().getQuestion());

            final int optionSizes = pollQuestion.getValue() instanceof MultipleChoiceQuestion ? ((MultipleChoiceQuestion) pollQuestion.getValue()).getChoices().size() : 1;

            msg.writeInt(optionSizes);

            if (optionSizes != 0) {
                for (int i = 0; i < optionSizes; i++) {
                    String choice = ((MultipleChoiceQuestion) pollQuestion.getValue()).getChoices().get(i);

                    msg.writeInt(i);
                    msg.writeString(choice);
                }
            }
        }
        /*
            msg.writeInt(1); // Answer Id
            msg.writeString("Una cabra."); // Answer Value
            msg.writeInt(2);
            msg.writeString("A tu madre.");
            msg.writeInt(3);
            msg.writeString("A Kev XDXDXD");*/
    }
}
