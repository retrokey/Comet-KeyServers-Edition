package com.cometproject.server.game.rooms.objects.items.types.floor;

import com.cometproject.api.game.rooms.entities.RoomEntityStatus;
import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFactory;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.websockets.WebSocketSessionManager;
import com.cometproject.server.network.websockets.packets.outgoing.BlurEffectWebPacket;
import com.cometproject.server.network.websockets.packets.outgoing.SurvivalSoundEffectWebPacket;
import com.cometproject.server.utilities.RandomUtil;


public class BlurFloorItem extends RoomItemFloor {
    private RoomEntity vendingEntity;
    private int state = -1;

    public BlurFloorItem(RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    @Override
    public boolean onInteract(RoomEntity entity, int requestData, boolean isWiredTrigger) {
        if (isWiredTrigger || entity == null) return false;

        PlayerEntity p = ((PlayerEntity) entity);

        Position posInFront = this.getPosition().squareInFront(this.getRotation());

        if (!posInFront.equals(entity.getPosition())) {
            entity.moveTo(posInFront.getX(), posInFront.getY());

            try {
                this.getRoom().getMapping().getTile(posInFront.getX(), posInFront.getY()).scheduleEvent(entity.getId(), (e) -> onInteract(e, requestData, false));
            } catch (Exception e) {
                // this isn't important, if we can't find the tile then we might as well just end it here.
            }
            return false;
        }

        int rotation = Position.calculateRotation(entity.getPosition().getX(), entity.getPosition().getY(), this.getPosition().getX(), this.getPosition().getY(), false);

        if (!entity.hasStatus(RoomEntityStatus.SIT) && !entity.hasStatus(RoomEntityStatus.LAY)) {
            entity.setBodyRotation(rotation);
            entity.setHeadRotation(rotation);

            entity.markNeedsUpdate();
        }

        p.getPlayer().getSession().send(new WhisperMessageComposer(-1, "Busca leche para que se te pase este efecto. Ay... qué pensarán tus padres.", 1));
        WebSocketSessionManager.getInstance().sendMessage(p.getPlayer().getSession().getWsChannel(), new BlurEffectWebPacket("smoke_start"));
        WebSocketSessionManager.getInstance().sendMessage(p.getPlayer().getSession().getWsChannel(),
                new SurvivalSoundEffectWebPacket("survivalSound", "zippo-strike"));

        this.getRoom().getEntities().broadcastMessage(new TalkMessageComposer(p.getId(), "<i>*se prende tremendo blunt*</i>", ChatEmotion.LAUGH, 5));

        this.vendingEntity = entity;

        this.state = 0;
        this.setTicks(RoomItemFactory.getProcessTime(1));
        return true;
    }

    @Override
    public void onTickComplete() {
        switch (this.state) {
            case 0: {
                this.getItemData().setData("1");
                this.sendUpdate();

                this.state = 1;
                this.setTicks(RoomItemFactory.getProcessTime(0.5));
                break;
            }

            case 1: {
                if (this.getDefinition().getVendingIds().length != 0) {
                    int vendingId = Integer.parseInt(this.getDefinition().getVendingIds()[RandomUtil.getRandomInt(0, this.getDefinition().getVendingIds().length - 1)].trim());
                    vendingEntity.carryItem(vendingId);
                }

                this.state = 2;
                this.setTicks(RoomItemFactory.getProcessTime(0.5));
                break;
            }

            case 2: {
                this.getItemData().setData("0");
                this.sendUpdate();

                this.state = 0;
                this.vendingEntity = null;
                break;
            }
        }
    }

    @Override
    public void onPlaced() {
        this.getItemData().setData("0");
    }
}
