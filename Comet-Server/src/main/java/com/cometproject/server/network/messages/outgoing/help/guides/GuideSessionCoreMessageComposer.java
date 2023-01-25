package com.cometproject.server.network.messages.outgoing.help.guides;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.guides.types.HelpRequest;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class GuideSessionCoreMessageComposer extends MessageComposer {
    private final HelpRequest request;

    public GuideSessionCoreMessageComposer(HelpRequest r) {
        this.request = r;
    }

    @Override
    public short getId() {
        return Composers.GuideSessionStartedMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(this.request.getPlayerSession().getPlayer().getId());
        msg.writeString(this.request.getPlayerSession().getPlayer().getData().getUsername());
        msg.writeString(this.request.getPlayerSession().getPlayer().getData().getFigure());

        msg.writeInt(this.request.getGuideSession().getPlayer().getId());
        msg.writeString(this.request.getGuideSession().getPlayer().getData().getUsername());
        msg.writeString(this.request.getGuideSession().getPlayer().getData().getFigure());
    }
}