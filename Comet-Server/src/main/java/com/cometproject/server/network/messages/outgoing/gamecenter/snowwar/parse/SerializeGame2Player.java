package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.network.sessions.Session;

public class SerializeGame2Player {
    public static void parse(IComposer msg, Session cn) {
        if(cn == null || cn.getPlayer() == null || cn.getPlayer().getData() == null || cn.getPlayer().getStats() == null || cn.snowWarPlayerData == null){
            msg.writeInt(0);
            msg.writeString("Custom");
            msg.writeString("sh-735-68.hd-3721-1.hr-515-33.ch-635-70.lg-716-66-62");
            msg.writeString("M");
            msg.writeInt(1);
            msg.writeInt(1);
            msg.writeInt(0);
            msg.writeInt(0);
        } else {
            msg.writeInt(cn.getPlayer().getData().getId());
            msg.writeString(cn.getPlayer().getData().getUsername());
            msg.writeString(cn.getPlayer().getData().getFigure());
            msg.writeString(cn.getPlayer().getData().getGender().toUpperCase());
            msg.writeInt(cn.snowWarPlayerData.humanObject.team);
            msg.writeInt(cn.getPlayer().getStats().getLevel());
            msg.writeInt(cn.snowWarPlayerData.score);
            msg.writeInt(cn.snowWarPlayerData.PointsNeed);
        }
    }
}