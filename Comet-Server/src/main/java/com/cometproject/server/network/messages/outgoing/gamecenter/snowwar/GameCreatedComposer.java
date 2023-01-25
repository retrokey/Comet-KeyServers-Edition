package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.games.snowwar.RoomQueue;
import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse.SerializeGame2;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

    public class GameCreatedComposer extends MessageComposer {
        private final RoomQueue roomQueue;

        public GameCreatedComposer(RoomQueue roomQueue) {
            this.roomQueue = roomQueue;
        }

        @Override
        public void compose(IComposer msg) {
            SerializeGame2.parse(msg, this.roomQueue);
        }

        @Override
        public short getId() {
            return Composers.GameOwnerSerializationMessageComposer;
        }
    }