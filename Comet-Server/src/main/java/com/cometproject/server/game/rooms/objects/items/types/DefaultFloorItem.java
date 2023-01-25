package com.cometproject.server.game.rooms.objects.items.types;

import com.cometproject.api.config.Configuration;
import com.cometproject.api.game.quests.QuestType;
import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.storage.queries.items.ItemDao;
import com.cometproject.server.utilities.RandomUtil;

import java.util.Timer;
import java.util.TimerTask;


public class DefaultFloorItem extends RoomItemFloor {
    public DefaultFloorItem(RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    @Override
    public boolean onInteract(RoomEntity entity, int requestData, boolean isWiredTrigger) {
        if (!isWiredTrigger) {
            if (!(entity instanceof PlayerEntity)) {
                return false;
            }

            PlayerEntity pEntity = (PlayerEntity) entity;
            if(pEntity.getPlayer().getData().isSearchFurniActive){
                int furniId = ItemDao.getFurniIdByItem(this.getId());
                if(furniId != 0){
                    int catalogId = ItemDao.getCatalogIdByItem(furniId);
                    if(catalogId != 0){
                        String catalogName = ItemDao.getCatalogNameByCatalogId(catalogId);
                        String messageFurni = "Este furni se encuentra en la sección " + catalogName + ". [" + furniId +"]";

                        pEntity.getPlayer().getSession().send(new WhisperMessageComposer(pEntity.getPlayer().getEntity().getId(), messageFurni, 1));
                    }
                    else pEntity.getPlayer().getSession().send(new WhisperMessageComposer(pEntity.getPlayer().getEntity().getId(), "Este furni no se ha podido encontrar en el catálogo.", 1));
                }
                else pEntity.getPlayer().getSession().send(new WhisperMessageComposer(pEntity.getPlayer().getEntity().getId(), "Este furni no se ha podido encontrar en el catálogo.", 1));
            }

            if((this.getItemData().getItemId() == 1000008108 || this.getItemData().getItemId() == 9974) && this.getRoom().getId() == Integer.parseInt(Configuration.currentConfig().get("comet.recibidor.id"))){
                if (!this.getPosition().touching(entity.getPosition())) {
                    entity.moveTo(this.getPosition().squareInFront(this.getRotation()).getX(), this.getPosition().squareBehind(this.getRotation()).getY());
                    return false;
                }
                //WebSocketSessionManager.getInstance().sendMessage(playerEntity.getPlayer().getSession().getWsChannel(), new OpenBadgeShopWebPacket(playerEntity.getPlayer().getData().getVipPoints()));
                RoomItemData rData = (RoomItemData)this.getItemData();
                if(!rData.isOnCooldown){
                    rData.isOnCooldown = true;

                    int diamondsBonus = RandomUtil.getRandomInt(0,5);
                    int creditsBonus = RandomUtil.getRandomInt(0,15);
                    pEntity.getPlayer().getData().increaseActivityPoints(diamondsBonus);
                    pEntity.getPlayer().getData().increaseCredits(creditsBonus);
                    pEntity.getPlayer().sendBalance();
                    pEntity.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new WhisperMessageComposer(pEntity.getPlayer().getEntity().getId(), "Has recibido " + diamondsBonus + " asteroides y " + creditsBonus + " créditos de bonus.", 11));
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            rData.isOnCooldown = false;
                        }
                    }, 600000);
                }
                else pEntity.getPlayer().getSession().send(new WhisperMessageComposer(pEntity.getPlayer().getEntity().getId(), "Debes esperar 10 minutos para recoger los cometas.", 1));
            }

            if (this.getDefinition().requiresRights()) {
                if (!pEntity.getRoom().getRights().hasRights(pEntity.getPlayerId()) && !pEntity.getPlayer().getPermissions().getRank().roomFullControl()) {
                    return false;
                }
            }

            if (pEntity.getPlayer().getId() == this.getRoom().getData().getOwnerId()) {
                pEntity.getPlayer().getQuests().progressQuest(QuestType.FURNI_SWITCH);
            }
        }

        this.toggleInteract(true);
        this.sendUpdate();

        this.saveData();
        return true;
    }

    @Override
    public void onEntityStepOn(RoomEntity entity) {
        if (entity instanceof PlayerEntity) {
            try {
                ((PlayerEntity) entity).getPlayer().getQuests().progressQuest(QuestType.EXPLORE_FIND_ITEM, this.getDefinition().getSpriteId());
            } catch (Exception ignored) {
            }
        }
    }
}
