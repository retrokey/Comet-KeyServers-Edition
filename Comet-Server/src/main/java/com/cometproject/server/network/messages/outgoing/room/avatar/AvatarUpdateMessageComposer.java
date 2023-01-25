package com.cometproject.server.network.messages.outgoing.room.avatar;

import com.cometproject.api.game.rooms.entities.RoomEntityStatus;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.network.messages.outgoing.room.items.SlideObjectBundleMessageComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;
import java.util.Map;


public class AvatarUpdateMessageComposer extends MessageComposer {

    private final int count;
    private final AvatarState singleEntity;
    private final List<AvatarState> entities;

    public AvatarUpdateMessageComposer(final Collection<RoomEntity> entities) {
        this.entities = Lists.newArrayList();

        for (RoomEntity entity : entities) {
            if (!entity.isVisible() || !entity.sendUpdateMessage()) {
                entity.setSendUpdateMessage(true);
                continue;
            }

            if(entity.isTransformed()){
                entity.getRoom().getEntities().broadcastMessage(new SlideObjectBundleMessageComposer(new Position(entity.getPosition().getX(),entity.getPosition().getY()), new Position(entity.getPosition().getX(),entity.getPosition().getY()), 0, 0, 2147418112 - entity.getId()));
            }

            this.entities.add(new AvatarState(entity));
        }

        this.count = this.entities.size();
        this.singleEntity = null;
    }

    public AvatarUpdateMessageComposer(final RoomEntity entity) {
        this.count = 1;
        this.singleEntity = new AvatarState(entity);
        this.entities = null;
    }

    @Override
    public short getId() {
        return Composers.UserUpdateMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(this.count);

        if (this.singleEntity != null) {
            this.composeEntity(msg, this.singleEntity);
        } else {
            for (final AvatarState entity : this.entities) {
                this.composeEntity(msg, entity);
            }
        }
    }

    private void composeEntity(IComposer msg, AvatarState entity) {
        msg.writeInt(entity.getId());

        msg.writeInt(entity.getPosition().getX());
        msg.writeInt(entity.getPosition().getY());
        msg.writeString(String.valueOf(entity.getPosition().getZ()));

        msg.writeInt(entity.getHeadRotation());
        msg.writeInt(entity.getBodyRotation());

        msg.writeString(entity.getStatusString());
    }

    private static class AvatarState {
        private final int id;
        private final Position position;
        private final int headRotation;
        private final int bodyRotation;
        private final String statusString;

        public AvatarState(RoomEntity entity) {
            this.id = entity.getId();
            this.position = entity.getPosition().copy();
            this.headRotation = entity.getHeadRotation();
            this.bodyRotation = entity.getBodyRotation();

            StringBuilder statusString = new StringBuilder();
            statusString.append("/");

            for (Map.Entry<RoomEntityStatus, String> status : entity.getStatuses().entrySet()) {

                statusString.append(status.getKey().getStatusCode());

                if (!status.getValue().isEmpty()) {
                    statusString.append(" ");
                    statusString.append(status.getValue());
                }

                statusString.append("/");
            }

            statusString.append("/");

            this.statusString = statusString.toString();
        }

        public int getId() {
            return id;
        }

        public Position getPosition() {
            return position;
        }

        public int getHeadRotation() {
            return headRotation;
        }

        public int getBodyRotation() {
            return bodyRotation;
        }

        public String getStatusString() {
            return statusString;
        }

        @Override
        public String toString() {
            return String.format("AvatarState[%s, %s, %s, %s, %s]", this.id, this.position, this.headRotation, this.bodyRotation, this.statusString);
        }
    }
}
