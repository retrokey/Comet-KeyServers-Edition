package com.cometproject.server.game.pets;

import com.cometproject.api.game.pets.IPetData;
import com.cometproject.api.game.pets.IPetRace;
import com.cometproject.api.utilities.Initialisable;
import com.cometproject.server.game.pets.data.PetSpeech;
import com.cometproject.server.game.pets.races.PetBreedLevel;
import com.cometproject.server.game.pets.races.PetRace;
import com.cometproject.server.game.pets.races.plants.PetMonsterPlant;
import com.cometproject.server.game.pets.races.plants.PetMonsterPlantColor;
import com.cometproject.server.storage.queries.pets.PetDao;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;


public class PetManager implements Initialisable {
    private static PetManager petManagerInstance;
    private final Map<Integer, IPetData> pendingPetDataSaves = Maps.newConcurrentMap();
    private Logger LOGGER = LoggerFactory.getLogger(PetManager.class.getName());
    private List<PetRace> petRaces;
    private Map<Integer, PetSpeech> petMessages;
    private Map<String, String> transformablePets;
    private Map<Integer, Map<PetBreedLevel, Set<Integer>>> petBreedPallets;
    private ArrayList<PetMonsterPlant> monsterPlantBodies;
    private ArrayList<PetMonsterPlantColor> monsterPlantColors;

    public PetManager() {

    }

    public static PetManager getInstance() {
        if (petManagerInstance == null)
            petManagerInstance = new PetManager();

        return petManagerInstance;
    }

    @Override
    public void initialize() {
        this.loadPetRaces();
        this.loadPetBreedPallets();
        this.loadPetSpeech();
        this.loadTransformablePets();
        this.loadMonsterPlantsRaces();

        // Set up the queue for saving pet data
        // CometThreadManager.getInstance().executePeriodic(this::savePetStats, 1000, 1000, TimeUnit.MILLISECONDS);

        LOGGER.info("PetManager initialized");
    }

    public void loadPetRaces() {
        if (this.petRaces != null) {
            this.petRaces.clear();
        }

        try {
            this.petRaces = PetDao.getRaces();

            LOGGER.info("Loaded " + this.petRaces.size() + " pet races");
        } catch (Exception e) {
            LOGGER.error("Error while loading pet races", e);
        }
    }

    public void loadPetBreedPallets() {
        if (this.petBreedPallets != null) {
            this.petBreedPallets.clear();
        }

        try {
            this.petBreedPallets = PetDao.getPetBreedPallets();

            LOGGER.info("Loaded " + this.petBreedPallets.size() + " pet breed pallet sets");
        } catch (Exception e) {
            LOGGER.error("Error while loading pet breed pallets", e);
        }
    }

    public void loadPetSpeech() {
        if (this.petMessages != null) {
            this.petMessages.clear();
        }

        try {
            AtomicInteger petSpeechCount = new AtomicInteger(0);
            this.petMessages = PetDao.getMessages(petSpeechCount);

            LOGGER.info("Loaded " + this.petMessages.size() + " pet message sets and " + petSpeechCount.get() + " total messages");
        } catch (Exception e) {
            LOGGER.error("Error while loading pet messages");
        }
    }

    public void loadTransformablePets() {
        if (this.transformablePets != null) {
            this.transformablePets.clear();
        }

        try {
            this.transformablePets = PetDao.getTransformablePets();

            LOGGER.info("Loaded " + this.transformablePets.size() + " transformable pets");
        } catch (Exception e) {
            LOGGER.error("Error while loading transformable pets");
        }
    }

    public int validatePetName(String petName) {
        String pattern = "^[a-zA-Z0-9]*$";

        if (petName.length() <= 0) {
            return 1;
        }

        if (petName.length() > 16) {
            return 2;
        }

        if (!petName.matches(pattern)) {
            return 3;
        }

        return 0;
    }

    public List<IPetRace> getRacesByRaceId(int raceId) {
        List<IPetRace> races = new ArrayList<>();

        for (PetRace race : this.getPetRaces()) {
            if (raceId == race.getRaceId())
                races.add(race);
        }

        return races;
    }

    public void loadMonsterPlantsRaces() {
        this.monsterPlantBodies = new ArrayList<>();
        this.monsterPlantBodies.add(new PetMonsterPlant(1, "Amnesia", 0, 3600, 300));
        this.monsterPlantBodies.add(new PetMonsterPlant(5, "Supersilver", 1, 3600, 300));
        this.monsterPlantBodies.add(new PetMonsterPlant(2, "Cookies", 2, 3600, 300));
        this.monsterPlantBodies.add(new PetMonsterPlant(3, "Stumpy", 3, 3600, 300));
        this.monsterPlantBodies.add(new PetMonsterPlant(4, "Calamity", 4, 3600, 480));
        this.monsterPlantBodies.add(new PetMonsterPlant(9, "Blueberry", 5, 3600, 480));
        this.monsterPlantBodies.add(new PetMonsterPlant(6, "Shroomer", 6, 3600, 480));
        this.monsterPlantBodies.add(new PetMonsterPlant(7, "Moby Dick", 7, 3600, 960));
        this.monsterPlantBodies.add(new PetMonsterPlant(10, "AK 47", 8, 3600, 960));
        this.monsterPlantBodies.add(new PetMonsterPlant(11, "Skywalker", 8, 3600, 1920));
        this.monsterPlantBodies.add(new PetMonsterPlant(12, "Gorilla Glue", 9, 3600, 1920));
        this.monsterPlantBodies.add(new PetMonsterPlant(8, "Hindu", 10, 3600, 1920));
        // 60 * 60 * 12

        this.monsterPlantColors = new ArrayList<>();
        this.monsterPlantColors.add(new PetMonsterPlantColor("OG", 0));
        this.monsterPlantColors.add(new PetMonsterPlantColor("Gelatto", 1));
        this.monsterPlantColors.add(new PetMonsterPlantColor("Goat", 2));
        this.monsterPlantColors.add(new PetMonsterPlantColor("G13", 3));
        this.monsterPlantColors.add(new PetMonsterPlantColor("Kush", 4));
        this.monsterPlantColors.add(new PetMonsterPlantColor("Incarnatus", 5));
        this.monsterPlantColors.add(new PetMonsterPlantColor("Amethyst", 6));
        this.monsterPlantColors.add(new PetMonsterPlantColor("Haze", 7));
        this.monsterPlantColors.add(new PetMonsterPlantColor("Atamasc", 8));
        this.monsterPlantColors.add(new PetMonsterPlantColor("Azureus", 9));
        this.monsterPlantColors.add(new PetMonsterPlantColor("Cyaneus", 10));
    }

    public ArrayList<PetMonsterPlant> getMonsterPlantBodies() {
        return monsterPlantBodies;
    }

    public ArrayList<PetMonsterPlantColor> getMonsterPlantColors() {
        return monsterPlantColors;
    }

    public List<PetRace> getPetRaces() {
        return this.petRaces;
    }

    public PetSpeech getSpeech(int petType) {
        return this.petMessages.get(petType);
    }

    public Map<String, String> getTransformablePets() {
        return transformablePets;
    }

    public String getTransformationData(String type) {
        return this.transformablePets.get(type);
    }

    public Map<Integer, Map<PetBreedLevel, Set<Integer>>> getPetBreedPallets() {
        return petBreedPallets;
    }

}
