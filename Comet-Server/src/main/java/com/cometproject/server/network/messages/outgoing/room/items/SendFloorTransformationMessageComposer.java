package com.cometproject.server.network.messages.outgoing.room.items;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.objects.items.types.DefaultFloorItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.AdjustableHeightFloorItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.MagicStackFloorItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.SoundMachineFloorItem;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;


public class SendFloorTransformationMessageComposer extends MessageComposer {
    private final PlayerEntity p;

    public SendFloorTransformationMessageComposer(PlayerEntity p) {
        this.p = p;
    }

    @Override
    public short getId() {
        return Composers.ObjectAddMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(2147418112 - p.getId());
        msg.writeInt(Integer.parseInt(p.getAttribute("item").toString()));
        msg.writeInt(p.getPosition().getX());
        msg.writeInt(p.getPosition().getY());
        msg.writeInt(2);

        msg.writeString(p.getPosition().getZ());
        msg.writeString(0);

        msg.writeInt(1);
        msg.writeInt(0);
        msg.writeString("1");

        msg.writeInt(-1);
        msg.writeInt(0);
        msg.writeInt(1);
        msg.writeString(p.getUsername());
    }
}
