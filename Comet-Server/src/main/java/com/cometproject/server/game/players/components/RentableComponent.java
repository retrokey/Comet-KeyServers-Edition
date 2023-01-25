package com.cometproject.server.game.players.components;

import com.cometproject.server.game.players.types.Player;
import com.cometproject.server.game.players.types.PlayerComponent;
import com.cometproject.server.storage.queries.player.PlayerDao;

public class RentableComponent extends PlayerComponent {
    private int rentedId;
    private int expiracy;
    private int cost;

    public RentableComponent(Player p){
        super(p);

        flush();
    }

    public void flush(){
        this.rentedId = PlayerDao.getRentableId(player.getData().getId());
    }

    public boolean hasRent() {
        return rentedId > 0;
    }

    public void setRentedId(int i){
        this.rentedId = i;
    }

    public int getRentedId() {
        return rentedId;
    }
}
