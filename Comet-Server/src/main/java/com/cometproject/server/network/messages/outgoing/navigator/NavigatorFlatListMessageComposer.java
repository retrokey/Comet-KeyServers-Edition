package com.cometproject.server.network.messages.outgoing.navigator;

import com.cometproject.api.game.rooms.IRoomData;
import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.rooms.RoomManager;
import com.cometproject.server.game.rooms.types.RoomWriter;
import com.cometproject.server.protocol.messages.MessageComposer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class NavigatorFlatListMessageComposer extends MessageComposer {
    private final int mode;
    private final String query;
    private final Collection<IRoomData> activeRooms;
    private final boolean limit;
    private final boolean order;

    public NavigatorFlatListMessageComposer(final int mode, final String query, final Collection<IRoomData> activeRooms, final boolean limit, final boolean order) {
        this.mode = mode;
        this.query = query;
        this.activeRooms = activeRooms;
        this.limit = limit;
        this.order = order;
    }

    public NavigatorFlatListMessageComposer(int mode, String query, Collection<IRoomData> activeRooms) {
        this(mode, query, activeRooms, true, true);
    }

    @Override
    public short getId() {
        return 0;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(mode);
        msg.writeString(query);
        msg.writeInt(limit ? (activeRooms.size() > 50 ? 50 : activeRooms.size()) : activeRooms.size());

        if (order) {
            try {
                Collections.sort((List<IRoomData>) activeRooms, (o1, o2) -> {
                    boolean is1Active = RoomManager.getInstance().isActive(o1.getId());
                    boolean is2Active = RoomManager.getInstance().isActive(o2.getId());

                    return ((!is2Active ? 0 : RoomManager.getInstance().get(o2.getId()).getEntities().playerCount()) -
                            (!is1Active ? 0 : RoomManager.getInstance().get(o1.getId()).getEntities().playerCount()));
                });
            } catch (Exception ignored) {

            }
        }

        List<IRoomData> topRooms = new ArrayList<>();

        for (IRoomData room : activeRooms) {
            if (topRooms.size() < 50 || !limit)
                topRooms.add(room);
        }

        for (IRoomData room : topRooms) {
            RoomWriter.write(room, msg);
        }

        msg.writeInt(0);
        msg.writeInt(0);
        msg.writeBoolean(false);

        // Clear the top rooms
        topRooms.clear();
    }

    @Override
    public void dispose() {
        this.activeRooms.clear();
    }
}
