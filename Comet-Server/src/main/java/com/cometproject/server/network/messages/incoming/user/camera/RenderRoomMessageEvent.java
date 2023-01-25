package com.cometproject.server.network.messages.incoming.user.camera;

import com.cometproject.api.config.CometSettings;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.composers.camera.PhotoPreviewMessageComposer;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class RenderRoomMessageEvent implements Event {
    private static final byte[] signature = new byte[]{-119, 80, 78, 71, 13, 10, 26, 10};

    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        int length = msg.readInt();
        byte[] payload = msg.readBytes(length);
        int timestamp = (int)Comet.getTime();
        System.out.println("TAMA\u00d1O : " + length);
        String URL2 = client.getPlayer().getData().getId() + "_" + timestamp + ".png";
        String URL_small = client.getPlayer().getData().getId() + "_" + timestamp + "_small.png";
        String base = CometSettings.cameraPhotoUrl.replace("%photoId%", "");
        client.getPlayer().setLastPhoto(URL2);
        if (RenderRoomMessageEvent.isPngFile(payload)) {
            try {
                ByteBuf test = Unpooled.copiedBuffer((byte[])payload);
                BufferedImage image = ImageIO.read((InputStream)new ByteBufInputStream(test));
                ImageIO.write((RenderedImage)image, "png", new File(CometSettings.cameraUploadUrl + URL2));
                ImageIO.write((RenderedImage)image, "png", new File(CometSettings.cameraUploadUrl + URL_small));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (IllegalArgumentException illegalArgumentException) {
                // empty catch block
            }
            client.send(new PhotoPreviewMessageComposer(URL2));
        }
    }

    public static boolean isPngFile(byte[] file) {
        return Arrays.equals(Arrays.copyOfRange(file, 0, 8), signature);
    }
}
