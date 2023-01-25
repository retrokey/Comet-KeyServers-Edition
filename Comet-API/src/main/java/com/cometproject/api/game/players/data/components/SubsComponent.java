package com.cometproject.api.game.players.data.components;

import com.cometproject.api.networking.messages.IMessageComposer;

public interface SubsComponent {
    void add(int days);
    void delete();

    boolean isValid();
    IMessageComposer deliver();
    IMessageComposer confirm();
    IMessageComposer update();
}
