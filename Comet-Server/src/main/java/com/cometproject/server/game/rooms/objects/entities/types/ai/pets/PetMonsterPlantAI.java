package com.cometproject.server.game.rooms.objects.entities.types.ai.pets;

import com.cometproject.api.game.rooms.entities.RoomEntityStatus;
import com.cometproject.server.game.pets.data.PetMonsterPlantData;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.MonsterPlantEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.network.messages.outgoing.room.avatar.AvatarsMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.pets.PetUpdateStatusComposer;

import java.util.Random;

public class PetMonsterPlantAI extends PetAI {

    public PetMonsterPlantAI(RoomEntity entity) {
        super(entity);
        this.setTicksUntilCompleteInSeconds(1);
    }

    @Override
    public boolean onPlayerEnter(PlayerEntity entity) {
        if (((PetMonsterPlantData) (this.getPetEntity()).getData()).isActive()) {
            return super.onPlayerEnter(entity);
        }
        return false;
    }

    @Override
    public boolean onAddedToRoom() {
        if (((PetMonsterPlantData) (this.getPetEntity()).getData()).isActive()) {
            return super.onAddedToRoom();
        }
        return false;
    }

    @Override
    public void onTickComplete() {

        if (this.getEntity().getRoom() != null) {
            PetMonsterPlantData data = ((PetMonsterPlantData) (this.getPetEntity()).getData());
            this.setTicksUntilCompleteInSeconds(5);

            if (!data.isFullyGrown() && !data.isDead()) {
                int stage = data.getRealGrowthStage();
                if (data.getGrowthStage() != stage) {
                    data.setGrowthStage(stage);
                    data.save();
                    this.clearStatus();
                    this.getEntity().addStatus(RoomEntityStatus.GESTURE, RoomEntityStatus.FLASH.getStatusCode());
                    ((MonsterPlantEntity) this.getEntity()).setGrowth();
                    if (stage == 7) {
                        ((MonsterPlantEntity) this.getEntity()).sendGrowth();
                        this.getEntity().getRoom().getEntities().broadcastMessage(new AvatarsMessageComposer(this.getPetEntity()));
                        this.getEntity().getRoom().getEntities().broadcastMessage(new PetUpdateStatusComposer(this.getPetEntity()));
                    } else {
                        this.getEntity().markNeedsUpdate();
                    }
                }
            }

            if (!data.asDead()) {
                if (data.isDead()) {
                    data.setDead(true);
                    data.save();
                    this.clearStatus();
                    ((MonsterPlantEntity) this.getEntity()).sendGrowth();
                    this.getEntity().getRoom().getEntities().broadcastMessage(new PetUpdateStatusComposer(this.getPetEntity()));
                }
            }

            if (data.isFullyGrown() && !data.isDead()) {
                if (data.getRarity() >= 20 && data.getLevel() < 20) {
                    data.setLevel(20);
                    data.save();
                }
                int diamondsStocked = data.diamondsStocked();
                if (diamondsStocked != 0) {
                    this.giveDiamonds(diamondsStocked);
                } else {
                    RoomEntityStatus status = this.randomStatus();
                    if (status != null) {
                        this.applyGesture(status.getStatusCode());
                    }
                }
            }
        }

    }

    public RoomEntityStatus randomStatus() {
        int[] array = {1, 1, 1, 1, 2, 2, 2, 3, 3, 3};
        int pick = new Random().nextInt(array.length);

        int rand = array[pick];

        switch (rand) {
            case 0:
                return null;
            case 1:
                return RoomEntityStatus.EAT;
            case 2:
                return RoomEntityStatus.HAPPY;
            case 3:
                return RoomEntityStatus.SAD;
        }
        return null;
    }

    public void giveDiamonds(int time) {
        /*
        int count = 1;
        int diamondsTime = PetManager.getInstance().getMonsterPlantDiamondsCycle();
        PetMonsterPlantData data = ((PetMonsterPlantData) (this.getPetEntity()).getData());
        int value = data.getMaxDiamonds() / 720;


        if(time > (diamondsTime * 5))
        {
            count += 4;
        }
        else if(time > (diamondsTime * 4))
        {
            count += 3;
        }
        else if(time > (diamondsTime * 3))
        {
            count += 2;
        }
        else if(time > (diamondsTime * 2))
        {
            count += 1;
        }
        int quantity = (count * (int) (Math.round((value) / 10.0) * 10));
        int size = quantity / 10;

        int diamondsId = PetManager.getInstance().getMonsterPlantDiamondsItem();
        ItemDefinition itemDefinition = ItemManager.getInstance().getDefinition(diamondsId);
        if(itemDefinition != null)
        {
            for(int i = 0; i < size; i++)
            {
                int rotation = Comet.getRandom().nextInt((4 - 1) + 1) + 1;
                long newItem = ItemDao.createItem(data.getOwnerId(), diamondsId, "");
                PlayerItem playerItem = new InventoryItem(newItem, diamondsId, "");
                Session client = NetworkManager.getInstance().getSessions().fromPlayer(data.getOwnerId());
                if(client != null)
                {
                    Position position = this.getEntity().getPosition().squareInFront(rotation);

                    RoomTile tile = this.getEntity().getRoom().getMapping().getTile(position);
                    if(tile != null && tile.canPlaceItemHere())
                    {
                        this.getEntity().getRoom().getItems().placeFloorItem(playerItem, position.getX(), position.getY(), 0, client.getPlayer());
                        sendNotif(Locale.get("pets.monsterplants.create.diamonds.room"), client);
                    }
                    else
                    {
                        client.getPlayer().getInventory().addItem(playerItem);
                        client.send(new UpdateInventoryMessageComposer());
                        client.send(new UnseenItemsMessageComposer(Sets.newHashSet(playerItem)));
                        sendNotif(Locale.get("pets.monsterplants.create.diamonds.inventory"), client);
                    }

                    this.clearStatus();
                    this.applyGesture(RoomEntityStatus.HAPPY.getStatusCode());
                }
            }

        }

        data.setLastDiamonds((int) Comet.getTime());
        data.increaseGivenDiamonds(10 * size);
        data.save();
        */
    }

    public void clearStatus() {
        this.getEntity().getStatuses().clear();
    }

    @Override
    public void onTick() {
        super.onTick();
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public boolean onTalk(PlayerEntity entity, String message) {
        if (((PetMonsterPlantData) (this.getPetEntity()).getData()).isActive()) {
            return super.onTalk(entity, message);
        }
        return false;
    }
}