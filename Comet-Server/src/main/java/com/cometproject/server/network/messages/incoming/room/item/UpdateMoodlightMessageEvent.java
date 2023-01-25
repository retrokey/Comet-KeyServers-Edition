package com.cometproject.server.network.messages.incoming.room.item;

import com.cometproject.server.game.rooms.objects.items.types.wall.MoodlightWallItem;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;
import com.cometproject.server.storage.queries.items.MoodlightDao;


public class UpdateMoodlightMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        Room r = client.getPlayer().getEntity() != null && client.getPlayer().getEntity().getRoom() != null ?
                client.getPlayer().getEntity().getRoom() : null;

        if (r == null) {
            return;
        }

        if (!r.getRights().hasRights(client.getPlayer().getEntity().getPlayerId())) {
            return;
        }

        MoodlightWallItem moodlight = r.getItems().getMoodlight();

        if (moodlight == null) {
            return;
        }

        int preset = msg.readInt();
        boolean bgOnly = msg.readInt() >= 2;
        String color = msg.readString();
        int intensity = msg.readInt();

        if (!MoodlightWallItem.isValidColour(color)) {
            color = "#000000";
        }

        moodlight.getMoodlightData().setEnabled(true);
        moodlight.getMoodlightData().setActivePreset(preset);
        moodlight.getMoodlightData().updatePreset(preset - 1, bgOnly, color, intensity);

        // save the data!
        MoodlightDao.updateMoodlight(moodlight);

        // set the mood!
        moodlight.getItemData().setData(moodlight.generateExtraData());
        moodlight.saveData();
        moodlight.sendUpdate();
    }
}