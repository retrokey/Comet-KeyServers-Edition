package com.cometproject.server.network.messages.incoming.room.bots;

import com.cometproject.api.game.bots.BotMode;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.rooms.RoomManager;
import com.cometproject.server.game.rooms.filter.FilterResult;
import com.cometproject.server.game.rooms.objects.entities.types.BotEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.notification.AdvancedAlertMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.AvatarsMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.DanceMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.UpdateInfoMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;
import com.cometproject.server.utilities.RandomUtil;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;


public class ModifyBotMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) {
        if(client.getPlayer().antiSpam(getClass().getName(), 0.4))
            return;

        PlayerEntity entity = client.getPlayer().getEntity();

        if (entity == null) {
            return;
        }

        Room room = client.getPlayer().getEntity().getRoom();

        if (room == null || (!room.getRights().hasRights(client.getPlayer().getId()) && !client.getPlayer().getPermissions().getRank().roomFullControl())) {
            return;
        }

        int botId = msg.readInt();
        int action = msg.readInt();
        String data = msg.readString();

        BotEntity botEntity = room.getEntities().getEntityByBotId(botId);

        if (botEntity == null || botEntity.getData() == null) {
            return;
        }

        switch (action) {
            case 1:
                String figure = entity.getFigure();
                String gender = entity.getGender();

                botEntity.getData().setFigure(figure);
                botEntity.getData().setGender(gender);

                room.getEntities().broadcastMessage(new UpdateInfoMessageComposer(botEntity));
                break;

            case 2:
                String[] data1 = data.split(";");

                List<String> messages = Arrays.asList(data1[0].split("\r"));

                String automaticChat = data1[2];
                String speakingInterval = data1[4];

                if (speakingInterval.isEmpty() || !StringUtils.isNumeric(speakingInterval) || Integer.parseInt(speakingInterval) < 7) {
                    speakingInterval = "7";
                }

                for (String message : messages) {
                    FilterResult filterResult = RoomManager.getInstance().getFilter().filter(message);

                    if (filterResult.isBlocked()) {
                        filterResult.sendLogToStaffs(client, "ModifyBot");
                        client.send(new AdvancedAlertMessageComposer(Locale.get("game.message.blocked").replace("%s", filterResult.getMessage())));
                        return;
                    }
                }

                botEntity.getData().setMessages((String[]) messages.toArray());
                botEntity.getData().setChatDelay(Integer.parseInt(speakingInterval));
                botEntity.getData().setAutomaticChat(Boolean.parseBoolean(automaticChat));
                break;

            case 3:
                // Relax
                if(botEntity.isWalking())
                    botEntity.cancelWalk();
                switch (botEntity.getData().getMode()) {
                    case DEFAULT:
                        botEntity.getData().setMode(BotMode.RELAXED);
                        break;

                    case RELAXED:
                        botEntity.getData().setMode(BotMode.DEFAULT);
                        break;
                }

                client.getPlayer().sendBubble("", "Walking: " + botEntity.getData().getMode());
                botEntity.getData().save();
                break;

            case 4:
                if (botEntity.getDanceId() != 0) {
                    botEntity.setDanceId(0);
                } else {
                    // Dance
                    int danceId = RandomUtil.getRandomInt(1, 4);

                    botEntity.setDanceId(danceId);
                    room.getEntities().broadcastMessage(new DanceMessageComposer(botEntity.getId(), danceId));
                }
                break;

            case 5:
                // Change name
                final String botName = room.getBots().getAvailableName(data);

                FilterResult filterResult = RoomManager.getInstance().getFilter().filter(botName);

                if (filterResult.isBlocked()) {
                    filterResult.sendLogToStaffs(client, "ModifyBot");
                    client.send(new AdvancedAlertMessageComposer(Locale.get("game.message.blocked").replace("%s", filterResult.getMessage())));
                    return;
                }

                room.getBots().changeBotName(botEntity.getUsername(), botName);

                botEntity.getData().setUsername(botName);

                room.getEntities().broadcastMessage(new AvatarsMessageComposer(botEntity));
                break;

            case 9:
                // Change motto
                final String botMotto = room.getBots().getAvailableName(data);

                FilterResult filteredResult = RoomManager.getInstance().getFilter().filter(botMotto);

                if (filteredResult.isBlocked()) {
                    filteredResult.sendLogToStaffs(client, "ModifyBot");
                    client.send(new AdvancedAlertMessageComposer(Locale.get("game.message.blocked").replace("%s", filteredResult.getMessage())));
                    return;
                }

                room.getBots().changeBotMotto(botEntity, botMotto);

                room.getEntities().broadcastMessage(new AvatarsMessageComposer(botEntity));
                break;
        }

        botEntity.getData().save();
    }
}
