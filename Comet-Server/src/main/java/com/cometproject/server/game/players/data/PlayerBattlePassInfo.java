package com.cometproject.server.game.players.data;

import com.cometproject.api.config.CometSettings;
import com.cometproject.api.game.players.data.components.inventory.PlayerItem;
import com.cometproject.server.composers.catalog.UnseenItemsMessageComposer;
import com.cometproject.server.game.achievements.BattlePassGlobals;
import com.cometproject.server.game.achievements.types.BattlePassMission;
import com.cometproject.server.game.achievements.types.BattlePassMissionEnums;
import com.cometproject.server.game.achievements.types.BattlePassRewardEnum;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.players.components.types.inventory.InventoryItem;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.inventory.UpdateInventoryMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.network.websockets.WebSocketSessionManager;
import com.cometproject.server.storage.queries.player.PlayerDao;
import com.cometproject.storage.api.StorageContext;
import com.cometproject.storage.api.data.Data;
import com.google.common.collect.Sets;

import java.util.ArrayList;

public class PlayerBattlePassInfo {
    public int playerId;
    public int level;
    public int exp;
    public ArrayList<Integer> completedMissions = new ArrayList<Integer>();

    public PlayerBattlePassInfo(int level, int exp){
        this.level = level;
        this.exp = exp;
    }

    public void addExperiencePoint(int mission){
        if(this.level != mission) return;

        BattlePassMission ms = BattlePassGlobals.battlePassMissions.stream().filter(x -> x.id == mission).findAny().orElse(null);
        if(ms == null) return;

        int matches = 0;
        for(int completed : completedMissions){
            if(mission == completed) matches++;
        }

        if(matches >= ms.maxExp) return;

        Session player = NetworkManager.getInstance().getSessions().getByPlayerId(this.playerId);
        if(player == null) return;

        this.exp++;
        matches++;

        completedMissions.add(mission);
        PlayerDao.InsertBattlePassCompleted(mission, this.playerId);


        if(matches == ms.maxExp){
            player.send(new NotificationMessageComposer("generic", "¡Felicidades! Has completado el nivel " + ms.id + "."));
            this.level++;
            this.giveReward(ms, player);
        }
        else player.send(new NotificationMessageComposer("generic", "Has conseguido 1 exp en el nivel " + ms.id + " del GalaxyPass."));

        PlayerDao.UpdateBattlePass(this);
        player.getPlayer().getData().updateBattlePass();
    }

    public void giveReward(BattlePassMission ms, Session player){
        if(ms.rewardType == BattlePassRewardEnum.RewardType.BADGE){
            player.getPlayer().getInventory().addBadge(ms.rewardReference, true);
            player.send(new NotificationMessageComposer("generic", "Has recibido una placa como recompensa por subir al nivel " + this.level));
        }

        if(ms.rewardType == BattlePassRewardEnum.RewardType.DIAMONDS){
            try{
                int diamonds = Integer.parseInt(ms.rewardReference);
                player.getPlayer().getData().increaseActivityPoints(diamonds);
                player.getPlayer().sendBalance();
                player.send(new NotificationMessageComposer("generic", "Has recibido " + diamonds + " asteroides como recompensa por subir al nivel " + this.level));
            }
            catch (Exception ex) { }
        }

        if(ms.rewardType == BattlePassRewardEnum.RewardType.RARE){
            try{
                int itemId = Integer.parseInt(ms.rewardReference);

                final String itemExtraData = "0";

                final Data<Long> itemIdData = Data.createEmpty();

                StorageContext.getCurrentContext().getRoomItemRepository().createItem(player.getPlayer().getId(), itemId, itemExtraData, itemIdData::set);

                final PlayerItem playerItem = new InventoryItem(itemIdData.get(), itemId, itemExtraData);

                player.getPlayer().getInventory().addItem(playerItem);

                player.send(new UpdateInventoryMessageComposer());

                player.send(new UnseenItemsMessageComposer(Sets.newHashSet(playerItem), ItemManager.getInstance()));
                player.send(new NotificationMessageComposer("generic", "Has recibido un rare como recompensa por subir al nivel " + this.level));
            }
            catch (Exception ex) { }
        }

        if(ms.id == 10){
            player.getPlayer().getInventory().addBadge("GLXF5", true);
            player.getPlayer().getData().increaseVipPoints(5);
            player.getPlayer().getData().flush();
        }
    }

    public int countExpMission(int mission){
        int matches = 0;
        for(int completed : completedMissions){
            if(mission == completed) matches++;
        }

        return matches;
    }
}
