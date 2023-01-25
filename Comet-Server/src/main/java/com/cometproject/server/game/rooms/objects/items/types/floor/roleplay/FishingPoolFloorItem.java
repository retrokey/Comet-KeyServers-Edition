package com.cometproject.server.game.rooms.objects.items.types.floor.roleplay;

import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.api.game.players.data.components.inventory.PlayerItem;
import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.composers.catalog.UnseenItemsMessageComposer;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.players.components.types.inventory.InventoryItem;
import com.cometproject.server.game.players.types.Player;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFactory;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.inventory.UpdateInventoryMessageComposer;
import com.cometproject.server.storage.queries.items.ItemDao;
import com.cometproject.storage.api.StorageContext;
import com.cometproject.storage.api.data.Data;
import com.google.common.collect.Sets;

public class FishingPoolFloorItem extends RoomItemFloor {

    public FishingPoolFloorItem(RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    public PlayerEntity incomingEntity = null;
    private int machineStatus = 0;
    private int fishingPoleBaseItem = 101;
    private int fishingBugBaseItem = 100;

    @Override
    public void onPlaced(){
        this.disposeItem();
    }

    @Override
    public boolean onInteract(RoomEntity entity, int requestData, boolean isWiredTrigger) {
        if (!isWiredTrigger) {
            if (!(entity instanceof PlayerEntity)) {
                return false;
            }

            Player player = ((PlayerEntity) entity).getPlayer();

            if (player == null)
                return false;

            if (this.getPosition().distanceTo(entity.getPosition()) > 5) {
                player.sendBubble("fishing_distance", "Debes estar más cerca para empezar a pescar.");
                return false;
            }

            if (!player.getInventory().hasBaseItem(fishingBugBaseItem) && !player.getInventory().hasBaseItem(fishingPoleBaseItem)) {
                player.sendBubble("fishing_items", "Necesitas una caña de pescar y un cebo para empezar a pescar.");
                return false;
            }

            if (this.incomingEntity != null) {
                if (this.getRoom().getEntities().getPlayerEntities().contains(this.incomingEntity)) {
                    player.sendBubble("fishing_in_use", "Este nodo de pesca se encuentra en uso, prueba una vez vuelva a echar burbujas.");
                    return false;
                } else {
                    this.disposeItem();
                }
            } else if (this.machineStatus != 0) {
                player.sendBubble("fishing_in_use", "Este nodo de pesca se encuentra en uso, prueba una vez vuelva a echar burbujas.");
                return false;
            } else {
                this.incomingEntity = (PlayerEntity) entity;
            }

            this.machineStatus = 1;
            this.flash(1);
            this.setTicks(RoomItemFactory.getProcessTime(2.5));
            this.incomingEntity.getPlayer().sendBubble("fishing_start", "Empiezas a intentar pescar en el nodo...");
            return true;
        }
        return false;
    }

    @Override
    public void onTickComplete() {
        if(this.incomingEntity != null) {
            switch (this.machineStatus) {
                case 1:
                    this.startFishing();
                    this.flash(0);
                    this.setTicks(RoomItemFactory.getProcessTime(Integer.parseInt(Locale.getOrDefault("fishing.step1.cooldown", "5"))));
                    break;

                case 2:
                    this.doFishingChance();
                    this.flash(1);
                    this.setTicks(RoomItemFactory.getProcessTime(Integer.parseInt(Locale.getOrDefault("fishing.step2.cooldown", "5"))));
                    break;

                case 3:
                    this.finishFishing();
                    this.flash(0);
                    break;
                case 4:
                    break;
                case 5:
                    this.disposeItem();
            }
        } else {
            this.disposeItem();
        }
    }

    private void startFishing(){
        this.incomingEntity.getPlayer().getSession().send(new WhisperMessageComposer(this.incomingEntity.getId(), "<i>Lanzas la caña junto al cebo al nodo de pesca...</i>", 1));
        this.incomingEntity.carryItem(34, 30);
        this.machineStatus = 2;
    }

    private void doFishingChance(){
        double percentage = Math.random() * 100;

        if(percentage <= 80){
            this.machineStatus = 3;
        } else{
            this.removeFishingReagents(true);
        }
    }

    private void removeFishingReagents(boolean isFishingFail){
        if(isFishingFail) {
            this.incomingEntity.getPlayer().getSession().send(new WhisperMessageComposer(this.incomingEntity.getId(), "<i>Tu caña acaba de romperse tras una reyerta con el pez que mordió el anzuelo...</i>", 1));
            this.disposeItem();
        } else {
            this.removeReagent(this.fishingBugBaseItem);
        }
    }

    private void removeReagent(int baseId){
        if(this.incomingEntity.getPlayer().getInventory().getInventoryItems() == null){
            this.disposeItem();
            return;
        }

        for(PlayerItem inventoryItem : this.incomingEntity.getPlayer().getInventory().getInventoryItems().values()){
            if(inventoryItem.getBaseId() == baseId){
                this.incomingEntity.getPlayer().getInventory().removeItem(inventoryItem);
                ItemDao.deleteItem(inventoryItem.getId());
                break;
            }
        }

        this.incomingEntity.getPlayer().getSession().send(new UpdateInventoryMessageComposer());
    }

    private void finishFishing(){
        if(!this.isValidAction())
            return;

        int fishOneBaseItem = 10109;
        int fishTwoBaseItem = 10105;
        int fishThreeBaseItem = 10069;
        int fishFourBaseItem = 10072;
        int fishFinalBaseItem;
        final Data<Long> itemIdData = Data.createEmpty();

        double percentage = Math.random() * 100;

        if(percentage <= 15){
            fishFinalBaseItem = fishOneBaseItem;
        } else if (percentage <= 35) {
            fishFinalBaseItem = fishTwoBaseItem;
        } else if (percentage <= 58) {
            fishFinalBaseItem = fishThreeBaseItem;
        } else {
            fishFinalBaseItem = fishFourBaseItem;
        }

        StorageContext.getCurrentContext().getRoomItemRepository().createItem(this.incomingEntity.getPlayer().getId(), fishFinalBaseItem, "0", itemIdData::set);

        final PlayerItem playerItem = new InventoryItem(itemIdData.get(), fishFinalBaseItem, "0");

        this.incomingEntity.getPlayer().getInventory().addItem(playerItem);

        this.incomingEntity.getPlayer().getSession().send(new UpdateInventoryMessageComposer());
        this.incomingEntity.getPlayer().getSession().send(new UnseenItemsMessageComposer(Sets.newHashSet(playerItem), ItemManager.getInstance()));

        this.incomingEntity.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_134, 1);
        this.incomingEntity.getPlayer().sendBubble("fishing_start", Locale.getOrDefault("fishing.completed", "Acabas de finalizar el proceso de pesca, cazas un %fishtype%.").replace("%fishtype%", playerItem.getDefinition().getPublicName()));
        this.disposeItem();
    }

    private void disposeItem(){
        this.incomingEntity = null;
        this.machineStatus = 0;
    }

    private boolean isValidAction(){
        if(!this.getRoom().getEntities().getPlayerEntities().contains(this.incomingEntity)){
            this.disposeItem();
            return false;
        }

        if(this.incomingEntity == null || this.incomingEntity.getPlayer() == null || this.incomingEntity.getPlayer().getData() == null || this.incomingEntity.getPlayer().getSession() == null){
            this.disposeItem();
            return false;
        }

        return true;
    }

    private void flash(int data){
        this.getItemData().setData(data);
        this.sendUpdate();
    }
}