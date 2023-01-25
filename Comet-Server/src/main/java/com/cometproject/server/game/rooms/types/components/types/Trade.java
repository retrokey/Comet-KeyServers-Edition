package com.cometproject.server.game.rooms.types.components.types;

import com.cometproject.api.game.players.data.components.inventory.PlayerItem;
import com.cometproject.api.game.rooms.entities.RoomEntityStatus;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.composers.catalog.UnseenItemsMessageComposer;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.achievements.BattlePassGlobals;
import com.cometproject.server.game.achievements.types.BattlePassMission;
import com.cometproject.server.game.achievements.types.BattlePassMissionEnums;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.types.components.TradeComponent;
import com.cometproject.server.logging.LogManager;
import com.cometproject.server.logging.entries.AlfaLogEntry;
import com.cometproject.server.logging.entries.TradeLogEntry;
import com.cometproject.server.network.messages.outgoing.notification.AlertMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.trading.*;
import com.cometproject.server.network.messages.outgoing.user.inventory.UpdateInventoryMessageComposer;
import com.cometproject.server.protocol.messages.MessageComposer;
import com.cometproject.server.storage.queries.items.TradeDao;
import com.cometproject.server.storage.queries.player.PlayerDao;
import com.cometproject.server.tasks.CometThreadManager;
import com.cometproject.server.utilities.collections.ConcurrentHashSet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Trade {
    /**
     * The entities which are trading
     */
    private PlayerEntity user1, user2;

    /**
     * The stage the trade is currently at
     */
    private int stage = 1;

    /**
     * The items which the entities are trading
     */
    private Set<PlayerItem> user1Items, user2Items;

    /**
     * Have the entities accepted the trade?
     */
    private boolean user1Accepted = false, user2Accepted = false;

    /**
     * The component instance which stores the trades
     */
    private TradeComponent tradeComponent;

    /**
     * Initialize the trade
     *
     * @param user1 The user who initialized the trade
     * @param user2 The user who is participating in the trade
     */
    public Trade(PlayerEntity user1, PlayerEntity user2) {
        this.user1 = user1;
        this.user2 = user2;

        user1Items = new ConcurrentHashSet<>();
        user2Items = new ConcurrentHashSet<>();

        if (!user1.hasStatus(RoomEntityStatus.TRADE)) {
            user1.addStatus(RoomEntityStatus.TRADE, "");
            user1.markNeedsUpdate();
        }

        if (!user2.getPlayer().getEntity().hasStatus(RoomEntityStatus.TRADE)) {
            user2.addStatus(RoomEntityStatus.TRADE, "");
            user2.markNeedsUpdate();
        }

        user1.getPlayer().getSession().getPlayer().getInventory().send();//send(new InventoryMessageComposer(user1.getPlayer().getInventory()));
        user2.getPlayer().getSession().getPlayer().getInventory().send();//new InventoryMessageComposer(user2.getPlayer().getInventory()));

        sendToUsers(new TradeStartMessageComposer(user1.getPlayer().getId(), user2.getPlayer().getId()));

        BattlePassMission ms = BattlePassGlobals.battlePassMissions.stream().filter(x -> x.type == BattlePassMissionEnums.MissionType.TRADE).findAny().orElse(null);
        if(ms != null){
            if(user1.getPlayer().getData().battlePass != null) user1.getPlayer().getData().battlePass.addExperiencePoint(ms.id);
            if(user2.getPlayer().getData().battlePass != null) user1.getPlayer().getData().battlePass.addExperiencePoint(ms.id);
        }
    }

    /**
     * Cancel the trade
     *
     * @param userId The user which is cancelling the trade
     */
    public void cancel(int userId) {
        this.cancel(userId, true);
    }

    /**
     * Cancel the trade
     *
     * @param userId  The user which is cancelling the trade
     * @param isLeave Is the user leaving the room?
     */
    public void cancel(int userId, boolean isLeave) {
        this.user1Items.clear();
        this.user2Items.clear();

        boolean sendToUser1 = true;
        boolean sendToUser2 = true;

        if (isLeave) {
            if (user1.getPlayer() == null || userId == user1.getPlayer().getId()) {
                sendToUser1 = false;
            } else {
                sendToUser2 = false;
            }
        }

        if (user1 != null && user1.getPlayer() != null && sendToUser1) {
            user1.removeStatus(RoomEntityStatus.TRADE);
            user1.markNeedsUpdate();
        }

        if (user2 != null && user2.getPlayer() != null && sendToUser2) {
            user2.removeStatus(RoomEntityStatus.TRADE);
            user2.markNeedsUpdate();
        }

        this.sendToUsers(new TradeCloseMessageComposer(userId));
        this.tradeComponent.remove(this);
    }

    /**
     * Add an item to the trade
     *
     * @param user The user which is adding an item
     * @param item The chosen item
     */
    public void addItem(int user, PlayerItem item, boolean update) {
        if(this.user1Accepted || this.user2Accepted) {
            return;
        }

        if (user == 1) {
            this.user1Items.add(item);
        } else {
            this.user2Items.add(item);
        }

        if (user1 != null && user1.getPlayer() != null) {
            this.sendToUsers(new TradeAcceptUpdateMessageComposer(user1.getPlayer().getId(), false));
            this.user1Accepted = false;
        }

        if (user2 != null && user2.getPlayer() != null) {
            this.sendToUsers(new TradeAcceptUpdateMessageComposer(user2.getPlayer().getId(), false));
            this.user2Accepted = false;
        }

        if (this.stage == 2)
            this.stage = 1;

        if (update) {
            this.updateWindow();
        }
    }

    public boolean isOffered(PlayerItem item) {
        return this.user1Items.contains(item) || this.user2Items.contains(item);
    }

    /**
     * Get the participant id
     *
     * @param user The user who is trading
     * @return participant ID
     */
    public int getUserNumber(PlayerEntity user) {
        return (user1 == user) ? 1 : 0;
    }

    /**
     * Remove an item from the trade
     *
     * @param user The user which is removing an item
     * @param item The chosen item
     */
    public void removeItem(int user, PlayerItem item) {
        if(this.user1Accepted || this.user2Accepted) {
            return;
        }

        if (user == 1) {
            this.user1Items.remove(item);
        } else {
            this.user2Items.remove(item);
        }

        this.updateWindow();
    }

    /**
     * Accept the trade
     *
     * @param user The user which is accepting the trade
     */
    public void accept(int user) {
        if (user == 1)
            this.user1Accepted = true;
        else
            this.user2Accepted = true;

        this.sendToUsers(new TradeAcceptUpdateMessageComposer(((user == 1) ? user1 : user2).getPlayer().getId(), true));

        if (user1Accepted && user2Accepted) {
            this.stage++;
            this.sendToUsers(new TradeConfirmationMessageComposer());
            this.user1Accepted = false;
            this.user2Accepted = false;
        }
    }

    public void unaccept(int user) {
        if (this.user1Accepted && user2Accepted) {
            this.stage--;
        }

        if (user == 1)
            this.user1Accepted = false;
        else
            this.user2Accepted = false;

        this.sendToUsers(new TradeAcceptUpdateMessageComposer(((user == 1) ? user1 : user2).getPlayer().getId(), false));
    }

    /**
     * Confirm the trade
     *
     * @param user The user which is confirming the trade
     */
    public void confirm(int user) {
        if (stage < 2) {
            return;
        }

        if (user == 1)
            this.user1Accepted = true;
        else
            this.user2Accepted = true;

        sendToUsers(new TradeAcceptUpdateMessageComposer(((user == 1) ? user1 : user2).getPlayer().getId(), true));

        if (user1Accepted && user2Accepted) {


            if(Arrays.asList(user1.getRoom().getData().getTags()).contains("inter_test")){
                proccessInter();
                return;
            }

            complete();

            this.user1Items.clear();
            this.user2Items.clear();

            if (user1.getPlayer().getEntity().hasStatus(RoomEntityStatus.TRADE)) {
                user1.getPlayer().getEntity().removeStatus(RoomEntityStatus.TRADE);
                user1.getPlayer().getEntity().markNeedsUpdate();
            }

            if (user2.getPlayer().getEntity().hasStatus(RoomEntityStatus.TRADE)) {
                user2.getPlayer().getEntity().removeStatus(RoomEntityStatus.TRADE);
                user2.getPlayer().getEntity().markNeedsUpdate();
            }
        }
    }

    /**
     * Complete the trade, provide each of the items within the trade to the users
     */
    public void complete() {
        // confirm the items still exist before making any permanent changes.
        for (PlayerItem item : this.user1Items) {
            if (user1.getPlayer().getInventory().getItem(item.getId()) == null) {
                sendToUsers(new AlertMessageComposer(Locale.get("game.trade.error")));
                return;
            }
        }

        for (PlayerItem item : this.user2Items) {
            if (user2.getPlayer().getInventory().getItem(item.getId()) == null) {
                sendToUsers(new AlertMessageComposer(Locale.get("game.trade.error")));
                return;
            }
        }

        final Map<Long, Integer> itemsToSave = new HashMap<>();
        int sequence = (int) Comet.getTime();

        for (PlayerItem item : this.user1Items) {
            user1.getPlayer().getInventory().removeItem(item);
            user2.getPlayer().getInventory().addItem(item);
            itemsToSave.put(item.getId(), user2.getPlayer().getId());

            PlayerDao.saveTradeLog(user2.getPlayer().getId(), user1.getPlayer().getId(), item.getBaseId(), item.getId(), sequence);
        }

        for (PlayerItem item : this.user2Items) {
            user2.getPlayer().getInventory().removeItem(item);
            user1.getPlayer().getInventory().addItem(item);
            itemsToSave.put(item.getId(), user1.getPlayer().getId());

            PlayerDao.saveTradeLog(user1.getPlayer().getId(), user2.getPlayer().getId(), item.getBaseId(), item.getId(), sequence);
        }

        CometThreadManager.getInstance().executeOnce(() -> {
            TradeDao.updateTradeItems(itemsToSave);
            itemsToSave.clear();
        });

        user1.getPlayer().getSession().send(new UnseenItemsMessageComposer(user2Items, ItemManager.getInstance()));
        user2.getPlayer().getSession().send(new UnseenItemsMessageComposer(user1Items, ItemManager.getInstance()));

        sendToUsers(new UpdateInventoryMessageComposer());
        sendToUsers(new TradeCompletedMessageComposer());

        this.tradeComponent.remove(this);
    }

    /**
     * Remove from inventory and cache.
     */
    private void proccessInter(){
        user1.setUser1Items(this.user1Items);
        user1.setUser2Items(this.user2Items);
        user2.setUser1Items(this.user2Items);
        user2.setUser2Items(this.user1Items);

        for (PlayerItem item : this.user1Items) {
            if (user1.getPlayer().getInventory().getItem(item.getId()) == null) {
                sendToUsers(new AlertMessageComposer(Locale.get("game.trade.error")));
                return;
            }
        }

        for (PlayerItem item : this.user2Items) {
            if (user2.getPlayer().getInventory().getItem(item.getId()) == null) {
                sendToUsers(new AlertMessageComposer(Locale.get("game.trade.error")));
                return;
            }
        }

        for (PlayerItem item : this.user1Items) {
            user1.getPlayer().getInventory().removeItem(item);
        }

        for (PlayerItem item : this.user2Items) {
            user2.getPlayer().getInventory().removeItem(item);
        }
    }

    /**
     * Send the packet which updates the trade window
     */
    public void updateWindow() {
        this.sendToUsers(new TradeUpdateMessageComposer(
                this.user1.getPlayerId(),
                this.user2.getPlayerId(),
                this.user1Items,
                this.user2Items
        ));
    }

    /**
     * Send a packet to each player participating in the trade
     *
     * @param msg The packet
     */
    public void sendToUsers(MessageComposer msg) {
        if (user1 != null && user1.getPlayer() != null && user1.getPlayer().getSession() != null) {
            user1.getPlayer().getSession().send(msg);
        }

        if (user2 != null && user2.getPlayer() != null && user2.getPlayer().getSession() != null) {
            user2.getPlayer().getSession().send(msg);
        }
    }

    /**
     * Get the user 1 participating in the trade
     *
     * @return user 1
     */
    public PlayerEntity getUser1() {
        return this.user1;
    }

    /**
     * Get the user 2 participating in the trade
     *
     * @return user 2
     */
    public PlayerEntity getUser2() {
        return this.user2;
    }

    /**
     * The component instance which stores the trades
     *
     * @return The instance
     */
    public TradeComponent getTradeComponent() {
        return tradeComponent;
    }

    /**
     * Set the trade component instance
     *
     * @param tradeComponent The trade instance
     */
    public void setTradeComponent(TradeComponent tradeComponent) {
        this.tradeComponent = tradeComponent;
    }
}
