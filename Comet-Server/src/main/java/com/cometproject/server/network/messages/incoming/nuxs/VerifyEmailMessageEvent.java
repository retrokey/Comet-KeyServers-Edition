package com.cometproject.server.network.messages.incoming.nuxs;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.moderation.ModerationManager;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.messenger.InstantChatMessageComposer;
import com.cometproject.server.network.messages.outgoing.moderation.ModToolMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.nuxs.EmailVerificationWindowMessageComposer;
import com.cometproject.server.network.messages.outgoing.nuxs.SMSVerificationCompleteMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class VerifyEmailMessageEvent implements Event {

    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        if (client == null || client.getPlayer() == null || client.getPlayer().getData() == null || !client.getPlayer().getPermissions().getRank().modTool())
            return;

        String email = msg.readString();

        if(email.toLowerCase().contains(client.getPlayer().getSettings().getPersonalPin().toLowerCase())) {
            client.getPlayer().sendBubble("pincode", Locale.getOrDefault("pin.code.success", "Acabas de introducir tu pin correctamente, ¡disfruta de tu sesión!"));
            client.getPlayer().getSettings().setPinSucces();
            client.sendQueue(new ModToolMessageComposer());
            client.sendQueue(new SMSVerificationCompleteMessageComposer(2,2));
            return;
        }

        if(client.getPlayer().getSettings().getPinTries() >= 2) {
            for (Session player : ModerationManager.getInstance().getLogChatUsers()) {
                player.send(new InstantChatMessageComposer(client.getPlayer().getData().getUsername() + " ha fallado tres veces seguidas su pin y ha sido desconectado.", Integer.MAX_VALUE - 1));
            }

            client.disconnect();
        }

        else {
            client.getPlayer().getSettings().incrementPinTries();
            client.getPlayer().sendBubble("pincode", Locale.getOrDefault("pin.code.error", "Vaya... parece que este no es tu pin."));
            client.sendQueue(new EmailVerificationWindowMessageComposer(1,1));
        }
    }
}
