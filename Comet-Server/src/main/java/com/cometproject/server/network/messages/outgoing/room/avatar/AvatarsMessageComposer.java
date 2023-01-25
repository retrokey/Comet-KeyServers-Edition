package com.cometproject.server.network.messages.outgoing.room.avatar;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;
import com.google.common.collect.Lists;

import java.util.List;


public class AvatarsMessageComposer extends MessageComposer {
    private final RoomEntity singleEntity;
    private final List<RoomEntity> entities;

    public AvatarsMessageComposer(final Room room) {
        this.entities = Lists.newArrayList();

        for (RoomEntity entity : room.getEntities().getAllEntities().values()) {
            if(entity.isTransformed())
                continue;

            if (entity.isVisible()) {
                if (entity instanceof PlayerEntity) {
                    if (((PlayerEntity) entity).getPlayer() == null) continue;
                }

                this.entities.add(entity);
            }
        }

        this.singleEntity = null;
    }

    public AvatarsMessageComposer(RoomEntity entity) {
        this.singleEntity = entity;
        this.entities = null;
    }

    public AvatarsMessageComposer(List<RoomEntity> entities) {
        this.singleEntity = null;
        this.entities = entities;
    }

    @Override
    public short getId() {
        return Composers.UsersMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        if (this.singleEntity != null) {
            msg.writeInt(1);

            this.singleEntity.compose(msg);
        } else {
            msg.writeInt(this.entities.size());
            for (RoomEntity entity : this.entities) {
                entity.compose(msg);
            }
        }
    }
}
