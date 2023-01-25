package com.cometproject.server.network.messages.outgoing.room.pets;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.rooms.objects.entities.types.PetEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;


public class PetInformationMessageComposer extends MessageComposer {

    private final PetEntity petEntity;
    private final PlayerEntity player;

    public PetInformationMessageComposer(final PetEntity petEntity) {
        this.petEntity = petEntity;
        this.player = null;
    }

    public PetInformationMessageComposer(final PlayerEntity playerEntity) {
        this.petEntity = null;
        this.player = playerEntity;
    }

    @Override
    public short getId() {
        return Composers.PetInformationMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        if (this.petEntity != null && this.petEntity.getData() != null) {
            this.petEntity.composeInformation(msg);
        } else {
            msg.writeInt(this.player.getPlayerId());
            msg.writeString(this.player.getUsername());
            msg.writeInt(20);
            msg.writeInt(20); // MAX_LEVEL
            msg.writeInt(0);
            msg.writeInt(200); // EXPERIENCE_GOAL
            msg.writeInt(0);
            msg.writeInt(100); // MAX_ENERGY
            msg.writeInt(100); // NUTRITION
            msg.writeInt(100); // MAX_NUTRITION
            msg.writeInt(0); // SCRATCHES
            msg.writeInt(this.player.getPlayerId());
            msg.writeInt(0); // AGE
            msg.writeString(this.player.getUsername());
            msg.writeInt(1);
            msg.writeBoolean(this.player.getMotto().toLowerCase().startsWith("rideable")); // HAS_SADDLE
            msg.writeBoolean(false); // HAS_RIDER
            msg.writeInt(0);

            // CAN ANYONE MOUNT?
            msg.writeInt(1); // yes = 1 no = 0

            msg.writeInt(0);
            msg.writeInt(0);
            msg.writeInt(0);
            msg.writeInt(0);
            msg.writeString("");
            msg.writeBoolean(false);
            msg.writeBoolean(true); // all can mount
            msg.writeInt(0);
            msg.writeString("");
            msg.writeBoolean(false);
            msg.writeInt(-1);
            msg.writeInt(-1);
            msg.writeInt(-1);
            msg.writeBoolean(false);

        }
    }
}
