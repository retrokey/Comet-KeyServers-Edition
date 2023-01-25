package com.cometproject.api.game;

import com.cometproject.api.game.catalog.ICatalogService;
import com.cometproject.api.game.furniture.IFurnitureService;
import com.cometproject.api.game.groups.IGroupService;
import com.cometproject.api.game.players.IPlayerService;
import com.cometproject.api.game.rooms.IRoomService;
import com.cometproject.api.game.rooms.models.IRoomModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameContext {
    private static GameContext gameContext;

    private ICatalogService catalogService;
    private IFurnitureService furnitureService;
    private IGroupService groupService;
    private IPlayerService playerService;
    private IRoomService roomService;
    private IRoomModelService roomModelService;

    private static final Logger LOGGER = LoggerFactory.getLogger(GameContext.class);

    public ICatalogService getCatalogService() {
        return catalogService;
    }

    public void setCatalogService(ICatalogService catalogService) {
        LOGGER.info("CatalogService initialised, " + catalogService.getClass().getName());

        this.catalogService = catalogService;
    }

    public IFurnitureService getFurnitureService() {
        return this.furnitureService;
    }

    public void setFurnitureService(IFurnitureService furnitureService) {
        LOGGER.info("FurnitureService initialised, " + furnitureService.getClass().getName());

        this.furnitureService = furnitureService;
    }

    public IGroupService getGroupService() {
         return this.groupService;
    }

    public void setGroupService(IGroupService groupService) {
        LOGGER.info("GroupService initialised, " + groupService.getClass().getName());

        this.groupService = groupService;
    }

    public IPlayerService getPlayerService() {
        return this.playerService;
    }

    public void setPlayerService(IPlayerService playerService) {
        this.playerService = playerService;
    }

    public static GameContext getCurrent() {
        if(gameContext == null) {
            LOGGER.info("GameContext not configured");
            System.exit(0);
        }

        return gameContext;
    }

    public static void setCurrent(GameContext instance) {
        GameContext.gameContext = instance;
    }

    public IRoomService getRoomService() {
        return roomService;
    }

    public void setRoomService(IRoomService roomService) {
        LOGGER.info("RoomService initialised, " + roomService.getClass().getName());

        this.roomService = roomService;
    }

    public IRoomModelService getRoomModelService() {
        return roomModelService;
    }

    public void setRoomModelService(IRoomModelService roomModelService) {
        LOGGER.info("RoomModelService initialised, " + roomModelService.getClass().getName());

        this.roomModelService = roomModelService;
    }
}
