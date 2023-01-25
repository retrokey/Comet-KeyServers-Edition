/*package com.cometproject.server.game.rooms.types.components;

import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.components.games.rps.RPSGame;

import java.util.ArrayList;
import java.util.List;

public class RPSComponent {
    private final Room room;
    private List<RPSGame> RPSGames;

    public RPSComponent(Room room) {
        this.room = room;
        this.RPSGames = new ArrayList<>();
    }

    public void add(RPSGame r) {
        r.setRPSComponent(this);

        this.RPSGames.add(r);
    }

    public RPSGame get(PlayerEntity client) {
        for (RPSGame r : this.getGames()) {
            if (r.getUser1() == client || r.getUser2() == client)
                return r;
        }

        return null;
    }

    public void remove(RPSGame r) {
        this.RPSGames.remove(r);
    }

    public synchronized List<RPSGame> getGames() {
        return this.RPSGames;
    }

    public Room getRoom() {
        return room;
    }
}*/