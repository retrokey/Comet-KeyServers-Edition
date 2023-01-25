package com.cometproject.server.network.messages.incoming.polls;

import com.cometproject.server.game.polls.PollManager;
import com.cometproject.server.game.polls.types.Poll;
import com.cometproject.server.game.polls.types.PollQuestion;
import com.cometproject.server.game.polls.types.questions.MultipleChoiceQuestion;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.room.polls.GetInfobusPollResultsMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;
import com.cometproject.server.storage.queries.polls.PollDao;

public class GetInfobusPollsResultsMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        int answerId = msg.readInt();
        final Room room = client.getPlayer().getEntity().getRoom();

        switch (answerId) {
            case 1:
                client.getPlayer().getEntity().getRoom().getInfobusChoice1().add(client.getPlayer().getId());
                break;
            case 2:
                client.getPlayer().getEntity().getRoom().getInfobusChoice2().add(client.getPlayer().getId());
                break;
            case 3:
                client.getPlayer().getEntity().getRoom().getInfobusChoice3().add(client.getPlayer().getId());
                break;
        }

        Poll poll = PollManager.getInstance().getPollbyId(1);

        if (poll != null) {
            PollQuestion pollQuestion = poll.getPollQuestions().get(1);
            if (pollQuestion instanceof MultipleChoiceQuestion) {
                PollDao.saveAnswer(client.getPlayer().getId(), 1, 1, ((MultipleChoiceQuestion) pollQuestion).getChoices().get(answerId - 1));
            }

            for(PlayerEntity player : room.getEntities().getPlayerEntities()){
                if(room.hasAnsweredInfobus(player.getPlayerId()) && player != client.getPlayer().getEntity())
                    player.getPlayer().getSession().send(new GetInfobusPollResultsMessageComposer(room));
            }

            client.send(new GetInfobusPollResultsMessageComposer(room));
        }
    }
}