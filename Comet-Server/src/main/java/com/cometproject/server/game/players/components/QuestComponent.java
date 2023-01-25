package com.cometproject.server.game.players.components;

import com.cometproject.api.game.furniture.types.FurnitureDefinition;
import com.cometproject.api.game.players.IPlayer;
import com.cometproject.api.game.players.data.components.PlayerQuests;
import com.cometproject.api.game.players.data.components.inventory.PlayerItem;
import com.cometproject.api.game.quests.IQuest;
import com.cometproject.api.game.quests.QuestType;
import com.cometproject.api.game.rooms.entities.PlayerRoomEntity;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.composers.catalog.UnseenItemsMessageComposer;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.players.components.types.inventory.InventoryItem;
import com.cometproject.server.game.players.types.PlayerComponent;
import com.cometproject.server.game.quests.QuestManager;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.quests.QuestCompletedMessageComposer;
import com.cometproject.server.network.messages.outgoing.quests.QuestListMessageComposer;
import com.cometproject.server.network.messages.outgoing.quests.QuestStartedMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.engine.RoomForwardMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.inventory.UpdateInventoryMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.purse.UpdateActivityPointsMessageComposer;
import com.cometproject.server.storage.queries.quests.PlayerQuestsDao;
import com.cometproject.storage.api.StorageContext;
import com.cometproject.storage.api.data.Data;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class QuestComponent extends PlayerComponent implements PlayerQuests {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestComponent.class.getName());

    private Map<Integer, Integer> questProgression;

    public QuestComponent(IPlayer player) {
        super(player);

        this.loadQuestProgression();
    }

    @Override
    public void loadQuestProgression() {
        this.questProgression = PlayerQuestsDao.getQuestProgression(this.getPlayer().getId());
    }

    @Override
    public boolean hasStartedQuest(int questId) {
        return this.questProgression.containsKey(questId);
    }

    @Override
    public boolean hasCompletedQuest(int questId) {
        final IQuest quest = QuestManager.getInstance().getById(questId);

        if (quest == null) return false;

        if(this.questProgression == null){
            return false;
        }

        if (this.questProgression.containsKey(questId)) {
            return this.questProgression.get(questId) >= quest.getGoalData();
        }

        return false;
    }

    @Override
    public void startQuest(IQuest quest) {
        if (this.questProgression.containsKey(quest.getId())) {
            // We've already started this quest
            return;
        }

        this.questProgression.put(quest.getId(), 0);
        PlayerQuestsDao.saveProgression(true, this.getPlayer().getId(), quest.getId(), 0);

        this.getPlayer().getSession().send(new QuestStartedMessageComposer(quest, this.getPlayer()));
        this.getPlayer().getSession().send(new QuestListMessageComposer(QuestManager.getInstance().getQuests(), this.getPlayer(), false));

        this.getPlayer().getData().setQuestId(quest.getId());
        this.getPlayer().getData().save();

        this.getPlayer().flush();
    }

    @Override
    public void cancelQuest(int questId) {
        PlayerQuestsDao.cancelQuest(questId, this.getPlayer().getId());
        this.questProgression.remove(questId);
    }

    @Override
    public void progressQuest(QuestType type) {
        this.progressQuest(type, 0);
    }

    @Override
    public void progressQuest(QuestType type, int data) {
        IQuest quest = this.canProgressQuest();

        if (quest == null) {
            return;
        }

        if (quest.getType() != type) {
            return;
        }

        this.deliverQuestPrize(quest, data);
    }

    @Override
    public void progressQuestById(int id, int data) {
        IQuest quest = this.canProgressQuest();

        if(quest == null)
            return;

        if (quest.getId() != id) {
            return;
        }

        this.deliverQuestPrize(quest, data);
    }

    private IQuest canProgressQuest(){
        int questId = this.getPlayer().getData().getQuestId();

        if (questId == 0 || !this.hasStartedQuest(questId)) {
            return null;
        }

        IQuest quest = QuestManager.getInstance().getById(questId);

        if (quest == null) {
            return null;
        }

        if (this.hasCompletedQuest(questId)) {
            return null;
        }

        if(quest.getTimestamp() != 0 && (quest.getTimestamp() > (int)Comet.getTime())) {
            return null;
        }

        return quest;
    }

    private void deliverQuestPrize(IQuest quest, int data){
        int questId = quest.getId();
        int newProgressValue = this.getProgress(questId);

        if (quest.getType() == QuestType.EXPLORE_FIND_ITEM) {
            if (quest.getGoalData() != data) {
                return;
            }

            newProgressValue = quest.getGoalData();
        } else {
            newProgressValue++;
        }

        if (this.questProgression.containsKey(questId)) {
            this.questProgression.replace(questId, newProgressValue);
        }

        if (newProgressValue >= quest.getGoalData()) {
            boolean refreshCreditBalance = false;
            boolean refreshCurrenciesBalance = false;
            int rewardType = 0;

            try {
                switch (quest.getRewardType()) {
                    case ACTIVITY_POINTS:
                        this.getPlayer().getData().increaseActivityPoints(quest.getReward());
                        refreshCurrenciesBalance = true;
                        break;

                    case ACHIEVEMENT_POINTS:
                        this.getPlayer().getData().increaseAchievementPoints(quest.getReward());
                        this.getPlayer().getSession().send(new NotificationMessageComposer("voucher", Locale.get("game.received.achievementPoints").replace("%points%", quest.getReward() + "")));
                        this.getPlayer().poof();
                        break;

                    case VIP_POINTS:
                        this.getPlayer().getData().increaseVipPoints(quest.getReward());
                        refreshCurrenciesBalance = true;
                        rewardType = 105;
                        break;

                    case SEASONAL_POINTS:
                        this.getPlayer().getData().increaseSeasonalPoints(quest.getReward());
                        refreshCurrenciesBalance = true;
                        rewardType = 103;
                        break;

                    case GO_TO_ROOM:
                        this.getPlayer().getSession().send(new RoomForwardMessageComposer(quest.getReward()));
                        break;

                    case CANDY_CHEST:
                        this.getPlayer().getSession().send(new NotificationMessageComposer("candy", Locale.getOrDefault("action.candy.recieved", "Acabas de recibir la cesta con caramelos."), "catalog/open"));
                        this.getPlayer().getData().increaseSeasonalPoints(quest.getReward());
                        refreshCurrenciesBalance = true;
                        break;

                    case ITEM:
                        String extraData = "0";

                        int itemId = quest.getReward();

                        FurnitureDefinition itemDefinition = ItemManager.getInstance().getDefinition(itemId);

                        IPlayer e = this.getPlayer();

                        if (itemDefinition != null) {
                            final Data<Long> newItem = Data.createEmpty();
                            StorageContext.getCurrentContext().getRoomItemRepository().createItem(e.getData().getId(), itemId, extraData, newItem::set);

                            PlayerItem playerItem = new InventoryItem(newItem.get(), itemId, extraData);

                            ((PlayerRoomEntity) e).getPlayer().getInventory().addItem(playerItem);

                            e.getSession().send(new UpdateInventoryMessageComposer());
                            e.getSession().send(new UnseenItemsMessageComposer(Sets.newHashSet(playerItem), ItemManager.getInstance()));
                        }
                        break;
                    case CREDITS:
                        this.getPlayer().getData().increaseCredits(quest.getReward());
                        refreshCreditBalance = true;
                        break;
                }
                if (!quest.getBadgeId().isEmpty()) {
                    // Deliver badge
                    this.getPlayer().getInventory().addBadge(quest.getBadgeId(), true);
                }
            } catch (Exception e) {
                LOGGER.error("Failed to deliver reward to player: " + this.getPlayer().getData().getUsername());
            }

            if (refreshCreditBalance) {
                this.getPlayer().getSession().send(this.getPlayer().composeCreditBalance());
            } else if (refreshCurrenciesBalance) {
                this.getPlayer().getSession().send(this.getPlayer().composeCurrenciesBalance());
                this.getPlayer().getSession().send(new UpdateActivityPointsMessageComposer(this.getPlayer().getData().getSeasonalPoints(), quest.getReward(), rewardType));
            }
            this.getPlayer().getSession().send(new QuestCompletedMessageComposer(quest, this.getPlayer()));
            this.getPlayer().getSession().send(new QuestListMessageComposer(QuestManager.getInstance().getQuests(), this.getPlayer(), true));
        } else{
            this.getPlayer().getSession().send(new QuestStartedMessageComposer(quest, this.getPlayer()));
        }

        this.getPlayer().getData().save();
        PlayerQuestsDao.saveProgression(false, this.getPlayer().getId(), questId, newProgressValue);
    }

    @Override
    public int getProgress(int quest) {
        if (this.questProgression.containsKey(quest)) {
            return this.questProgression.get(quest);
        }

        return 0;
    }

    @Override
    public void dispose() {
        super.dispose();

        this.questProgression.clear();
        this.questProgression = null;
    }
}
