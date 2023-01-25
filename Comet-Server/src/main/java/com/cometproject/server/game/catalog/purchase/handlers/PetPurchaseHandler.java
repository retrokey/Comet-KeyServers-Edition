package com.cometproject.server.game.catalog.purchase.handlers;

import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.api.game.catalog.types.ICatalogItem;
import com.cometproject.server.composers.catalog.UnseenItemsMessageComposer;
import com.cometproject.server.game.catalog.purchase.PurchaseHandler;
import com.cometproject.server.game.catalog.purchase.PurchaseResult;
import com.cometproject.server.game.pets.data.PetData;
import com.cometproject.server.game.pets.data.StaticPetProperties;
import com.cometproject.server.network.messages.outgoing.user.inventory.PetInventoryMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.storage.queries.pets.PetDao;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;

public class PetPurchaseHandler implements PurchaseHandler {
    @Override
    public PurchaseResult handlePurchaseData(Session session, String purchaseData, ICatalogItem catalogItem, int amount) {
        String petRace = catalogItem.getDisplayName().replace("a0 pet", "");
        String[] petData = purchaseData.split("\n"); // [0:name, 1:race, 2:colour]

        if (petData.length != 3) {
            return null;
        }

        int petId = PetDao.createPet(session.getPlayer().getId(), petData[0], Integer.parseInt(petRace), Integer.parseInt(petData[1]), petData[2], "");

        session.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_36, 1);
        session.getPlayer().getPets().addPet(new PetData(petId, petData[0], 0, StaticPetProperties.DEFAULT_LEVEL, StaticPetProperties.DEFAULT_HAPPINESS, StaticPetProperties.DEFAULT_EXPERIENCE, StaticPetProperties.DEFAULT_ENERGY, StaticPetProperties.DEFAULT_HUNGER, session.getPlayer().getId(), session.getPlayer().getData().getUsername(), petData[2], Integer.parseInt(petData[1]), Integer.parseInt(petRace)));
        session.send(new PetInventoryMessageComposer(session.getPlayer().getPets().getPets()));

        session.send(new UnseenItemsMessageComposer(new HashMap<Integer, List<Integer>>() {{
            put(3, Lists.newArrayList(petId));
        }}));

        return null;
    }
}
