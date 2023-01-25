package com.cometproject.server.game;

import com.cometproject.api.config.CometSettings;
import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.api.game.players.data.components.inventory.PlayerItem;
import com.cometproject.api.networking.sessions.ISession;
import com.cometproject.api.utilities.Initialisable;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.composers.catalog.UnseenItemsMessageComposer;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.moderation.BanManager;
import com.cometproject.server.game.players.components.types.inventory.InventoryItem;
import com.cometproject.server.game.players.data.PlayerData;
import com.cometproject.server.game.rooms.RoomManager;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.details.UserObjectMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.inventory.UpdateInventoryMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.purse.UpdateActivityPointsMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.storage.queries.player.PlayerDao;
import com.cometproject.server.storage.queries.system.StatisticsDao;
import com.cometproject.server.tasks.CometTask;
import com.cometproject.server.tasks.CometThreadManager;
import com.cometproject.storage.api.StorageContext;
import com.cometproject.storage.api.data.Data;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class GameCycle implements CometTask, Initialisable {
    private static final int interval = 1;

    private static GameCycle gameThreadInstance;

    private static final Logger LOGGER = LoggerFactory.getLogger(GameCycle.class);

    private ScheduledFuture gameFuture;

    private boolean active = false;

    private int currentOnlineRecord = StatisticsDao.getPlayerRecord();
    private int onlineRecord = 0;

    public GameCycle() {

    }

    public static GameCycle getInstance() {
        if (gameThreadInstance == null)
            gameThreadInstance = new GameCycle();

        return gameThreadInstance;
    }

    @Override
    public void initialize() {
        this.gameFuture = CometThreadManager.getInstance().executePeriodic(this, interval, interval, TimeUnit.MINUTES);
        this.active = true;

        this.onlineRecord = StatisticsDao.getPlayerRecord();
    }

    @Override
    public void run() {
        try {
            if (!this.active) {
                return;
            }

            BanManager.getInstance().processBans();

            final int usersOnline = NetworkManager.getInstance().getSessions().getUsersOnlineCount();
            boolean updateOnlineRecord = false;

            if (usersOnline >= this.currentOnlineRecord) {
                this.currentOnlineRecord = usersOnline;
            }

            if (usersOnline >= this.onlineRecord) {
                this.onlineRecord = usersOnline;
                updateOnlineRecord = true;
            }

            if (!updateOnlineRecord)
                StatisticsDao.saveStatistics(usersOnline, RoomManager.getInstance().getRoomInstances().size(), Comet.getBuild());
            else
                StatisticsDao.saveStatistics(usersOnline, RoomManager.getInstance().getRoomInstances().size(), Comet.getBuild(), this.onlineRecord);

            this.processSession();

        } catch (Exception e) {
            LOGGER.error("Error during game thread", e);
        }
    }

    private void processSession() {
        final LocalDate date = LocalDate.now();
        final Calendar calendar = Calendar.getInstance();

        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);
        boolean clubReward = false;

        final boolean doubleRewards = CometSettings.onlineRewardDoubleDays.contains(date.getDayOfWeek());
        final boolean updateDaily = hour == 0 && minute == 0;
        final int dailyRespects = 3;
        final int dailyScratches = 3;
        final int dailyRolls = 3;

        if (CometSettings.onlineRewardEnabled || updateDaily) {
            for (ISession client : NetworkManager.getInstance().getSessions().getSessions().values()) {
                try {
                    if (!(client instanceof Session) || client.getPlayer() == null || client.getPlayer().getData() == null) {
                        continue;
                    }

                    if(client.getPlayer().getData().getRank() == 2)
                        clubReward = true;


                    if (updateDaily) {
                        client.getPlayer().getStats().setDailyRespects(dailyRespects);
                        client.getPlayer().getStats().setScratches(dailyScratches);
                        client.getPlayer().getStats().setDailyRolls(dailyRolls);

                        client.send(new UserObjectMessageComposer(((Session) client).getPlayer()));
                    }

                    client.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_2, 1);

                    final boolean needsReward = (Comet.getTime() - client.getPlayer().getLastReward()) >= (60 * CometSettings.onlineRewardInterval);
                    final boolean needsDiamondsReward = (Comet.getTime() - client.getPlayer().getLastDiamondReward()) >= (60 * CometSettings.onlineRewardDiamondsInterval);

                    int creditsAmount = CometSettings.onlineRewardCredits * (doubleRewards ? 2 : 1) * (clubReward ? 2 : 1);
                    int asteroidesAmount = CometSettings.onlineRewardDuckets * (doubleRewards ? 2 : 1) * (clubReward ? 2 : 1);

                    if (needsReward || needsDiamondsReward) {
                        if(needsReward) {
                            if (CometSettings.onlineRewardCredits > 0) {
                                client.getPlayer().getData().increaseCredits(creditsAmount);
                                client.getPlayer().composeCreditBalance();
                            }

                            if (CometSettings.onlineRewardDuckets > 0) {
                                client.getPlayer().getData().increaseActivityPoints(asteroidesAmount);
                                client.send(new UpdateActivityPointsMessageComposer(client.getPlayer().getData().getActivityPoints(), CometSettings.onlineRewardDuckets, 0));
                                client.getPlayer().sendBubble("pixel", "Has recibido " +  creditsAmount + " Créditos y " + asteroidesAmount + " Asteroides por estar conectad" + (client.getPlayer().getData().getGender().equals("F") ? "a" : "o") + ".\n\nBonus VIP: " + (clubReward ? "Sí" : "No") + "\nBonus Especial: " + (doubleRewards ? "Sí" : "No"));
                            }

                            if(!(((PlayerData)client.getPlayer().getData()).viewPoints >= 100)){
                                ((PlayerData)client.getPlayer().getData()).addViewPoint();
                                if(((PlayerData)client.getPlayer().getData()).viewPoints == 100){
                                    int itemId = 886735091;

                                    final String itemExtraData = "0";

                                    final Data<Long> itemIdData = Data.createEmpty();

                                    StorageContext.getCurrentContext().getRoomItemRepository().createItem(client.getPlayer().getId(), itemId, itemExtraData, itemIdData::set);

                                    final PlayerItem playerItem = new InventoryItem(itemIdData.get(), itemId, itemExtraData);

                                    client.getPlayer().getInventory().addItem(playerItem);

                                    client.send(new NotificationMessageComposer("generic", "Felicidades, has conseguido el rare mensual"));
                                    client.send(new UpdateInventoryMessageComposer());

                                    client.send(new UnseenItemsMessageComposer(Sets.newHashSet(playerItem), ItemManager.getInstance()));
                                }
                            }


                            client.getPlayer().setLastReward(Comet.getTime());
                        }

                        if(needsDiamondsReward) {
                            if(clubReward) {
                                if (CometSettings.onlineRewardDiamonds > 0) {
                                    client.getPlayer().getData().increaseVipPoints(client.getPlayer().getData().getRank() == 2 ? CometSettings.onlineRewardDiamonds * 2 : CometSettings.onlineRewardDiamonds);
                                    client.send(new UpdateActivityPointsMessageComposer(client.getPlayer().getData().getVipPoints(), client.getPlayer().getData().getRank() == 2 ? CometSettings.onlineRewardDiamonds * 2 : CometSettings.onlineRewardDiamonds, 5));
                                }
                                client.getPlayer().setLastDiamondReward(Comet.getTime());
                            }
                        }

                        client.getPlayer().getData().save();
                        client.getPlayer().composeCreditBalance();
                    }
                } catch (Exception e) {
                    LOGGER.error("Error while cycling rewards", e);
                }
            }

            if (updateDaily) {
                PlayerDao.dailyPlayerUpdate(dailyRespects, dailyScratches, dailyRolls);
            }
        }
    }

    public void stop() {
        this.active = false;
        this.gameFuture.cancel(false);
    }

    public int getCurrentOnlineRecord() {
        return this.currentOnlineRecord;
    }

    public int getOnlineRecord() {
        return this.onlineRecord;
    }
}
