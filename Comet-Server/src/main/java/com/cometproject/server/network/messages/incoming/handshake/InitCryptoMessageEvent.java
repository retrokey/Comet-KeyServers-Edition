package com.cometproject.server.network.messages.incoming.handshake;

import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.handshake.InitCryptoMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.crypto.exceptions.HabboCryptoException;
import com.cometproject.server.protocol.messages.MessageEvent;


public class InitCryptoMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws HabboCryptoException {
        // encryption is disabled, so no idea what this client is trying to do
        if(client.getEncryption() == null) {
            client.disconnect();
            return;
        }

        client.send(new InitCryptoMessageComposer(
                client.getEncryption().getDiffie().getSignedPrime(),
                client.getEncryption().getDiffie().getSignedGenerator()));
    }
}