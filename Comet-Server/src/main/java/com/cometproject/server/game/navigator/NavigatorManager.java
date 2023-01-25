package com.cometproject.server.game.navigator;

import com.cometproject.api.utilities.Initialisable;
import com.cometproject.server.game.navigator.types.Category;
import com.cometproject.server.game.navigator.types.categories.NavigatorCategoryType;
import com.cometproject.server.game.navigator.types.publics.PublicRoom;
import com.cometproject.server.storage.queries.navigator.NavigatorDao;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class NavigatorManager implements Initialisable {
    private static NavigatorManager navigatorManagerInstance;
    private final Logger LOGGER = LoggerFactory.getLogger(NavigatorManager.class.getName());
    private Map<Integer, Category> categories;
    private List<Category> userCategories;
    private Map<Integer, PublicRoom> publicRooms;
    private Set<Integer> staffPicks;
    private Set<Integer> rolePlayRooms;

    public NavigatorManager() {
    }

    public static NavigatorManager getInstance() {
        if (navigatorManagerInstance == null)
            navigatorManagerInstance = new NavigatorManager();

        return navigatorManagerInstance;
    }

    @Override
    public void initialize() {
        this.loadCategories();
        this.loadPublicRooms();
        this.loadStaffPicks();
        this.loadRolePlayRooms();

        LOGGER.info("NavigatorManager initialized");
    }

    public void loadPublicRooms() {
        try {
            if (this.publicRooms != null && this.publicRooms.size() != 0) {
                this.publicRooms.clear();
            }

            this.publicRooms = NavigatorDao.getPublicRooms();

        } catch (Exception e) {
            LOGGER.error("Error while loading public rooms", e);
        }

        LOGGER.info("Loaded " + this.publicRooms.size() + " featured rooms");
    }

    public void loadStaffPicks() {
        try {
            if (this.staffPicks != null && this.staffPicks.size() != 0) {
                this.staffPicks.clear();
            }

            this.staffPicks = NavigatorDao.getStaffPicks();

        } catch (Exception e) {
            LOGGER.error("Error while loading staff picked rooms", e);
        }

        LOGGER.info("Loaded " + this.publicRooms.size() + " staff picks");
    }

    public void loadRolePlayRooms() {
        try {
            if (this.rolePlayRooms != null && this.rolePlayRooms.size() != 0) {
                this.rolePlayRooms.clear();
            }

            this.rolePlayRooms = NavigatorDao.getRPRooms();

        } catch (Exception e) {
            LOGGER.error("Error while loading roleplay picked rooms", e);
        }

        LOGGER.info("Loaded " + this.rolePlayRooms.size() + " roleplay picks");
    }

    public void loadCategories() {
        try {
            if (this.categories != null && this.categories.size() != 0) {
                this.categories.clear();
            }

            if (this.userCategories == null) {
                this.userCategories = Lists.newArrayList();
            } else {
                this.userCategories.clear();
            }

            this.categories = NavigatorDao.getCategories();

            for (Category category : this.categories.values()) {
                if (category.getCategoryType() == NavigatorCategoryType.CATEGORY) {
                    this.userCategories.add(category);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error while loading navigator categories", e);
        }

        LOGGER.info("Loaded " + (this.getCategories() == null ? 0 : this.getCategories().size()) + " room categories");
    }

    public Category getCategory(int id) {
        return this.categories.get(id);
    }

    public boolean isStaffPicked(int roomId) {
        return this.staffPicks.contains(roomId);
    }

    public PublicRoom getPublicRoom(int roomId) {
        return this.publicRooms.get(roomId);
    }

    public Map<Integer, Category> getCategories() {
        return this.categories;
    }

    public Map<Integer, PublicRoom> getPublicRooms(String category) {
        Map<Integer, PublicRoom> pRooms = new LinkedHashMap<>();

        for (PublicRoom publicRoom : this.publicRooms.values()) {
           if(publicRoom.getCategory().equals(category))
               pRooms.put(publicRoom.getRoomId(), publicRoom);

        }

        return pRooms;
    }

    public boolean isPublicRoom(int roomId){
        for (PublicRoom publicRoom : this.publicRooms.values()) {
            if(publicRoom.getRoomId() == roomId)
                return true;
        }
        return false;
    }

    public Set<Integer> getStaffPicks() {
        return staffPicks;
    }

    public Set<Integer> getRolePlayRooms() {
        return rolePlayRooms;
    }

    public List<Category> getUserCategories() {
        return userCategories;
    }
}
