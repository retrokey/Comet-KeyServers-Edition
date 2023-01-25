package com.cometproject.server.network.messages.incoming.handshake;

import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.handshake.SecretKeyMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.codec.EncryptionDecoder;
import com.cometproject.server.protocol.codec.EncryptionEncoder;
import com.cometproject.server.protocol.crypto.exceptions.HabboCryptoException;
import com.cometproject.server.protocol.messages.MessageEvent;

public class GenerateSecretKeyMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws HabboCryptoException {
        // encryption is disabled, so no idea what this client is trying to do
        if(client.getEncryption() == null) {
            client.disconnect();
            return;
        }

        byte[] sharedKey = client.getEncryption().getDiffie().getSharedKey(msg.readString());
        client.setHandshakeFinished(true);

        client.send(new SecretKeyMessageComposer(client.getEncryption().getDiffie().getPublicKey()));

        client.getChannel().pipeline().addFirst("encryptionDecoder", new EncryptionDecoder(sharedKey));
        client.getChannel().pipeline().addFirst("encryptionEncoder", new EncryptionEncoder(sharedKey));
    }
}
