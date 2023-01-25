package com.cometproject.server.game.rooms.objects.items.types.floor;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFactory;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.objects.items.events.types.RollerFloorItemEvent;
import com.cometproject.server.game.rooms.objects.items.types.AdvancedFloorItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.RollableFloorItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.groups.GroupGateFloorItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.triggers.WiredTriggerWalksOffFurni;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.triggers.WiredTriggerWalksOnFurni;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.mapping.RoomTile;
import com.cometproject.server.network.messages.outgoing.room.items.SlideObjectBundleMessageComposer;
import com.cometproject.server.utilities.collections.ConcurrentHashSet;
import java.util.List;
import java.util.Set;

public class RollerFloorItem
        extends AdvancedFloorItem<RollerFloorItemEvent> {
    private boolean hasRollScheduled = false;
    private long lastTick = 0L;
    private final Set<Integer> entitiesOnRoller = new ConcurrentHashSet<Integer>();
    private final RollerFloorItemEvent event = new RollerFloorItemEvent(this.getTickCount());

    public RollerFloorItem(RoomItemData itemData, Room room) {
        super(itemData, room);
        this.queueEvent(this.event);
    }

    @Override
    public void onLoad() {
        this.event.setTotalTicks(this.getTickCount());
        this.queueEvent(this.event);
    }

    @Override
    public void onPlaced() {
        this.event.setTotalTicks(this.getTickCount());
        this.queueEvent(this.event);
    }

    @Override
    public void onEntityStepOn(RoomEntity entity) {
        this.entitiesOnRoller.add(entity.getId());
        this.event.setTotalTicks(this.getTickCount());
    }

    @Override
    public void onEntityStepOff(RoomEntity entity) {
        if (!this.entitiesOnRoller.contains(entity.getId())) {
            return;
        }
        this.entitiesOnRoller.remove(entity.getId());
    }

    @Override
    public void onItemAddedToStack(RoomItemFloor floorItem) {
        this.event.setTotalTicks(this.getTickCount());
    }

    @Override
    public void onEventComplete(RollerFloorItemEvent event) {
        this.handleItems();
        this.handleEntities();
        event.setTotalTicks(this.getTickCount());
        this.queueEvent(event);
    }

    private void handleEntities() {
        RoomTile tile = this.getTile();
        Position sqInfront = this.getPosition().squareInFront(this.getRotation());
        if (!this.getRoom().getMapping().isValidPosition(sqInfront)) {
            return;
        }
        boolean retry = false;
        List<RoomEntity> entities = this.getRoom().getEntities().getEntitiesAt(this.getPosition());
        for (RoomEntity entity : entities) {
            if (entity.getPosition().getX() != this.getPosition().getX() && entity.getPosition().getY() != this.getPosition().getY() || !this.entitiesOnRoller.contains(entity.getId()) || entity.getPositionToSet() != null) continue;
            if (!this.getRoom().getMapping().isValidStep((Integer)entity.getId(), entity.getPosition(), sqInfront, true, false, false, null, true) || this.getRoom().getEntities().positionHasEntity(sqInfront)) {
                retry = true;
                break;
            }
            if (entity.isWalking()) continue;
            if (sqInfront.getX() == this.getRoom().getModel().getDoorX() && sqInfront.getY() == this.getRoom().getModel().getDoorY()) {
                entity.leaveRoom(false, false, true);
                continue;
            }
            WiredTriggerWalksOffFurni.executeTriggers(entity, this);
            for (RoomItemFloor nextItem : this.getRoom().getItems().getItemsOnSquare(sqInfront.getX(), sqInfront.getY())) {
                if (nextItem instanceof GroupGateFloorItem) break;
                if (entity instanceof PlayerEntity) {
                    WiredTriggerWalksOnFurni.executeTriggers(entity, nextItem);
                }
                nextItem.onEntityStepOn(entity);
            }
            double toHeight = this.getRoom().getMapping().getTile(sqInfront.getX(), sqInfront.getY()).getWalkHeight();
            RoomTile oldTile = this.getRoom().getMapping().getTile(entity.getPosition().getX(), entity.getPosition().getY());
            RoomTile newTile = this.getRoom().getMapping().getTile(sqInfront.getX(), sqInfront.getY());
            if (oldTile != null) {
                oldTile.getEntities().remove(entity);
            }
            if (newTile != null) {
                newTile.getEntities().add(entity);
            }
            this.getRoom().getEntities().broadcastMessage(new SlideObjectBundleMessageComposer(entity.getPosition(), new Position(sqInfront.getX(), sqInfront.getY(), toHeight), this.getVirtualId(), entity.getId(), 0));
            entity.setPosition(new Position(sqInfront.getX(), sqInfront.getY(), toHeight));
            this.onEntityStepOff(entity);
        }
        if (retry) {
            this.setTicks(this.getTickCount());
        }
    }

    private void handleItems() {
        List<RoomItemFloor> floorItems = this.getRoom().getItems().getItemsOnSquare(this.getPosition().getX(), this.getPosition().getY());
        if (floorItems.size() < 2) {
            return;
        }
        int rollerCount = 0;
        for (RoomItemFloor f : floorItems) {
            if (!(f instanceof RollerFloorItem)) continue;
            ++rollerCount;
        }
        if (rollerCount > 1) {
            return;
        }
        Position sqInfront = this.getPosition().squareInFront(this.getRotation());
        boolean noItemsOnNext = false;
        for (RoomItemFloor floor : floorItems) {
            if (floor.getPosition().getX() != this.getPosition().getX() && floor.getPosition().getY() != this.getPosition().getY() || floor instanceof RollerFloorItem || floor.getPosition().getZ() <= this.getPosition().getZ() || !floor.getDefinition().canStack() && !(floor instanceof RollableFloorItem) && floor.getTile().getTopItem() != floor.getId()) continue;
            double height = floor.getPosition().getZ();
            List<RoomItemFloor> itemsSq = this.getRoom().getItems().getItemsOnSquare(sqInfront.getX(), sqInfront.getY());
            boolean hasRoller = false;
            for (RoomItemFloor iq : itemsSq) {
                if (!(iq instanceof RollerFloorItem)) continue;
                hasRoller = true;
                if (iq.getPosition().getZ() == this.getPosition().getZ()) continue;
                height -= this.getPosition().getZ();
                height += iq.getPosition().getZ();
            }
            if (!hasRoller || noItemsOnNext) {
                height -= 0.5;
                noItemsOnNext = true;
            }
            if (!this.getRoom().getMapping().isValidStep(null, new Position(floor.getPosition().getX(), floor.getPosition().getY(), floor.getPosition().getZ()), sqInfront, true, false, false, null, true) || this.getRoom().getEntities().positionHasEntity(sqInfront)) {
                this.setTicks(this.getTickCount());
                return;
            }
            this.getRoom().getEntities().broadcastMessage(new SlideObjectBundleMessageComposer(new Position(floor.getPosition().getX(), floor.getPosition().getY(), floor.getPosition().getZ()), new Position(sqInfront.getX(), sqInfront.getY(), height), this.getVirtualId(), 0, floor.getVirtualId()));
            floor.getPosition().setX(sqInfront.getX());
            floor.getPosition().setY(sqInfront.getY());
            floor.getPosition().setZ(height);
            floor.save();
        }
        this.getRoom().getMapping().updateTile(this.getPosition().getX(), this.getPosition().getY());
        this.getRoom().getMapping().updateTile(sqInfront.getX(), sqInfront.getY());
        for (RoomItemFloor nextItem : this.getRoom().getItems().getItemsOnSquare(sqInfront.getX(), sqInfront.getY())) {
            for (RoomItemFloor floor : floorItems) {
                nextItem.onItemAddedToStack(floor);
            }
        }
    }

    private int getTickCount() {
        return RoomItemFactory.getProcessTime(this.getRoom().getData().getRollerSpeed() / 2);
    }
}