package com.cometproject.server.game.rooms.objects.entities.types;

import com.cometproject.api.game.pets.IPetData;
import com.cometproject.api.game.players.data.PlayerAvatar;
import com.cometproject.api.game.players.data.components.inventory.PlayerItem;
import com.cometproject.api.game.rooms.entities.RoomEntityStatus;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.composers.catalog.UnseenItemsMessageComposer;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.pets.data.PetMonsterPlantData;
import com.cometproject.server.game.players.PlayerManager;
import com.cometproject.server.game.players.components.types.inventory.InventoryItem;
import com.cometproject.server.game.rooms.objects.entities.types.ai.pets.PetMonsterPlantAI;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.mapping.RoomTile;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.room.avatar.AvatarUpdateMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.pets.PetUpdateStatusComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.storage.api.StorageContext;
import com.cometproject.storage.api.data.Data;
import com.google.common.collect.Sets;

import static com.cometproject.server.game.commands.ChatCommand.sendNotif;

public class MonsterPlantEntity extends PetEntity {

    public MonsterPlantEntity(IPetData data, int identifier, Position startPosition, int startBodyRotation, int startHeadRotation, Room roomInstance) {
        super(data, identifier, startPosition, startBodyRotation, startHeadRotation, roomInstance);
        this.setData(data);
        this.setAi(new PetMonsterPlantAI(this));
        this.setGrowth();
    }

    public void sendGrowth() {
        this.setGrowth();
        this.composeUpdate();
    }

    @Override
    public void moveTo(int x, int y) {
        Position position = new Position(x, y, this.getRoom().getMapping().getTile(x, y).getWalkHeight());
        this.getData().setRoomPosition(position);
        this.getData().savePlantsData();
        super.moveTo(x, y);
    }

    public void composeUpdate() {
        this.getRoom().getEntities().broadcastMessage(new AvatarUpdateMessageComposer(this));
    }

    public void setGrowth() {
        if (((PetMonsterPlantData) this.getData()).isDead()) {
            this.addStatus(RoomEntityStatus.RIP, "");
        } else {
            if (((PetMonsterPlantData) this.getData()).getGrowthStage() == 0) {
                this.addStatus(RoomEntityStatus.GROW, "");
            } else {
                this.addStatus(RoomEntityStatus.fromString("grw" + ((PetMonsterPlantData) this.getData()).getGrowthStage()), "");
            }
        }
    }


    @Override
    public void compose(IComposer msg) {
        PetMonsterPlantData pet = ((PetMonsterPlantData) this.getData());
        msg.writeInt(pet.getId());
        msg.writeString(pet.getPlantName());
        msg.writeString("");
        msg.writeString(pet.getLook().toLowerCase());
        msg.writeInt(this.getId());
        msg.writeInt(this.getPosition().getX());
        msg.writeInt(this.getPosition().getY());
        msg.writeDouble(this.getPosition().getZ());
        msg.writeInt(0);
        msg.writeInt(2);
        msg.writeInt(pet.getRaceId());
        msg.writeInt(pet.getOwnerId());
        msg.writeString(pet.getOwnerName());
        msg.writeInt(pet.getRarity()); // rarity
        msg.writeBoolean(false); // has saddle
        msg.writeBoolean(false); // has rider
        msg.writeBoolean(pet.canBreed()); // can breed
        msg.writeBoolean(pet.isFullyGrown());
        msg.writeBoolean(pet.isDead());
        msg.writeBoolean(pet.isActive()); //reedable checkbox /
        msg.writeInt(pet.getGrowthStage());
        msg.writeString("");
    }

    @Override
    public void composeInformation(IComposer msg) {
        PetMonsterPlantData pet = ((PetMonsterPlantData) this.getData());
        msg.writeInt(pet.getId());
        msg.writeString(pet.getPlantName());
        msg.writeInt(pet.getGrowthStage());
        msg.writeInt(pet.getGrowthStage());
        msg.writeInt(0);
        msg.writeInt(0);
        msg.writeInt(0);
        msg.writeInt(0);
        msg.writeInt(0);
        msg.writeInt(0);
        msg.writeInt(0);
        msg.writeInt(pet.getOwnerId());
        msg.writeInt(this.daysSinceBirthday(pet.getBirthday()));
        msg.writeString(PlayerManager.getInstance().getAvatarByPlayerId(pet.getOwnerId(), PlayerAvatar.USERNAME_FIGURE).getUsername());
        msg.writeInt(pet.getRarity());
        msg.writeBoolean(false);
        msg.writeBoolean(false);
        msg.writeInt(0);
        msg.writeInt(0);
        msg.writeBoolean(pet.canBreed());
        msg.writeBoolean(pet.isFullyGrown());
        msg.writeBoolean(pet.isDead());
        msg.writeInt(pet.getRarity());
        msg.writeInt(pet.getBody().getLifeTime());// time to live
        msg.writeInt(pet.getLastFood()); //
        msg.writeInt(pet.remainingGrowTime());
        msg.writeBoolean(pet.isActive());
    }

    @Override
    public void composeUpdate(IComposer msg) {
        PetMonsterPlantData pet = (PetMonsterPlantData) this.getData();
        msg.writeInt(this.getId());
        msg.writeInt(0); // anyone can ride
        msg.writeBoolean(pet.canBreed()); // can breed
        msg.writeBoolean(pet.isFullyGrown()); // is fully grown
        msg.writeBoolean(pet.isDead()); //is dead
        msg.writeBoolean(pet.isActive());
    }

    public void breed(MonsterPlantEntity plant) {
        PetMonsterPlantData plantData = (PetMonsterPlantData) plant.getData();
        PetMonsterPlantData thisData = (PetMonsterPlantData) this.getData();

        if (plantData.canBreed() && thisData.canBreed()) {
            plantData.setHasBreed(true);
            thisData.setHasBreed(true);

            this.getRoom().getEntities().broadcastMessage(new PetUpdateStatusComposer(this));
            this.getRoom().getEntities().broadcastMessage(new PetUpdateStatusComposer(plant));

            plant.addStatus(RoomEntityStatus.GESTURE, "reb");
            this.addStatus(RoomEntityStatus.GESTURE, "reb");

            this.getRoom().getEntities().broadcastMessage(new AvatarUpdateMessageComposer(this));
            this.getRoom().getEntities().broadcastMessage(new AvatarUpdateMessageComposer(plant));

            plant.removeStatus(RoomEntityStatus.GESTURE);
            this.removeStatus(RoomEntityStatus.GESTURE);


            int fatherSeedRarity = thisData.getSeedRarity();
            int motherSeedRarity = plantData.getSeedRarity();

            int seedRarity = Math.min(fatherSeedRarity, motherSeedRarity);

            int seedItemId = 0;
            final Data<Long> newItem = Data.createEmpty();

            StorageContext.getCurrentContext().getRoomItemRepository().createItem(thisData.getOwnerId(), seedItemId, "" + seedRarity, newItem::set);
            PlayerItem playerItem = new InventoryItem(newItem.get(), seedItemId, "" + seedRarity);

            Session client = NetworkManager.getInstance().getSessions().getByPlayerId(thisData.getOwnerId());
            if (client != null) {
                int rotation = Comet.getRandom().nextInt((4 - 1) + 1) + 1;
                Position position = this.getPosition().squareInFront(rotation);

                RoomTile tile = this.getRoom().getMapping().getTile(position);
                if (tile != null && tile.canPlaceItemHere()) {
                    this.getRoom().getItems().placeFloorItem(playerItem, position.getX(), position.getY(), 0, client.getPlayer(), false);
                    sendNotif(Locale.get("pets.monsterplants.create.seed.room"), client);
                } else {
                    client.getPlayer().getInventory().addItem(playerItem);
                    client.send(new UnseenItemsMessageComposer(Sets.newHashSet(playerItem), ItemManager.getInstance()));
                    sendNotif(Locale.get("pets.monsterplants.create.seed.inventory"), client);
                }
            }
            this.getData().savePlantsData();
            plant.getData().savePlantsData();
        }
    }
}
