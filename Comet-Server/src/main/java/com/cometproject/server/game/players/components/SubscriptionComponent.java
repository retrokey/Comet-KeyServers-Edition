package com.cometproject.server.game.players.components;

import com.cometproject.api.game.players.IPlayer;
import com.cometproject.api.game.players.data.components.SubsComponent;
import com.cometproject.api.networking.messages.IMessageComposer;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.game.players.types.PlayerComponent;
import com.cometproject.server.network.messages.outgoing.user.club.ClubStatusMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.club.SubscriptionCenterInfoMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.permissions.FuserightsMessageComposer;
import com.cometproject.server.storage.queries.player.SubscriptionDao;


public class SubscriptionComponent extends PlayerComponent implements SubsComponent {
    private boolean hasSub;
    private int expire;
    private int start;
    private int presents;

    public SubscriptionComponent(IPlayer player) {
        super(player);
        this.load();
    }

    public void load() {
        this.expire = this.getExpireFromDao();
        this.start = this.getStartFromDao();
        this.presents = this.getPresentsFromDao();
        this.hasSub = this.isValid();
    }

    @Override
    public void add(int days) {
        if (this.hasSub) {
            SubscriptionDao.renewSubscription(this.player.getId(), this.getExpire() + 86400 * days);
        } else {
            SubscriptionDao.addSubscription(this.player.getId(), (int)Comet.getTime() + 86400 * days);
        }
        this.load();
    }

    @Override
    public void delete() {
        this.hasSub = false;
    }

    @Override
    public void dispose() {

    }

    @Override
    public IMessageComposer deliver(){
        return new SubscriptionCenterInfoMessageComposer(this);
    }

    @Override
    public IMessageComposer confirm(){
        return new ClubStatusMessageComposer(this);
    }

    @Override
    public IMessageComposer update(){
        return new FuserightsMessageComposer(this.hasSub, this.getPlayer().getData().getRank());
    }

    @Override
    public boolean isValid() {
        return (long)this.getExpire() >= Comet.getTime();
    }

    public boolean exists() {
        return this.hasSub;
    }

    public int getExpire() {
        return this.expire;
    }

    public int getStart() {
        return this.start;
    }

    public int getPresents() {
        return this.presents;
    }

    public void decrementPresents(int playerId) {
        --this.presents;
        SubscriptionDao.decrementPresents(playerId, this.presents);
    }

    public int getTimeLeft() {
        return this.getExpire() - (int)Comet.getTime();
    }

    public int getDaysLeft() {
        return this.getTimeLeft() / 86400;
    }

    public int getYearsLeft() {
        return (int)Math.floor(this.getDaysLeft() / 365);
    }

    public int getMinutesLeft() {
        return (int)Math.ceil((double)this.getTimeLeft() / 60.0);
    }

    public int getExpireFromDao() {
        return SubscriptionDao.getExpireTime(this.player.getId());
    }

    public int getStartFromDao() {
        return SubscriptionDao.getStartTime(this.player.getId());
    }

    public int getPresentsFromDao() {
        return SubscriptionDao.getPresents(this.player.getId());
    }

    @Override
    public IPlayer getPlayer() {
        return this.player;
    }
}
