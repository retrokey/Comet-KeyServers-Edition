package com.cometproject.server.network.messages.incoming.room.pets.monsterplants;

import com.cometproject.api.game.GameContext;
import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.api.game.groups.types.IGroup;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.pets.data.PetMonsterPlantData;
import com.cometproject.server.game.rooms.objects.entities.types.MonsterPlantEntity;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;
import com.cometproject.server.storage.queries.pets.PetDao;

public class MonsterPlantSetBreedableMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        final int petId = msg.readInt();

        if (client.getPlayer().getEntity() == null) {
            return;
        }

        final MonsterPlantEntity monsterPlant = client.getPlayer().getEntity().getRoom().getEntities().getEntityByPlantId(petId);
        PetMonsterPlantData plantData = ((PetMonsterPlantData) monsterPlant.getData());

        if(plantData != null){
            if(plantData.getOwnerId() != client.getPlayer().getId())
                return;


            int seedReward;
            seedReward = plantData.getRarity();

            if(seedReward == 0){
                seedReward = 1;
            }

            client.getPlayer().sendBubble("seed_reward", Locale.getOrDefault("seed.sold",
                    "Acabas de vender una %name% en el mercado negro, tu mafia obtiene %value% en Dinero Negro, recibes una comisión de 1 Koin por tu trabajo realizado.")
                    .replace("%name%", plantData.getPlantName())
                    .replace("%value%", seedReward + ""));

            client.getPlayer().getData().increaseCredits(1);
            client.getPlayer().getData().save();
            client.getPlayer().sendBalance();

            monsterPlant.leaveRoom(false);
            PetDao.deletePet(client.getPlayer().getId(), plantData.getId());

            client.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_131, 1);
        }
    }
}
