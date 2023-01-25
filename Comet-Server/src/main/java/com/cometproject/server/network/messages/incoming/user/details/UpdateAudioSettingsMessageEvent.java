package com.cometproject.server.network.messages.incoming.user.details;

import com.cometproject.api.utilities.JsonUtil;
import com.cometproject.server.game.players.components.types.settings.VolumeData;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;
import com.cometproject.server.storage.queries.player.PlayerDao;


public class UpdateAudioSettingsMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        if (client.getPlayer() == null) {
            return;
        }

        int systemVolume = msg.readInt();
        int furniVolume = msg.readInt();
        int traxVolume = msg.readInt();

        client.getPlayer().sendBubble("", "System: " + systemVolume + "\nFurni: " + furniVolume + "\nTrax: " + traxVolume);

        if (client.getPlayer().getSettings().getVolumes().getSystemVolume() == systemVolume
                && client.getPlayer().getSettings().getVolumes().getFurniVolume() == furniVolume
                && client.getPlayer().getSettings().getVolumes().getTraxVolume() == traxVolume) {
            return;
        }

        PlayerDao.saveVolume(JsonUtil.getInstance().toJson(new VolumeData(systemVolume, furniVolume, traxVolume)), client.getPlayer().getId());
    }
}
