package com.cometproject.server.game.rooms.types.components;

import com.cometproject.api.config.CometSettings;
import com.cometproject.api.game.quests.QuestType;
import com.cometproject.api.game.rooms.entities.RoomEntityStatus;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.RoomEntityType;
import com.cometproject.server.game.rooms.objects.entities.WiredTriggerExecutor;
import com.cometproject.server.game.rooms.objects.entities.effects.PlayerEffect;
import com.cometproject.server.game.rooms.objects.entities.pathfinding.Square;
import com.cometproject.server.game.rooms.objects.entities.types.BotEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PetEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.objects.items.types.floor.EffectFloorItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.TeleportPadFloorItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.pet.breeding.BreedingBoxFloorItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.triggers.WiredTriggerBotReachedAvatar;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.triggers.WiredTriggerBotReachedFurni;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.triggers.WiredTriggerWalksOffFurni;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.triggers.WiredTriggerWalksOnFurni;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.triggers.custom.WiredTriggerUsersCollide;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.mapping.RoomEntityMovementNode;
import com.cometproject.server.game.rooms.types.mapping.RoomTile;
import com.cometproject.server.network.messages.outgoing.room.avatar.AvatarUpdateMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.UpdateInfoMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.RemoveFloorItemMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.SendShadowMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.SlideObjectBundleMessageComposer;
import com.cometproject.server.tasks.CometTask;
import com.cometproject.server.tasks.CometThreadManager;
import com.cometproject.server.utilities.TimeSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class ProcessComponent implements CometTask {
    private Room room;

    private Logger LOGGER;
    private ScheduledFuture processFuture;
    private boolean active = false;

    private boolean adaptiveProcessTimes = false;
    private List<Long> processTimes;

    private long lastProcess = 0;

    private boolean isProcessing = false;

    private List<PlayerEntity> playersToRemove;
    private List<RoomEntity> entitiesToUpdate;

    private boolean update = false;

    public ProcessComponent(Room room) {
        this.room = room;
        this.LOGGER = LoggerFactory.getLogger("Room Process [" + room.getData().getName() + ", #" + room.getId() + "]");

        this.adaptiveProcessTimes = CometSettings.adaptiveEntityProcessDelay;
    }

    public void tick() {
        if (!this.active) {
            return;
        }

        if (this.isProcessing) return;

        this.isProcessing = true;

        update = !update;

        long timeSinceLastProcess = this.lastProcess == 0 ? 0 : (System.currentTimeMillis() - this.lastProcess);
        this.lastProcess = System.currentTimeMillis();

        if (this.getProcessTimes() != null && this.getProcessTimes().size() < 30) {
            LOGGER.info("Time since last process: " + timeSinceLastProcess + "ms");
        }

        long timeStart = System.currentTimeMillis();

        try {
            if (this.update)
                this.getRoom().tick();
        } catch (Exception e) {
            LOGGER.error("Error while cycling room: " + room.getData().getId() + ", " + room.getData().getName(), e);
        }

        try {
            Map<Integer, RoomEntity> entities = this.room.getEntities().getAllEntities();

            playersToRemove = new ArrayList<>();
            entitiesToUpdate = new ArrayList<>();

            ArrayList<RoomEntity> listEntities = new ArrayList<>(entities.values());

            if(this.getRoom().getData().hasSorting())
            Collections.shuffle(listEntities);

            for (RoomEntity entity : listEntities) {
                if (entity.isFastWalkEnabled() || this.update)
                    this.startProcessing(entity);
            }

            // only send the updates if we need to
            if (entitiesToUpdate.size() > 0) {
                this.getRoom().getEntities().broadcastMessage(new AvatarUpdateMessageComposer(entitiesToUpdate));
            }

            for (RoomEntity entity : entitiesToUpdate) {
                if (entity.updatePhase == 1) continue;

                if (this.updateEntityStuff(entity) && entity instanceof PlayerEntity) {
                    playersToRemove.add((PlayerEntity) entity);
                }
            }

            for (PlayerEntity entity : playersToRemove) {
                entity.leaveRoom(entity.getPlayer() == null, false, true);
            }

            playersToRemove.clear();
            entitiesToUpdate.clear();

            playersToRemove = null;
            entitiesToUpdate = null;

            //LOGGER.debug("Room processing took " + (System.currentTimeMillis() - timeStart) + "ms");
        } catch (Exception e) {
            LOGGER.warn("Error during room entity processing", e);
        }

        TimeSpan span = new TimeSpan(timeStart, System.currentTimeMillis());

        if (this.getProcessTimes() != null) {
            if (this.getProcessTimes().size() < 30)
                this.getProcessTimes().add(span.toMilliseconds());
        }

        if (this.adaptiveProcessTimes) {
            CometThreadManager.getInstance().executeSchedule(this, 500 - span.toMilliseconds(), TimeUnit.MILLISECONDS);
        }

        this.isProcessing = false;
    }

    public void start() {
        if (Room.useCycleForEntities) {
            this.active = true;
            return;
        }

        if (this.active) {
            stop();
        }

        if (this.adaptiveProcessTimes) {
            CometThreadManager.getInstance().executeSchedule(this, 250, TimeUnit.MILLISECONDS);
        } else {
            this.processFuture = CometThreadManager.getInstance().executePeriodic(this, 450, 250, TimeUnit.MILLISECONDS);
        }

        this.active = true;

        LOGGER.debug("Processing started");
    }

    public void stop() {
        if (Room.useCycleForEntities) {
            this.active = false;
            return;
        }

        if (this.getProcessTimes() != null) {
            this.getProcessTimes().clear();
        }

        if (this.processFuture != null) {
            this.active = false;

            if (!this.adaptiveProcessTimes)
                this.processFuture.cancel(false);

            LOGGER.debug("Processing stopped");
        }
    }

    @Override
    public void run() {
        this.tick();
    }

    public void setDelay(int time) {
        this.processFuture.cancel(false);
        this.processFuture = CometThreadManager.getInstance().executePeriodic(this, 0, time, TimeUnit.MILLISECONDS);
    }

    private void startProcessing(RoomEntity entity) {
        if (entity.getEntityType() == RoomEntityType.PLAYER) {
            PlayerEntity playerEntity = (PlayerEntity) entity;

            try {
                if (playerEntity.getPlayer() == null || playerEntity.getPlayer().isDisposed() || playerEntity.getPlayer().getSession() == null) {
                    playersToRemove.add(playerEntity);
                    return;
                }
            } catch (Exception e) {
                LOGGER.warn("Failed to remove null player from room - user data was null");
                return;
            }

            boolean playerNeedsRemove = processEntity(playerEntity);

            if (playerNeedsRemove) {
                playersToRemove.add(playerEntity);
            }
        } else {
            try {
                if (entity.getAI() != null) {
                    entity.getAI().onTick();
                }

                if (entity.getEntityType() == RoomEntityType.BOT) {
                    // do anything special here for bots?
                    processEntity(entity);
                } else if (entity.getEntityType() == RoomEntityType.PET) {
                    if (entity.getMountedEntity() == null) {
                        // do anything special here for pets?
                        processEntity(entity);
                    }
                }
            } catch (Exception e) {
                LOGGER.error(String.format("Error while processing entity %s", entity.getId()), e);
            }
        }

        if ((entity.needsUpdate() && !entity.needsUpdateCancel() || entity.needsForcedUpdate) && entity.isVisible()) {
            if (entity.needsForcedUpdate && entity.updatePhase == 1) {
                entity.needsForcedUpdate = false;
                entity.updatePhase = 0;

                entitiesToUpdate.add(entity);
            } else if (entity.needsForcedUpdate) {
                if (entity.hasStatus(RoomEntityStatus.MOVE)) {
                    entity.removeStatus(RoomEntityStatus.MOVE);
                }

                entity.updatePhase = 1;
                entitiesToUpdate.add(entity);
            } else {
                if (entity instanceof PlayerEntity) {
                    if (entity.getMountedEntity() != null) {
                        processEntity(entity.getMountedEntity());

                        entity.getMountedEntity().markUpdateComplete();
                        entitiesToUpdate.add(entity.getMountedEntity());
                    }
                }

                if (entity.isWarped()) {
                    entity.setWarped(false);
                }

                entity.markUpdateComplete();
                entitiesToUpdate.add(entity);
            }
        }
    }

    public boolean updateEntityStuff(RoomEntity entity) {
        if (entity.getPositionToSet() != null) {
            if ((entity.getPositionToSet().getX() == this.room.getModel().getDoorX()) && (entity.getPositionToSet().getY() == this.room.getModel().getDoorY())) {
                boolean leaveRoom = true;
                final List<RoomItemFloor> floorItemsAtDoor = this.getRoom().getItems().getItemsOnSquare(entity.getPositionToSet().getX(), entity.getPositionToSet().getY());

                if (!floorItemsAtDoor.isEmpty()) {
                    for (RoomItemFloor floorItem : floorItemsAtDoor) {
                        if (floorItem instanceof TeleportPadFloorItem) {
                            leaveRoom = false;
                            break;
                        }
                    }
                }

                if (leaveRoom) {
                    entity.updateAndSetPosition(null);
                    return true;
                }
            }

            if (entity.hasStatus(RoomEntityStatus.SIT)) {
                entity.removeStatus(RoomEntityStatus.SIT);
            }

            // Create the new position
            Position newPosition = entity.getPositionToSet().copy();
            Position oldPosition = entity.getPosition().copy();

            List<RoomItemFloor> itemsOnSq = this.getRoom().getItems().getItemsOnSquare(entity.getPositionToSet().getX(), entity.getPositionToSet().getY());
            List<RoomItemFloor> itemsOnOldSq = this.getRoom().getItems().getItemsOnSquare(entity.getPosition().getX(), entity.getPosition().getY());

            final RoomTile oldTile = this.getRoom().getMapping().getTile(entity.getPosition().getX(), entity.getPosition().getY());
            final RoomTile newTile = this.getRoom().getMapping().getTile(newPosition.getX(), newPosition.getY());

            if (oldTile != null) {
                if (!oldTile.equals(newTile)) // FIXE WIRED TP
                {
                    oldTile.getEntities().remove(entity);
                }
            }

            entity.updateAndSetPosition(null);
            entity.setPosition(newPosition);

            if (entity.hasAttribute("tpencours")) {
                entity.removeAttribute("tpencours");
            }

            if (entity instanceof BotEntity) {
                entity.getAI().onReachedTile(newTile);
            }

            if(this.getRoom().getData().advancedCollision()) {
                if (entity instanceof PlayerEntity) {
                    entity.onReachedTile(newTile);
                }
            }

            // Step off
            for (RoomItemFloor item : itemsOnOldSq) {
                if (!itemsOnSq.contains(item)) {
                    item.onEntityStepOff(entity);
                    this.getRoom().getItemProcess().queueAction(new WiredTriggerExecutor(WiredTriggerWalksOffFurni.class, entity, item));
                }
            }

            for (RoomItemFloor item : itemsOnSq) {
                if (entity instanceof PlayerEntity) {
                    PlayerEntity playerEntity = ((PlayerEntity) entity);

                    if (playerEntity.getPlayer() != null && playerEntity.getPlayer().getData().getQuestId() != 0 && playerEntity.getPlayer().getQuests() != null)
                        ((PlayerEntity) entity).getPlayer().getQuests().progressQuest(QuestType.EXPLORE_FIND_ITEM, item.getDefinition().getSpriteId());
                }
            }

            if (entity.getFollowingEntities().size() != 0) {
                entity.getFollowingEntities().forEach(e -> e.moveTo(oldPosition));
            }

            if (newTile != null && newTile.getTopItem() != 0 && !entity.isWarped()) {
                RoomItemFloor topItem = this.getRoom().getItems().getFloorItem(newTile.getTopItem());

                if (topItem != null) {
                    final int itemEffectId = topItem.getDefinition().getEffectId();

                    if (!(topItem instanceof EffectFloorItem) && itemEffectId != 0 && (entity.getCurrentEffect() == null || entity.getCurrentEffect().getEffectId() != itemEffectId)) {
                        entity.applyEffect(new PlayerEffect(topItem.getDefinition().getEffectId(), true));
                    }

                    topItem.onEntityStepOn(entity);
                    if (entity instanceof PlayerEntity) {
                        //WiredTriggerWalksOnFurni.executeTriggers(entity, topItem);
                        this.getRoom().getItemProcess().queueAction(new WiredTriggerExecutor<>(WiredTriggerWalksOnFurni.class, entity, topItem));
                    }

                    if (entity instanceof BotEntity) {
                        WiredTriggerBotReachedFurni.executeTriggers(entity, topItem, entity.getUsername());
                    }
                }
            } else if (newTile != null) {
                newTile.onEntityEntersTile(entity);
            }
        }

        return false;
    }

    private boolean processEntity(RoomEntity entity) {
        return this.processEntity(entity, false);
    }

    private boolean processEntity(RoomEntity entity, boolean isRetry) {
        boolean isPlayer = entity instanceof PlayerEntity;

        if (isPlayer && ((PlayerEntity) entity).getPlayer() == null || entity.getRoom() == null) {
            return true; // adds it to the to remove list automatically..
        }

        if (!isRetry) {
            if (isPlayer) {
                // Handle flood
                if (((PlayerEntity) entity).getPlayer().getRoomFloodTime() >= 0.5) {
                    ((PlayerEntity) entity).getPlayer().setRoomFloodTime(((PlayerEntity) entity).getPlayer().getRoomFloodTime() - 0.5);

                    if (((PlayerEntity) entity).getPlayer().getRoomFloodTime() < 0) {
                        ((PlayerEntity) entity).getPlayer().setRoomFloodTime(0);
                    }
                }
            }

            if (entity.handItemNeedsRemove() && entity.getHandItem() != 0) {
                entity.carryItem(0);
                entity.setHandItemTimer(0);
            }

            if(entity.isBodyRotating()){
                int rotation = entity.getBodyRotation();
                if(rotation >= 8)
                    rotation = 0;

                rotation++;
                entity.setBodyRotation(rotation);
                entity.markNeedsUpdate();
            }

            if(entity.isHeadRotating()){
                int rotation = entity.getHeadRotation();
                if(rotation >= 8)
                    rotation = 0;

                rotation++;
                entity.setHeadRotation(rotation);
                entity.markNeedsUpdate();
            }

            // Handle signs
            if (entity.hasStatus(RoomEntityStatus.SIGN) && !entity.isDisplayingSign()) {
                entity.removeStatus(RoomEntityStatus.SIGN);
                entity.markNeedsUpdate();
            }

            if(entity instanceof PlayerEntity && entity.isForcedIdle()){
                ((PlayerEntity) entity).getPlayer().getData().setMotto(Locale.getOrDefault("idle.timer.message", "Llevo " + entity.getIdleTime() + " segundos ausente."));
                this.getRoom().getEntities().broadcastMessage(new UpdateInfoMessageComposer(entity));
            }

            if (entity instanceof PlayerEntity && entity.isIdleAndIncrement() && entity.isVisible()) {
                if (entity.getIdleTime() >= 360 * CometSettings.roomIdleMinutes * 2) {
                    if (this.getRoom().getData().getOwnerId() != ((PlayerEntity) entity).getPlayerId() && !((PlayerEntity) entity).getPlayer().getPermissions().getRank().roomFullControl())
                        return true;
                }
            }
        }

        if (entity.hasStatus(RoomEntityStatus.MOVE)) {
            entity.removeStatus(RoomEntityStatus.MOVE);
            entity.removeStatus(RoomEntityStatus.GESTURE);

            entity.markNeedsUpdate();
        }

        // Check if we are wanting to walk to a location
        if (entity.getWalkingPath() != null) {
            entity.setProcessingPath(new CopyOnWriteArrayList<>(entity.getWalkingPath()));

            // Clear the walking path now we have a goal set
            entity.getWalkingPath().clear();
            entity.setWalkingPath(null);
        }

        if (entity.isWalking()) {
            Square nextSq = entity.getProcessingPath().get(0);
            entity.incrementPreviousSteps();

            if (entity.getProcessingPath().size() > 1)
                entity.setFutureSquare(entity.getProcessingPath().get(1));

            if (isPlayer && ((PlayerEntity) entity).isKicked()) {

                if (((PlayerEntity) entity).getKickWalkStage() > 5) {
                    return true;
                }

                ((PlayerEntity) entity).increaseKickWalkStage();
            }

            entity.getProcessingPath().remove(nextSq);

            boolean isLastStep = (entity.getProcessingPath().size() == 0);

            if (nextSq != null && entity.getRoom().getMapping().isValidEntityStep(entity, entity.getPosition(), new Position(nextSq.x, nextSq.y, 0), isLastStep) || entity.isOverriden()) {
                Position currentPos = entity.getPosition() != null ? entity.getPosition() : new Position(0, 0, 0);
                Position nextPos = new Position(nextSq.x, nextSq.y);

                final double mountHeight = entity instanceof PlayerEntity && entity.getMountedEntity() != null ? 1.0 : 0;//(entity.getMountedEntity() != null) ? (((String) entity.getAttribute("transform")).startsWith("15 ") ? 1.0 : 0.5) : 0;

                final RoomTile tile = this.room.getMapping().getTile(nextSq.x, nextSq.y);
                final double height = tile.getWalkHeight() + mountHeight;
                boolean isCancelled = entity.isWalkCancelled();
                boolean effectNeedsRemove = true;

                List<RoomItemFloor> preItems = this.getRoom().getItems().getItemsOnSquare(nextSq.x, nextSq.y);

                for (RoomItemFloor item : preItems) {
                    if (item != null) {
                        if (!(item instanceof EffectFloorItem) && entity.getCurrentEffect() != null && entity.getCurrentEffect().getEffectId() == item.getDefinition().getEffectId()) {
                            if (item.getId() == tile.getTopItem()) {
                                effectNeedsRemove = false;
                            }
                        }

                        if (item.isMovementCancelled(entity, new Position(nextSq.x, nextSq.y))) {
                            isCancelled = true;
                        }

                        if (!isCancelled) {
                            item.onEntityPreStepOn(entity);
                        }
                    }
                }

                if (effectNeedsRemove && entity.getCurrentEffect() != null && entity.getCurrentEffect().isItemEffect()) {
                    entity.applyEffect(entity.getLastEffect());
                }

                if (this.getRoom().getEntities().positionHasEntity(nextPos)) {
                    final boolean allowWalkthrough = this.getRoom().getData().getAllowWalkthrough();
                    final boolean isFinalStep = entity.getWalkingGoal().equals(nextPos);

                    if (isFinalStep && allowWalkthrough) {
                        isCancelled = true;
                    } else if (!allowWalkthrough) {
                        isCancelled = true;
                    }

                    RoomEntity entityOnTile = this.getRoom().getMapping().getTile(nextPos.getX(), nextPos.getY()).getEntity();

                    if (entityOnTile != null && entityOnTile.getMountedEntity() != null && entityOnTile.getMountedEntity() == entity) {
                        isCancelled = false;
                    }

                    if (entityOnTile instanceof PetEntity && entity instanceof PetEntity) {
                        if (entityOnTile.getTile().getTopItemInstance() instanceof BreedingBoxFloorItem) {
                            isCancelled = false;
                        }
                    }
                }

                if (!isCancelled) {
                    entity.setBodyRotation(Position.calculateRotation(currentPos.getX(), currentPos.getY(), nextSq.x, nextSq.y, entity.isMoonwalking()));
                    entity.setHeadRotation(entity.getBodyRotation());

                    entity.addStatus(RoomEntityStatus.MOVE, String.valueOf(nextSq.x).concat(",").concat(String.valueOf(nextSq.y)).concat(",").concat(String.valueOf(height)));

                    entity.removeStatus(RoomEntityStatus.SIT);
                    entity.removeStatus(RoomEntityStatus.LAY);

                    final Position newPosition = new Position(nextSq.x, nextSq.y, height);

                    if(entity instanceof PlayerEntity) {
                        if(((PlayerEntity)entity).getPlayer().getShadowStatus() == 1) {
                            ((PlayerEntity) entity).getPlayer().setShadow(2);
                            ((PlayerEntity) entity).getPlayer().getSession().send(new SendShadowMessageComposer(((PlayerEntity) entity).getPlayerId(), entity.getPosition()));
                        }
                        if(((PlayerEntity)entity).getPlayer().getShadowStatus() == 2)
                            ((PlayerEntity)entity).getPlayer().getSession().send(new SlideObjectBundleMessageComposer(entity.getPosition(), newPosition, ((PlayerEntity)entity).getPlayerId()));

                    }

                    entity.updateAndSetPosition(newPosition);
                    entity.markNeedsUpdate();

                    if (entity instanceof PlayerEntity && entity.getMountedEntity() != null) {
                        RoomEntity mountedEntity = entity.getMountedEntity();

                        mountedEntity.moveTo(newPosition.getX(), newPosition.getY());
                    }

                    List<RoomItemFloor> postItems = this.getRoom().getItems().getItemsOnSquare(nextSq.x, nextSq.y);

                    for (RoomItemFloor item : postItems) {
                        if (item != null) {
                            item.onEntityPostStepOn(entity);
                        }
                    }

                    entity.addToTile(tile);
                } else {
                    if (entity.getWalkingPath() != null) {
                        entity.getWalkingPath().clear();
                    }
                    entity.getProcessingPath().clear();
                    entity.setWalkCancelled(false);//
                }
            } else {
                if (entity.getWalkingPath() != null) {
                    entity.getWalkingPath().clear();
                }

                entity.getProcessingPath().clear();

                // RoomTile is blocked, let's try again!
                if (!isLastStep) {
                    entity.moveTo(entity.getWalkingGoal().getX(), entity.getWalkingGoal().getY());
                } else {
                    final RoomTile goalTile = this.getRoom().getMapping().getTile(entity.getWalkingGoal());

                    if (goalTile != null) {
                        for (RoomTile adjacentTile : goalTile.getAdjacentTiles(entity.getPosition())) {

                            if (adjacentTile != null && adjacentTile.getMovementNode() != RoomEntityMovementNode.CLOSED) {
                                entity.moveTo(adjacentTile.getPosition());
                                this.processEntity(entity, true);
                                return false;
                            }
                        }
                    }
                }

                return false;//this.processEntity(entity, true);
            }
        } else {
            if (isPlayer && ((PlayerEntity) entity).isKicked())
                return true;

            if (entity.getPositionToSet() != null) {
                this.updateEntityStuff(entity);
            }
        }

        // Handle expiring effects
        if (entity.getCurrentEffect() != null) {
            entity.getCurrentEffect().decrementDuration();

            if (entity.getCurrentEffect().getDuration() == 0 && entity.getCurrentEffect().expires()) {
                entity.applyEffect(entity.getLastEffect());

                if (entity.getLastEffect() != null)
                    entity.setLastEffect(null);
            }
        }

        if (entity.isWalkCancelled()) {
            entity.setWalkCancelled(false);
        }
//
//        if(entity.getPendingWalk() != null) {
//            entity.moveTo(entity.getPendingWalk());
//            entity.setPendingWalk(null);
//        }
//
        return false;
    }

    public boolean isActive() {
        return this.active;
    }

    public Room getRoom() {
        return this.room;
    }

    public List<Long> getProcessTimes() {
        return processTimes;
    }

    public void setProcessTimes(List<Long> processTimes) {
        this.processTimes = processTimes;
    }
}