package com.cometproject.server.game.rooms.objects.items.types;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemWall;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.storage.queries.items.ItemDao;


public final class DefaultWallItem extends RoomItemWall {
    public DefaultWallItem(RoomItemData roomItemData, Room room) {
        super(roomItemData, room);
    }

    @Override
    public boolean onInteract(RoomEntity entity, int requestData, boolean isWiredTrigger) {
        if (!isWiredTrigger) {
            if (!(entity instanceof PlayerEntity))
                return false;

            if (entity instanceof PlayerEntity) {
                PlayerEntity playerEntity = (PlayerEntity)entity;
                if(playerEntity.getPlayer().getData().isSearchFurniActive){
                    int furniId = ItemDao.getFurniIdByItem(this.getId());
                    if(furniId != 0){
                        int catalogId = ItemDao.getCatalogIdByItem(furniId);
                        if(catalogId != 0){
                            String catalogName = ItemDao.getCatalogNameByCatalogId(catalogId);
                            String messageFurni = "Este furni se encuentra en la sección " + catalogName + ". [" + furniId +"]";

                            playerEntity.getPlayer().getSession().send(new WhisperMessageComposer(playerEntity.getPlayer().getEntity().getId(), messageFurni, 1));
                        }
                        else playerEntity.getPlayer().getSession().send(new WhisperMessageComposer(playerEntity.getPlayer().getEntity().getId(), "Este furni no se ha podido encontrar en el catálogo.", 1));
                    }
                    else playerEntity.getPlayer().getSession().send(new WhisperMessageComposer(playerEntity.getPlayer().getEntity().getId(), "Este furni no se ha podido encontrar en el catálogo.", 1));
                }
            }

            if (!entity.getRoom().getRights().hasRights(((PlayerEntity) entity).getPlayerId())) {
                return false;
            }
        }

        this.toggleInteract(true);
        this.sendUpdate();

        this.saveData();
        return true;
    }
}
