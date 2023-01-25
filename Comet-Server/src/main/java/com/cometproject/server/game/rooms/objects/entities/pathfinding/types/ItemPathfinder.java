package com.cometproject.server.game.rooms.objects.entities.pathfinding.types;

import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.game.rooms.objects.RoomObject;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.pathfinding.Pathfinder;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.objects.items.types.floor.RollableFloorItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.VIPGateFloorItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.football.FootballFloorItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.groups.GroupGateFloorItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.actions.WiredActionChase;
import com.cometproject.server.game.rooms.types.mapping.RoomEntityMovementNode;
import com.cometproject.server.game.rooms.types.mapping.RoomTile;
import com.cometproject.api.game.rooms.models.RoomTileState;

public class ItemPathfinder extends Pathfinder {
    private static ItemPathfinder pathfinderInstance;

    public static ItemPathfinder getInstance() {
        if (pathfinderInstance == null) {
            pathfinderInstance = new ItemPathfinder();
        }

        return pathfinderInstance;
    }

    @Override
    public boolean isValidStep(RoomObject roomFloorObject, Position from, Position to, boolean lastStep, boolean isRetry) {
        if (from.getX() == to.getX() && from.getY() == to.getY()) {
            return true;
        }

        if (!(to.getX() < roomFloorObject.getRoom().getModel().getSquareState().length)) {
            return false;
        }

        if ((!roomFloorObject.getRoom().getMapping().isValidPosition(to) || (roomFloorObject.getRoom().getModel().getSquareState()[to.getX()][to.getY()] == RoomTileState.INVALID))) {
            return false;
        }

        final int rotation = Position.calculateRotation(from, to);

        if (rotation == 1 || rotation == 3 || rotation == 5 || rotation == 7) {
            RoomTile left = null;
            RoomTile right = null;

            switch (rotation) {
                case 1:
                    left = roomFloorObject.getRoom().getMapping().getTile(from.squareInFront(rotation + 1));
                    right = roomFloorObject.getRoom().getMapping().getTile(to.squareBehind(rotation + 1));
                    break;

                case 3:
                    left = roomFloorObject.getRoom().getMapping().getTile(to.squareBehind(rotation + 1));
                    right = roomFloorObject.getRoom().getMapping().getTile(to.squareBehind(rotation - 1));
                    break;

                case 5:
                    left = roomFloorObject.getRoom().getMapping().getTile(from.squareInFront(rotation - 1));
                    right = roomFloorObject.getRoom().getMapping().getTile(to.squareBehind(rotation - 1));
                    break;

                case 7:
                    left = roomFloorObject.getRoom().getMapping().getTile(to.squareBehind(0));
                    right = roomFloorObject.getRoom().getMapping().getTile(from.squareInFront(rotation - 1));
                    break;
            }

            if (left != null && right != null) {
                if (left.getMovementNode() != RoomEntityMovementNode.OPEN && right.getMovementNode() != RoomEntityMovementNode.OPEN)
                    return false;
            }
        }

        RoomTile tile = roomFloorObject.getRoom().getMapping().getTile(to.getX(), to.getY());

        if (tile == null) {
            return false;
        }

        if (roomFloorObject instanceof FootballFloorItem) {
            for (RoomItemFloor floor : tile.getItems()) {
                if (floor instanceof GroupGateFloorItem || floor instanceof VIPGateFloorItem) {
                    return false;
                }
            }

            if (tile.getItems().size() == 1) {
                return tile.getStackHeight() <= 0.5 && tile.canPlaceItemHere();
            }
        }

        if (roomFloorObject instanceof WiredActionChase) {
            int target = ((WiredActionChase) roomFloorObject).getTargetId();

            if (target != -1) {
                for (RoomEntity entity : tile.getEntities()) {
                    if (entity.getId() != target) {
                        return false;
                    }
                }
            }
        }

        if (roomFloorObject instanceof RollableFloorItem) {
            for (RoomEntity entity : tile.getEntities()) {
                return false;
            }
        }

        if (tile.getMovementNode() == RoomEntityMovementNode.CLOSED || (tile.getMovementNode() == RoomEntityMovementNode.END_OF_ROUTE && !lastStep)) {
            return false;
        }

        final double fromHeight = roomFloorObject.getRoom().getMapping().getStepHeight(from);
        final double toHeight = roomFloorObject.getRoom().getMapping().getStepHeight(to);

        return !(fromHeight < toHeight) || !((toHeight - fromHeight) > 1.0);
    }
}
