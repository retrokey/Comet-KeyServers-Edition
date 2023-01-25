package com.cometproject.server.game.rooms.types.components;

import com.cometproject.api.game.pets.IPetData;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.game.pets.data.PetMonsterPlantData;
import com.cometproject.server.game.rooms.objects.entities.types.MonsterPlantEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PetEntity;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.storage.queries.pets.RoomPetDao;


public class PetComponent {

    private Room room;

    public PetComponent(Room room) {
        this.room = room;

        this.load();
    }

    public void load() {
        for (IPetData data : this.room.getCachedData() != null ? this.room.getCachedData().getPets() :
                RoomPetDao.getPetsByRoomId(this.room.getId())) {
            this.loadPet(data);
        }
    }

    private void loadPet(IPetData petData) {
        if (petData instanceof PetMonsterPlantData) {
            MonsterPlantEntity petEntity = new MonsterPlantEntity(petData, room.getEntities().getFreeId(), petData.getRoomPosition(), 3, 3, room);
            this.getRoom().getEntities().addEntity(petEntity);
        } else {
            PetEntity petEntity = new PetEntity(petData, room.getEntities().getFreeId(), petData.getRoomPosition(), 3, 3, room);
            this.getRoom().getEntities().addEntity(petEntity);
        }
    }


    public PetEntity addPet(IPetData pet, Position position) {
        RoomPetDao.updatePet(this.room.getId(), position.getX(), position.getY(), pet.getId());

        int virtualId = room.getEntities().getFreeId();
        PetEntity petEntity = new PetEntity(pet, virtualId, position, 3, 3, room);
        this.getRoom().getEntities().addEntity(petEntity);

        return petEntity;
    }

    public MonsterPlantEntity addMonsterPlant(PetMonsterPlantData pet, Position position) {
        pet.setRoomId(this.room.getId());
        pet.setRoomPosition(position);
        pet.save();
        MonsterPlantEntity petEntity = new MonsterPlantEntity(pet, room.getEntities().getFreeId(), position, 3, 3, room);
        this.getRoom().getEntities().addEntity(petEntity);

        return petEntity;
    }

    public Room getRoom() {
        return this.room;
    }
}
