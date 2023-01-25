package com.cometproject.server.game.rooms.types.components;

import com.cometproject.api.config.CometSettings;
import com.cometproject.api.game.furniture.types.FurnitureDefinition;
import com.cometproject.api.game.furniture.types.ItemType;
import com.cometproject.api.game.furniture.types.LimitedEditionItem;
import com.cometproject.api.game.players.data.components.inventory.PlayerItem;
import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.composers.catalog.UnseenItemsMessageComposer;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.players.types.Player;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.pathfinding.AffectedTile;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItem;
import com.cometproject.server.game.rooms.objects.items.RoomItemFactory;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.objects.items.RoomItemWall;
import com.cometproject.server.game.rooms.objects.items.types.floor.*;
import com.cometproject.server.game.rooms.objects.items.types.floor.games.banzai.BanzaiTileFloorItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.WiredFloorItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.triggers.WiredTriggerWalksOnFurni;
import com.cometproject.server.game.rooms.objects.items.types.wall.MoodlightWallItem;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.mapping.RoomTile;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.AvatarUpdateMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.engine.UpdateStackMapMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.RemoveFloorItemMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.RemoveWallItemMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.SendFloorItemMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.SendWallItemMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.inventory.UpdateInventoryMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.storage.cache.objects.items.FloorItemDataObject;
import com.cometproject.server.storage.cache.objects.items.WallItemDataObject;
import com.cometproject.storage.api.StorageContext;
import com.cometproject.storage.api.data.Data;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class ItemsComponent {

    private final Logger LOGGER;
    private final Map<Long, RoomItemFloor> floorItems = new ConcurrentHashMap<>();
    private final Map<Long, RoomItemWall> wallItems = new ConcurrentHashMap<>();
    private final Map<Integer, String> itemOwners = new ConcurrentHashMap<>();
    private Room room;
    private Map<Class<? extends RoomItemFloor>, Set<Long>> itemClassIndex = new ConcurrentHashMap<>();
    private Map<String, Set<Long>> itemInteractionIndex = new ConcurrentHashMap<>();
    private long soundMachineId = 0;
    private long moodlightId;

    public ItemsComponent(Room room) {
        this.room = room;
        this.LOGGER = LoggerFactory.getLogger("Room Items Component [" + room.getData().getName() + "]");

        if (room.getCachedData() != null) {
            for (FloorItemDataObject floorItemDataObject : room.getCachedData().getFloorItems()) {
                final RoomItemData data = new RoomItemData(floorItemDataObject.getId(),
                        floorItemDataObject.getItemDefinitionId(),
                        floorItemDataObject.getOwner(),
                        floorItemDataObject.getOwnerName(),
                        floorItemDataObject.getPosition(),
                        floorItemDataObject.getRotation(),
                        floorItemDataObject.getData(), "", floorItemDataObject.getLimitedEditionItemData());

                this.floorItems.put(floorItemDataObject.getId(), RoomItemFactory.createFloor(
                        data, room, ItemManager.getInstance().getDefinition(floorItemDataObject.getItemDefinitionId())));
            }

            for (WallItemDataObject wallItemDataObject : room.getCachedData().getWallItems()) {
                final RoomItemData data = new RoomItemData(wallItemDataObject.getId(),
                        wallItemDataObject.getItemDefinitionId(),
                        wallItemDataObject.getOwner(),
                        wallItemDataObject.getOwnerName(),
                        new Position(),
                        0,
                        wallItemDataObject.getData(), wallItemDataObject.getWallPosition(),
                        wallItemDataObject.getLimitedEditionItemData());

                this.wallItems.put(wallItemDataObject.getId(), RoomItemFactory.createWall(data, room,
                        ItemManager.getInstance().getDefinition(wallItemDataObject.getItemDefinitionId())));
            }
        } else {
            final Data<List<RoomItemData>> items = Data.createEmpty();

            StorageContext.getCurrentContext().getRoomItemRepository().getItemsByRoomId(this.room.getId(), items::set);

            if (items.has()) {
                for (RoomItemData roomItem : items.get()) {
                    final FurnitureDefinition itemDefinition = ItemManager.getInstance().getDefinition(roomItem.getItemId());

                    if (itemDefinition == null) continue;

                    if (itemDefinition.getItemType() == ItemType.FLOOR)
                        this.floorItems.put(roomItem.getId(), RoomItemFactory.createFloor(roomItem, room, itemDefinition));
                    else if (itemDefinition.getItemType() == ItemType.WALL)
                        this.wallItems.put(roomItem.getId(), RoomItemFactory.createWall(roomItem, room, itemDefinition));
                }
            }
        }

        for (RoomItemFloor floorItem : this.floorItems.values()) {
            if (floorItem instanceof SoundMachineFloorItem) {
                soundMachineId = floorItem.getId();
            }

            if (floorItem instanceof WiredFloorItem) {
                List<Long> itemsToRemove = Lists.newArrayList();

                for (long selectedItemId : ((WiredFloorItem) floorItem).getWiredData().getSelectedIds()) {
                    final RoomItemFloor floor = this.getFloorItem(selectedItemId);

                    if (floor != null) {
                        floor.getWiredItems().add(floorItem.getId());
                    } else {
                        itemsToRemove.add(selectedItemId);
                    }
                }

                for (long itemId : itemsToRemove) {
                    ((WiredFloorItem) floorItem).getWiredData().getSelectedIds().remove(itemId);
                }

                itemsToRemove.clear();
            }

            this.indexItem(floorItem);
        }
    }

    public void onLoaded() {
        for (RoomItemFloor floorItem : floorItems.values()) {
            floorItem.onLoad();
        }

        for (RoomItemWall wallItem : wallItems.values()) {
            wallItem.onLoad();
        }
    }

    public void dispose() {
        for (RoomItemFloor floorItem : floorItems.values()) {
            ItemManager.getInstance().disposeItemVirtualId(floorItem.getId());
            floorItem.onUnload();
        }

        for (RoomItemWall wallItem : wallItems.values()) {
            ItemManager.getInstance().disposeItemVirtualId(wallItem.getId());
            wallItem.onUnload();
        }

        this.floorItems.clear();
        this.wallItems.clear();

        for (Set<Long> itemIds : this.itemClassIndex.values()) {
            itemIds.clear();
        }

        for (Set<Long> itemInteractions : itemInteractionIndex.values()) {
            itemInteractions.clear();
        }

        this.itemOwners.clear();
        this.itemInteractionIndex.clear();
        this.itemClassIndex.clear();
    }

    public boolean setMoodlight(long moodlight) {
        if (this.moodlightId != 0)
            return false;

        this.moodlightId = moodlight;
        return true;
    }

    public boolean removeMoodlight() {
        if (this.moodlightId == 0) {
            return false;
        }

        this.moodlightId = 0;
        return true;
    }

    public void commit() {
        if (!CometSettings.storageItemQueueEnabled) {
            return;
        }

        /*List<RoomItem> floorItems = new ArrayList<>();

        for (RoomItemFloor floorItem : this.floorItems.values()) {
            if (floorItem.hasQueuedSave()) {
                floorItems.add(floorItem);

                ItemStorageQueue.getInstance().unqueue(floorItem);
            }
        }

        if (floorItems.size() != 0) {
            RoomItemData.saveFloorItems(floorItems);
        }

        floorItems.clear();*/
    }

    public boolean isMoodlightMatches(RoomItem item) {
        if (this.moodlightId == 0) {
            return false;
        }

        return (this.moodlightId == item.getId());
    }

    public MoodlightWallItem getMoodlight() {
        return (MoodlightWallItem) this.getWallItem(this.moodlightId);
    }

    public RoomItemFloor addFloorItem(long id, int baseId, Room room, int ownerId, String ownerName, int x, int y, int rot, double height, String data, LimitedEditionItem limitedEditionItem) {
        final RoomItemData itemData = new RoomItemData(id, baseId, ownerId, ownerName, new Position(x, y, height), rot, data, "", limitedEditionItem);

        RoomItemFloor floor = RoomItemFactory.createFloor(itemData, room, ItemManager.getInstance().getDefinition(baseId));

        if (floor == null) return null;

        this.floorItems.put(id, floor);
        this.indexItem(floor);

        return floor;
    }

    public RoomItemWall addWallItem(long id, int baseId, Room room, int ownerId, String ownerName, String position, String data) {
        final RoomItemData itemData = new RoomItemData(id, baseId, ownerId, ownerName, new Position(), 0, data, position, null);
        RoomItemWall wall = RoomItemFactory.createWall(itemData, room, ItemManager.getInstance().getDefinition(baseId));
        this.getWallItems().put(id, wall);

        return wall;
    }

    public List<RoomItemFloor> getItemsOnSquare(int x, int y) {
        RoomTile tile = this.getRoom().getMapping().getTile(x, y);

        if (tile == null) {
            return Lists.newArrayList();
        }

        return new ArrayList<>(tile.getItems());
    }

    public RoomItemFloor getFloorItem(int id) {
        Long itemId = ItemManager.getInstance().getItemIdByVirtualId(id);

        if (itemId == null) {
            return null;
        }

        return this.floorItems.get(itemId);
    }

    public RoomItemWall getWallItem(int id) {
        Long itemId = ItemManager.getInstance().getItemIdByVirtualId(id);

        if (itemId == null) {
            return null;
        }

        return this.wallItems.get(itemId);
    }

    public RoomItemFloor getFloorItem(long id) {
        return this.floorItems.get(id);
    }

    public RoomItemWall getWallItem(long id) {
        return this.wallItems.get(id);
    }

    public List<RoomItemFloor> getByInteraction(String interaction) {
        List<RoomItemFloor> items = new ArrayList<>();

        for (RoomItemFloor floorItem : this.floorItems.values()) {
            if (floorItem == null || floorItem.getDefinition() == null) continue;

            if (floorItem.getDefinition().getInteraction().equals(interaction)) {
                items.add(floorItem);
            } else if (interaction.contains("%")) {
                if (interaction.startsWith("%") && floorItem.getDefinition().getInteraction().endsWith(interaction.replace("%", ""))) {
                    items.add(floorItem);
                } else if (interaction.endsWith("%") && floorItem.getDefinition().getInteraction().startsWith(interaction.replace("%", ""))) {
                    items.add(floorItem);
                }
            }
        }

        return items;
    }

    public <T extends RoomItemFloor> List<T> getByClass(Class<T> clazz) {
        List<T> items = new ArrayList<>();

        if (this.itemClassIndex.containsKey(clazz)) {
            for (long itemId : this.itemClassIndex.get(clazz)) {
                RoomItemFloor floorItem = this.getFloorItem(itemId);

                if (floorItem == null || floorItem.getDefinition() == null) continue;

                if (!floorItem.getClass().equals(clazz)) {
                    continue;
                }

                T item = ((T) floorItem);
                items.add(item);
            }
        }

        return items;
    }

    public void removeItem(RoomItemWall item, int ownerId, Session client) {
        StorageContext.getCurrentContext().getRoomItemRepository().removeItemFromRoom(item.getId(), ownerId, item.getItemData().getData());

        room.getEntities().broadcastMessage(new RemoveWallItemMessageComposer(ItemManager.getInstance().getItemVirtualId(item.getId()), ownerId));
        this.getWallItems().remove(item.getId());

        if (client != null && client.getPlayer() != null) {
            client.getPlayer().getInventory().add(item.getId(), item.getItemData().getItemId(), item.getItemData().getData(), item.getLimitedEditionItemData());
            client.send(new UpdateInventoryMessageComposer());
        }
    }

    public void removeItem(RoomItemFloor item, Session client) {
        removeItem(item, client, true);
    }

    public void removeItem(RoomItemFloor item, Session client, boolean toInventory) {
        if (item instanceof SoundMachineFloorItem) {
            this.soundMachineId = 0;
        }

        if (item.getWiredItems().size() != 0) {
            for (long wiredItem : item.getWiredItems()) {
                final WiredFloorItem floorItem = (WiredFloorItem) this.getFloorItem(wiredItem);

                if (floorItem != null) {
                    floorItem.getWiredData().getSelectedIds().remove(item.getId());
                }
            }
        }

        removeItem(item, client, toInventory, false);
    }

    public void removeItem(RoomItemFloor item, Session session, boolean toInventory, boolean delete) {
        List<RoomEntity> affectEntities = room.getEntities().getEntitiesAt(item.getPosition());
        List<Position> tilesToUpdate = new ArrayList<>();

        tilesToUpdate.add(new Position(item.getPosition().getX(), item.getPosition().getY(), 0d));

        for (RoomEntity entity : affectEntities) {
            item.onEntityStepOff(entity);
        }

        if (item instanceof SoundMachineFloorItem) {
            if (this.soundMachineId == item.getId()) {
                this.soundMachineId = 0;
            }
        }

        for (AffectedTile tile : AffectedTile.getAffectedTilesAt(item.getDefinition().getLength(), item.getDefinition().getWidth(), item.getPosition().getX(), item.getPosition().getY(), item.getRotation())) {
            List<RoomEntity> entitiesOnItem = room.getEntities().getEntitiesAt(new Position(tile.x, tile.y));
            tilesToUpdate.add(new Position(tile.x, tile.y, 0d));

            for (RoomEntity entity : entitiesOnItem) {
                item.onEntityStepOff(entity);
            }
        }

        Session client = session;
        int owner = item.getItemData().getOwnerId();

        if (session != null) {
            if (owner != session.getPlayer().getId()) {
                client = NetworkManager.getInstance().getSessions().getByPlayerId(owner);
            }
        }

        this.getRoom().getEntities().broadcastMessage(new RemoveFloorItemMessageComposer(item.getVirtualId(), (session != null) ? owner : 0));
        this.getFloorItems().remove(item.getId());

        StorageContext.getCurrentContext().getRoomItemRepository().removeItemFromRoom(item.getId(), owner, item.getDataObject());

        if (toInventory && client != null) {
            final PlayerItem playerItem = client.getPlayer().getInventory().add(item.getId(), item.getItemData().getItemId(), item.getItemData().getData(), item instanceof GiftFloorItem ? ((GiftFloorItem) item).getGiftData() : null, item.getLimitedEditionItemData());
            client.sendQueue(new UpdateInventoryMessageComposer());
            client.sendQueue(new UnseenItemsMessageComposer(Sets.newHashSet(playerItem), ItemManager.getInstance()));
            client.flush();
        } else {
            if (delete)
                StorageContext.getCurrentContext().getRoomItemRepository().deleteItem(item.getId());
        }

        for (Position tileToUpdate : tilesToUpdate) {
            final RoomTile tileInstance = this.room.getMapping().getTile(tileToUpdate.getX(), tileToUpdate.getY());

            if (tileInstance != null) {
                tileInstance.reload();

                room.getEntities().broadcastMessage(new UpdateStackMapMessageComposer(tileInstance));
            }
        }
    }

    public boolean moveFloorItemWired(RoomItemFloor item, Position newPosition, int rotation, boolean save, boolean autoheight, boolean limit) {
        if (item == null) return false;

        RoomTile tile = this.getRoom().getMapping().getTile(newPosition.getX(), newPosition.getY());

        if (autoheight && !this.verifyItemPosition(item.getDefinition(), item, tile, item.getPosition(), rotation)) {
            return false;
        }

        double height;

        if (autoheight)
            height = tile.getStackHeight(item);
        else
            height = newPosition.getZ();


        if (limit && (tile.getStackHeight() - tile.getTileHeight()) > 0.2)
            return false;


        if (autoheight && this.getRoom().getEntities().getEntitiesAt(newPosition).size() > 0)
            return false;

        return this.moveFloorItemAfter(item, newPosition, height, rotation, save);
    }

    private boolean moveFloorItemAfter(RoomItemFloor item, Position newPosition, double height, int rotation, boolean save) {

        List<RoomItemFloor> floorItemsAt = this.getItemsOnSquare(newPosition.getX(), newPosition.getY());
        RoomTile oldTile = item.getTile();

        for (RoomItemFloor stackItem : floorItemsAt) {
            if (item.getId() != stackItem.getId()) {

                stackItem.onItemAddedToStack(item);
            }
        }

        item.onPositionChanged(newPosition);

        List<RoomEntity> affectEntities0 = room.getEntities().getEntitiesAt(item.getPosition());

        for (RoomEntity entity0 : affectEntities0) {
            item.onEntityStepOff(entity0);
        }

        List<Position> tilesToUpdate = new ArrayList<>();

        tilesToUpdate.add(new Position(item.getPosition().getX(), item.getPosition().getY()));
        tilesToUpdate.add(new Position(newPosition.getX(), newPosition.getY()));

        // Catch this so the item still updates!
        try {
            for (AffectedTile affectedTile : AffectedTile.getAffectedTilesAt(item.getDefinition().getLength(), item.getDefinition().getWidth(), item.getPosition().getX(), item.getPosition().getY(), item.getRotation())) {
                tilesToUpdate.add(new Position(affectedTile.x, affectedTile.y));

                List<RoomEntity> affectEntities1 = room.getEntities().getEntitiesAt(new Position(affectedTile.x, affectedTile.y));

                for (RoomEntity entity1 : affectEntities1) {
                    item.onEntityStepOff(entity1);
                }
            }

            for (AffectedTile affectedTile : AffectedTile.getAffectedTilesAt(item.getDefinition().getLength(), item.getDefinition().getWidth(), newPosition.getX(), newPosition.getY(), rotation)) {
                tilesToUpdate.add(new Position(affectedTile.x, affectedTile.y));

                List<RoomEntity> affectEntities2 = room.getEntities().getEntitiesAt(new Position(affectedTile.x, affectedTile.y));

                for (RoomEntity entity2 : affectEntities2) {
                    item.onEntityStepOn(entity2);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Failed to update entity positions for changing item position", e);
        }

        item.getPosition().setX(newPosition.getX());
        item.getPosition().setY(newPosition.getY());
        item.getPosition().setZ(height);
        item.setRotation(rotation);

        List<RoomEntity> affectEntities3 = room.getEntities().getEntitiesAt(newPosition);

        for (RoomEntity entity3 : affectEntities3) {
            item.onEntityStepOn(entity3);
        }

        if (save)
            item.save();

        for (Position tileToUpdate : tilesToUpdate) {
            final RoomTile tileInstance = this.room.getMapping().getTile(tileToUpdate.getX(), tileToUpdate.getY());

            if (tileInstance != null) {
                tileInstance.reload();

                room.getEntities().broadcastMessageModeBuild(tileInstance);
            }
        }

        /*RoomItemFloor topItem = oldTile.getTopItemInstance();
        if(topItem != null){
            for(RoomEntity oldTileEntity : oldTile.getEntities()){
                if(oldTileEntity instanceof PlayerEntity){
                    WiredTriggerWalksOnFurni.executeTriggers(oldTileEntity, topItem);
                }
            }
        }*/

        tilesToUpdate.clear();
        return true;
    }

    public void removeItem(RoomItemWall item, Session client, boolean toInventory) {
        this.getRoom().getEntities().broadcastMessage(new RemoveWallItemMessageComposer(item.getVirtualId(), item.getItemData().getOwnerId()));
        this.getWallItems().remove(item.getId());

        if (toInventory) {
            StorageContext.getCurrentContext().getRoomItemRepository().removeItemFromRoom(item.getId(), item.getItemData().getOwnerId(), item.getItemData().getData());

            Session session = client;

            if (item.getItemData().getOwnerId() != client.getPlayer().getId()) {
                session = NetworkManager.getInstance().getSessions().getByPlayerId(item.getItemData().getOwnerId());
            }

            if (session != null) {
                session.getPlayer().getInventory().add(item.getId(), item.getItemData().getItemId(), item.getItemData().getData(), item.getLimitedEditionItemData());
                session.send(new UpdateInventoryMessageComposer());
                session.send(new UnseenItemsMessageComposer(new HashMap<Integer, List<Integer>>() {{
                    put(1, Lists.newArrayList(item.getVirtualId()));
                }}));
            }
        } else {
            StorageContext.getCurrentContext().getRoomItemRepository().deleteItem(item.getId());
        }
    }

    public boolean moveFloorItem(long itemId, Position newPosition, int rotation, boolean save) {
        return moveFloorItem(itemId, newPosition, rotation, save, true, null, false);
    }

    public boolean moveFloorItem(long itemId, Position newPosition, int rotation, boolean save, boolean obeyStack, Player mover, boolean isRentable) {
        RoomItemFloor item = this.getFloorItem(itemId);
        if (item == null) return false;

        RoomTile tile = this.getRoom().getMapping().getTile(newPosition.getX(), newPosition.getY());

        if (mover != null && mover.getEntity() != null) {
            if ((mover.getEntity()).setzok) {
                if (!verifyItemPositionSetZ(item.getDefinition(), tile, item.getPosition(), rotation, mover))
                    return false;
            } else if (!verifyItemPosition(item.getDefinition(), item, tile, item.getPosition(), rotation)) {
                return false;
            }
        } else if (!verifyItemPosition(item.getDefinition(), item, tile, item.getPosition(), rotation)) {
            return false;
        }

        double height = obeyStack ? tile.getStackHeight(item) : newPosition.getZ();

        if (mover != null) {
            if (mover.getEntity().setzok) {
                height = mover.getItemPlacementHeight();
            }
        }

        List<RoomItemFloor> floorItemsAt = this.getItemsOnSquare(newPosition.getX(), newPosition.getY());

        // Rentable Spaces by Custom.
        if(isRentable) {
            for (RoomItemFloor tileItems : floorItemsAt) {
                if (tileItems instanceof RentableSpaceFloorItem) {
                    boolean _break = ((RentableSpaceFloorItem) tileItems).verifyStatus(mover);

                    if (tileItems.getId() != mover.getRentable().getRentedId()) {
                        // Notify that you can't place items on a rentable space (TYPES: Already bought, Not bought).
                        return false;
                    }
                }
            }
        }

        for (RoomItemFloor stackItem : floorItemsAt) {
            if (item.getId() != stackItem.getId()) {
                stackItem.onItemAddedToStack(item);
            }
        }

        item.onPositionChanged(newPosition);

        List<RoomEntity> affectEntities0 = room.getEntities().getEntitiesAt(item.getPosition());

        for (RoomEntity entity0 : affectEntities0) {
            item.onEntityStepOff(entity0);
        }

        List<Position> tilesToUpdate = new ArrayList<>();

        tilesToUpdate.add(new Position(item.getPosition().getX(), item.getPosition().getY()));
        tilesToUpdate.add(new Position(newPosition.getX(), newPosition.getY()));

        // Catch this so the item still updates!
        try {
            for (AffectedTile affectedTile : AffectedTile.getAffectedBothTilesAt(item.getDefinition().getLength(), item.getDefinition().getWidth(), item.getPosition().getX(), item.getPosition().getY(), item.getRotation())) {
                tilesToUpdate.add(new Position(affectedTile.x, affectedTile.y));

                List<RoomEntity> affectEntities1 = room.getEntities().getEntitiesAt(new Position(affectedTile.x, affectedTile.y));

                for (RoomEntity entity1 : affectEntities1) {
                    item.onEntityStepOff(entity1);

                    // update their height!
                    if (tile.getWalkHeight() != entity1.getPosition().getZ()) {
                        entity1.getPosition().setZ(tile.getWalkHeight());
                        this.getRoom().getEntities().broadcastMessage(new AvatarUpdateMessageComposer(entity1));
                    }
                }
            }

            for (AffectedTile affectedTile : AffectedTile.getAffectedBothTilesAt(item.getDefinition().getLength(), item.getDefinition().getWidth(), newPosition.getX(), newPosition.getY(), rotation)) {
                tilesToUpdate.add(new Position(affectedTile.x, affectedTile.y));

                List<RoomEntity> affectEntities2 = room.getEntities().getEntitiesAt(new Position(affectedTile.x, affectedTile.y));

                for (RoomEntity entity2 : affectEntities2) {
                    item.onEntityStepOn(entity2);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Failed to update entity positions for changing item position", e);
        }

        item.getPosition().setX(newPosition.getX());
        item.getPosition().setY(newPosition.getY());

        item.getPosition().setZ(height);
        item.getItemData().setRotation(rotation);

        List<RoomEntity> affectEntities3 = room.getEntities().getEntitiesAt(newPosition);

        for (RoomEntity entity3 : affectEntities3) {
            item.onEntityStepOn(entity3);
        }

        if (save)
            item.save();

        for (Position tileToUpdate : tilesToUpdate) {
            final RoomTile tileInstance = this.room.getMapping().getTile(tileToUpdate.getX(), tileToUpdate.getY());

            if (tileInstance != null) {
                tileInstance.reload();

                room.getEntities().broadcastMessage(new UpdateStackMapMessageComposer(tileInstance));
            }
        }

        tilesToUpdate.clear();
        return true;
    }

    private boolean verifyItemPosition(FurnitureDefinition item, RoomItemFloor floor, RoomTile tile, Position currentPosition, int rotation) {
        if (tile != null) {
            if (currentPosition != null && currentPosition.getX() == tile.getPosition().getX() && currentPosition.getY() == tile.getPosition().getY())
                return true;

            List<AffectedTile> affectedTiles = AffectedTile.getAffectedBothTilesAt(
                    item.getLength(), item.getWidth(), tile.getPosition().getX(), tile.getPosition().getY(), rotation);

            for (AffectedTile affectedTile : affectedTiles) {
                final RoomTile roomTile = this.getRoom().getMapping().getTile(affectedTile.x, affectedTile.y);

                if (roomTile != null) {
                    if (!this.verifyItemTilePosition(item, floor, roomTile, rotation)) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }

    private boolean verifyItemPositionSetZ(FurnitureDefinition item, RoomTile tile, Position currentPosition, int rotation, Player player) {
        if (tile != null) {
            if (currentPosition != null && currentPosition.getX() == tile.getPosition().getX() && currentPosition.getY() == tile.getPosition().getY())
                return true;

            List<AffectedTile> affectedTiles = AffectedTile.getAffectedBothTilesAt(item.getLength(), item.getWidth(), tile.getPosition().getX(), tile.getPosition().getY(), rotation);

            for (AffectedTile affectedTile : affectedTiles) {
                RoomTile roomTile = getRoom().getMapping().getTile(affectedTile.x, affectedTile.y);
                if (roomTile != null && !verifyItemTilePositionSetz(roomTile, player))
                    return false;
            }
        } else {
            return false;
        }
        return true;
    }

    private boolean verifyItemTilePositionSetz(RoomTile tile, Player player) {
        return true;
    }


    private boolean verifyItemTilePosition(FurnitureDefinition item, RoomItemFloor floorItem, RoomTile tile, int rotation) {
        if (!tile.canPlaceItemHere()) {
            return false;
        }

        if (!tile.canStack() && tile.getTopItem() != 0 && (floorItem == null || tile.getTopItem() != floorItem.getId())) {
            if (!item.getItemName().startsWith(RoomItemFactory.STACK_TOOL))
                return false;
        }

        if (!item.getInteraction().equals(RoomItemFactory.TELEPORT_PAD) && tile.getPosition().getX() == this.getRoom().getModel().getDoorX() && tile.getPosition().getY() == this.getRoom().getModel().getDoorY()) {
            return false;
        }

        if (item instanceof DiceFloorItem || item instanceof BanzaiTileFloorItem || item instanceof MagicStackFloorItem) {
            for (RoomItemFloor itemFloor : tile.getItems()) {
                if (itemFloor instanceof DiceFloorItem && item instanceof DiceFloorItem)
                    return false;

                if (itemFloor instanceof MagicStackFloorItem && item instanceof MagicStackFloorItem)
                    return false;

                if (itemFloor instanceof BanzaiTileFloorItem && item instanceof BanzaiTileFloorItem)
                    return false;
            }
        }

        if (!CometSettings.roomCanPlaceItemOnEntity) {
            return tile.getEntities().size() == 0;
        }

        return true;
    }

    public void placeWallItem(PlayerItem item, String position, Player player) {
        int roomId = this.room.getId();

        StorageContext.getCurrentContext().getRoomItemRepository().placeWallItem(roomId, position, item.getExtraData().trim().isEmpty() ? "0" :
                item.getExtraData(), item.getId());
        player.getInventory().removeItem(item.getId());

        RoomItemWall wallItem = this.addWallItem(item.getId(), item.getBaseId(), this.room, player.getId(),
                player.getData().getUsername(), position, (item.getExtraData().isEmpty() ||
                        item.getExtraData().equals(" ")) ? "0" : item.getExtraData());

        this.room.getEntities().broadcastMessage(
                new SendWallItemMessageComposer(wallItem)
        );

        wallItem.onPlaced();
    }

    public Room getRoom() {
        return this.room;
    }

    public Map<Long, RoomItemFloor> getFloorItems() {
        return this.floorItems;
    }

    public Map<Long, RoomItemWall> getWallItems() {
        return this.wallItems;
    }

    public void placeFloorItem(PlayerItem item, int x, int y, int rot, Player player, boolean isRentable) {
        RoomTile tile = room.getMapping().getTile(x, y);

        if (tile == null)
            return;

        if(player == null){
            return;
        }

        double height = tile.getStackHeight();
        rot = player.getItemPlacementRotation() >= 0 ? player.getItemPlacementRotation() : rot;
        String data;

        if(player.getItemPlacementState() >= 0) {
            data = player.getItemPlacementState() + "";
            //System.out.print("SOCKET SYNC: I'm properly setting state " + data + ".");
        } else {
            data = (item.getExtraData().isEmpty() || item.getExtraData().equals(" ")) ? "0" : item.getExtraData();
            //System.out.print("\n\nSOCKET SYNC: I have no clue on what the fuck I'm actually doing. " + data + ".\n\n");
        }

        if (player.getEntity() != null && player.getEntity().setzok)
            height = player.getItemPlacementHeight();

        if (player.getEntity() != null) {
            if (player.getEntity().setzok) {
                if(!verifyItemPositionSetZ(item.getDefinition(), tile, null, rot, player)){
                    return;
                }
            } else if (!verifyItemPosition(item.getDefinition(), null, tile, null, rot)) {
                return;
            }
        } else if (!verifyItemPosition(item.getDefinition(), null, tile, null, rot)) {
            return;
        }

        if (item.getDefinition().getInteraction().equals("soundmachine")) {
            if (this.soundMachineId > 0) {
                Map<String, String> notificationParams = Maps.newHashMap();

                notificationParams.put("message", Locale.get("game.room.jukeboxExists"));

                player.getSession().send(new NotificationMessageComposer("furni_placement_error", notificationParams));
                return;
            } else {
                this.soundMachineId = item.getId();
            }
        }

        List<RoomItemFloor> floorItems = room.getItems().getItemsOnSquare(x, y);

        if (item.getDefinition() != null && item.getDefinition().getInteraction() != null) {
            if (item.getDefinition().getInteraction().equals("mannequin")) {
                rot = 2;
            }
        }

        // Rentable Spaces by Custom.
        if(isRentable) {

            if(floorItems.isEmpty())
                return;

            for (RoomItemFloor tileItems : floorItems) {
                if (tileItems instanceof RentableSpaceFloorItem) {
                    boolean _break = ((RentableSpaceFloorItem) tileItems).verifyStatus(player);

                    if (tileItems.getId() != player.getRentable().getRentedId()) {
                        // Notify that you can't place items on a rentable space (TYPES: Already bought, Not bought).
                        return;
                    }
                }
            }
        }

        StorageContext.getCurrentContext().getRoomItemRepository().placeFloorItem(room.getId(), x, y, height, rot, data, item.getBaseId(), item.getId());
        player.getInventory().removeItem(item.getId());

        RoomItemFloor floorItem = room.getItems().addFloorItem(item.getId(), item.getBaseId(), room, player.getId(),
                player.getData().getUsername(), x, y, rot, height, data, item.getLimitedEditionItem());
        List<Position> tilesToUpdate = new ArrayList<>();

        for (RoomItemFloor stackItem : floorItems) {
            if (item.getId() != stackItem.getId()) {
                stackItem.onItemAddedToStack(floorItem);
            }
        }

        tilesToUpdate.add(new Position(floorItem.getPosition().getX(), floorItem.getPosition().getY(), 0d));

        for (AffectedTile affTile : AffectedTile.getAffectedBothTilesAt(item.getDefinition().getLength(), item.getDefinition().getWidth(), floorItem.getPosition().getX(), floorItem.getPosition().getY(), floorItem.getRotation())) {
            tilesToUpdate.add(new Position(affTile.x, affTile.y, 0d));

            List<RoomEntity> affectEntities0 = room.getEntities().getEntitiesAt(new Position(affTile.x, affTile.y));

            for (RoomEntity entity0 : affectEntities0) {
                floorItem.onEntityStepOn(entity0);
            }
        }

        for (Position tileToUpdate : tilesToUpdate) {
            final RoomTile tileInstance = this.room.getMapping().getTile(tileToUpdate.getX(), tileToUpdate.getY());

            if (tileInstance != null) {
                tileInstance.reload();

                room.getEntities().broadcastMessage(new UpdateStackMapMessageComposer(tileInstance));
            }
        }

        room.getEntities().broadcastMessage(new SendFloorItemMessageComposer(floorItem));


        floorItem.onPlaced();
        floorItem.saveData();
    }

    public void placeFloorItem(PlayerItem item, int x, int y, int rot) {
        RoomTile tile = room.getMapping().getTile(x, y);

        if (tile == null)
            return;

        double height = tile.getStackHeight();
        String data;

        data = (item.getExtraData().isEmpty() || item.getExtraData().equals(" ")) ? "0" : item.getExtraData();

        if (!this.verifyItemPosition(item.getDefinition(), null, tile, null, rot))
            return;

        List<RoomItemFloor> floorItems = room.getItems().getItemsOnSquare(x, y);

        StorageContext.getCurrentContext().getRoomItemRepository().placeFloorItem(room.getId(), x, y, height, rot, data, item.getBaseId(), item.getId());

        RoomItemFloor floorItem = room.getItems().addFloorItem(item.getId(), item.getBaseId(), room, 1,
                "Yai", x, y, rot, height, data, item.getLimitedEditionItem());

        List<Position> tilesToUpdate = new ArrayList<>();

        for (RoomItemFloor stackItem : floorItems) {
            if (item.getId() != stackItem.getId()) {
                stackItem.onItemAddedToStack(floorItem);
            }
        }

        tilesToUpdate.add(new Position(floorItem.getPosition().getX(), floorItem.getPosition().getY(), 0d));

        for (AffectedTile affTile : AffectedTile.getAffectedBothTilesAt(item.getDefinition().getLength(), item.getDefinition().getWidth(), floorItem.getPosition().getX(), floorItem.getPosition().getY(), floorItem.getRotation())) {
            tilesToUpdate.add(new Position(affTile.x, affTile.y, 0d));

            List<RoomEntity> affectEntities0 = room.getEntities().getEntitiesAt(new Position(affTile.x, affTile.y));

            for (RoomEntity entity0 : affectEntities0) {
                floorItem.onEntityStepOn(entity0);
            }
        }

        for (Position tileToUpdate : tilesToUpdate) {
            final RoomTile tileInstance = this.room.getMapping().getTile(tileToUpdate.getX(), tileToUpdate.getY());

            if (tileInstance != null) {
                tileInstance.reload();

                room.getEntities().broadcastMessage(new UpdateStackMapMessageComposer(tileInstance));
            }
        }

        room.getEntities().broadcastMessage(new SendFloorItemMessageComposer(floorItem));


        floorItem.onPlaced();
        floorItem.saveData();
    }

    private void indexItem(RoomItemFloor floorItem) {
        this.itemOwners.put(floorItem.getItemData().getOwnerId(), floorItem.getItemData().getOwnerName());

        if (!this.itemClassIndex.containsKey(floorItem.getClass())) {
            itemClassIndex.put(floorItem.getClass(), new HashSet<>());
        }

        if (!this.itemInteractionIndex.containsKey(floorItem.getDefinition().getInteraction())) {
            this.itemInteractionIndex.put(floorItem.getDefinition().getInteraction(), new HashSet<>());
        }

        this.itemClassIndex.get(floorItem.getClass()).add(floorItem.getId());
        this.itemInteractionIndex.get(floorItem.getDefinition().getInteraction()).add(floorItem.getId());
    }

    public SoundMachineFloorItem getSoundMachine() {
        if (this.soundMachineId != 0) {
            return ((SoundMachineFloorItem) this.getFloorItem(this.soundMachineId));
        }

        return null;
    }

    public Map<Integer, String> getItemOwners() {
        return this.itemOwners;
    }
}
