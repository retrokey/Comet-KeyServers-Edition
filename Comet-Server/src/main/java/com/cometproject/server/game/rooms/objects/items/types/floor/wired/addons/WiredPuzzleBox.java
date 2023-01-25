package com.cometproject.server.game.rooms.objects.items.types.floor.wired.addons;

import com.cometproject.api.game.furniture.types.CrackableReward;
import com.cometproject.api.game.rooms.entities.RoomEntityStatus;
import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.pathfinding.Square;
import com.cometproject.server.game.rooms.objects.entities.pathfinding.types.ItemPathfinder;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.mapping.RoomTile;
import com.cometproject.server.network.messages.outgoing.notification.AlertMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.SlideObjectBundleMessageComposer;
import java.util.List;


public class WiredPuzzleBox extends RoomItemFloor
{
    public WiredPuzzleBox(RoomItemData itemData, Room room) {
        super(itemData, room);
    }


    private Position getNextPosition(Position nextPosition)
    {
        return nextPosition.squareInFront(this.getRotation());
    }

    private boolean isValidRoll(Position nextPosition) {
        List<Square> path = ItemPathfinder.getInstance().makePath(this, nextPosition);
        return !(path == null || path.isEmpty());
    }

    private static void roll(RoomItemFloor item, Position from, Position to, Room room) {
        room.getEntities().broadcastMessage(new SlideObjectBundleMessageComposer(from.copy(), to.copy(), 0, 0, item.getVirtualId()));
    }

    private static Position calculatePosition(int x, int y, int playerRotation) {
        return Position.calculatePosition(x, y, playerRotation, false, 1);
    }

    private void moveTo(Position pos, int rotation, RoomEntity entity, Position old)
    {
        RoomTile newTile = this.getRoom().getMapping().getTile(pos);

        if (newTile == null || !isValidRoll(pos))
            return;

        //pos.setZ(newTile.getStackHeight());

        roll(this, this.getPosition(), pos, this.getRoom());

        RoomTile tile = this.getRoom().getMapping().getTile(this.getPosition());

        this.getPosition().setX(pos.getX());
        this.getPosition().setY(pos.getY());

        if (tile != null) {
            tile.reload();
        }

        entity.moveTo(old);
        newTile.reload();

        // tell all other items on the new square that there's a new item. (good method of updating score...)
        for (RoomItemFloor floorItem : this.getRoom().getItems().getItemsOnSquare(pos.getX(), pos.getY())) {
            if(floorItem == this)
                continue;
            floorItem.onItemAddedToStack(this);
        }

        //this.getPosition().setZ(pos.getZ());

    }

    @Override
    public boolean onInteract(RoomEntity entity, int requestData, boolean isWiredTrigger) {
        if (isWiredTrigger || entity == null) return false;

        if (!this.getPosition().touching(entity.getPosition()))
            return true;

        if(entity.getPosition().squareInFront(entity.getBodyRotation()) != this.getPosition()){
            entity.lookTo(this.getPosition().getX(), this.getPosition().getY());
        }

        if (entity.getBodyRotation() % 2 != 0) {
            entity.moveTo(this.getPosition().getX() + 1, this.getPosition().getY());
            return true;
        }

        int bodyRotation = entity.getBodyRotation();

        Position currentPosition = new Position(this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ());
        Position newPosition;

        switch (bodyRotation){
            case 4:
                newPosition = new Position(this.getPosition().getX(), this.getPosition().getY() + 1);
                break;
            case 0:
                newPosition = new Position(this.getPosition().getX(), this.getPosition().getY() - 1);
                break;
            case 6:
                newPosition = new Position(this.getPosition().getX() - 1, this.getPosition().getY());
                break;
            case 2:
                newPosition = new Position(this.getPosition().getX() + 1, this.getPosition().getY());
                break;
            default:
                newPosition = new Position(this.getPosition().getX() + 1, this.getPosition().getY());
                break;
        }

        this.moveTo(newPosition, this.getRotation(), entity, currentPosition);
        return true;
    }

    @Override
    public void onPlaced() {
        this.getItemData().setData("0");
    }
}
