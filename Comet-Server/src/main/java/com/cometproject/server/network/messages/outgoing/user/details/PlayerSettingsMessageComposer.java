package com.cometproject.server.network.messages.outgoing.user.details;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.players.types.PlayerSettings;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;


public class PlayerSettingsMessageComposer extends MessageComposer {
    private final PlayerSettings playerSettings;
    private int roomToolStatus;

    public PlayerSettingsMessageComposer(final PlayerSettings playerSettings, int status) {
        this.playerSettings = playerSettings;
        this.roomToolStatus = status;
    }

    @Override
    public short getId() {
        return Composers.SoundSettingsMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(this.playerSettings.getVolumes().getSystemVolume());
        msg.writeInt(this.playerSettings.getVolumes().getFurniVolume());
        msg.writeInt(this.playerSettings.getVolumes().getTraxVolume());
        msg.writeBoolean(this.playerSettings.isUseOldChat()); // old chat enabled?
        msg.writeBoolean(this.playerSettings.ignoreEvents()); // ignore room invites
        msg.writeBoolean(this.playerSettings.roomCameraFollow()); //disable_room_camera_follow_checkbox
        msg.writeInt(this.roomToolStatus);
        msg.writeInt(this.playerSettings.getBubbleId());
    }
}
