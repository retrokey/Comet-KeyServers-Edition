package com.cometproject.server.network.messages.outgoing.room.pets;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.rooms.objects.entities.types.PetEntity;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class PetUpdateStatusComposer extends MessageComposer {

    private PetEntity entity;

    public PetUpdateStatusComposer(PetEntity entity) {
        this.entity = entity;
    }


    @Override
    public void compose(IComposer msg) {
        this.entity.composeUpdate(msg);
    }

    @Override
    public short getId() {
        return Composers.PetUpdateStatusComposer;
    }
}