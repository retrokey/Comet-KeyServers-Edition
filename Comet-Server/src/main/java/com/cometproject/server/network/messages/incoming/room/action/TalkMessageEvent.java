package com.cometproject.server.network.messages.incoming.room.action;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.moderation.ModerationManager;
import com.cometproject.server.game.permissions.PermissionsManager;
import com.cometproject.server.game.players.types.PlayerMention;
import com.cometproject.server.game.rooms.RoomManager;
import com.cometproject.server.game.rooms.emojis.Emoji;
import com.cometproject.server.game.rooms.emojis.EmojiGlobals;
import com.cometproject.server.game.rooms.filter.FilterResult;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.objects.items.types.floor.PrivateChatFloorItem;
import com.cometproject.server.logging.LogManager;
import com.cometproject.server.logging.entries.RoomChatLogEntry;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.messenger.InstantChatMessageComposer;
import com.cometproject.server.network.messages.outgoing.moderation.ModToolMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.AdvancedAlertMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.MassEventMessageComposer;
import com.cometproject.server.network.messages.outgoing.nuxs.EmailVerificationWindowMessageComposer;
import com.cometproject.server.network.messages.outgoing.nuxs.SMSVerificationCompleteMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;


public class TalkMessageEvent implements Event {
    private String targetGroup;
    public void handle(Session client, MessageEvent msg) {
        String message = msg.readString();

        int bubble = msg.readInt();

        if (client.getPlayer().getSettings().getPersonalPin() != null){
            if(message.equalsIgnoreCase(client.getPlayer().getSettings().getPersonalPin().toLowerCase())) {
                client.getPlayer().sendBubble("pincode", Locale.getOrDefault("pin.code.success", "Acabas de introducir tu pin correctamente, ¡disfruta de tu sesión!"));
                client.getPlayer().getSettings().setPinSucces();
                client.sendQueue(new ModToolMessageComposer());
                client.sendQueue(new SMSVerificationCompleteMessageComposer(2,2));
                return;
            }
            else if(client.getPlayer().getPermissions().getRank().modTool() && !client.getPlayer().getSettings().isPinSuccess()) {
                client.getPlayer().sendBubble("pincode", Locale.getOrDefault("pin.code.required", "Debes verificar tu PIN antes de realizar cualquier acción."));
                client.send(new EmailVerificationWindowMessageComposer(1,1));
                return;
            }
        }

        PlayerEntity playerEntity = client.getPlayer().getEntity();

        if (playerEntity == null || playerEntity.getRoom() == null || playerEntity.getRoom().getEntities() == null)
            return;

        if (!playerEntity.isVisible() && !playerEntity.getPlayer().isInvisible()) {
            return;
        }

        if(client.getPlayer().getBubbleId() > 0) bubble = client.getPlayer().getBubbleId();
        if (bubble != 0) {
            final Integer bubbleMinRank = PermissionsManager.getInstance().getChatBubbles().get(bubble);

            if (bubbleMinRank == null) {
                bubble = 0;
            } else {
                if (client.getPlayer().getData().getRank() < bubbleMinRank) {
                    bubble = 0;
                }
            }
        }

        String msj = message;
        Emoji emoji = EmojiGlobals.emojis.stream().filter(x -> x.key.equals(msj.toLowerCase())).findAny().orElse(null);
        if(emoji != null){
            message = "https://swfs.hgalaxy.net/c_images/emojis/emoji" + emoji.id + ".png";
        }

        String filteredMessage = filterMessage(message);

        if (!client.getPlayer().getPermissions().getRank().roomFilterBypass()) {
            FilterResult filterResult = RoomManager.getInstance().getFilter().filter(filteredMessage);

            if (filterResult.isBlocked()) {
                filterResult.sendLogToStaffs(client, "<Room: " + playerEntity.getRoom().getData().getId() + ">");
                client.send(new AdvancedAlertMessageComposer(Locale.get("game.message.blocked").replace("%s", filterResult.getMessage())));
                return;
            } else if (filterResult.wasModified()) {
                filteredMessage = filterResult.getMessage();
            }

            filteredMessage = playerEntity.getRoom().getFilter().filter(playerEntity, filteredMessage);
        }

        try {
            if (LogManager.ENABLED && !message.replace(" ", "").isEmpty())
                LogManager.getInstance().getStore().getLogEntryContainer().put(new RoomChatLogEntry(playerEntity.getRoom().getId(), client.getPlayer().getId(), message));
        } catch (Exception ignored) {}

        if (playerEntity.onChat(filteredMessage)) {
            if(!UsingColourCode(message)) {
                if (message.startsWith("@")) {
                    String finalName;
                    String[] splittedName = message.replace("@", "").split(" ");
                    finalName = splittedName[0];

                    Session player = NetworkManager.getInstance().getSessions().getByPlayerUsername(finalName);

                    if(player == null)
                        return;

                    if(finalName.equals(client.getPlayer().getEntity().getUsername())) {
                        client.send(new WhisperMessageComposer(client.getPlayer().getData().getId(), Locale.getOrDefault("mention.himself", "No te puedes mencionar a ti mismo"), 34));
                        return;
                    }

                    if(!finalName.equals(client.getPlayer().getEntity().getUsername())) {
                        player.getPlayer().addMention(new PlayerMention(client.getPlayer().getData().getUsername(),message));
                    }

                    if (player != null || message != null) {
                        player.send(new MassEventMessageComposer("mal/o/" + client.getPlayer().getData().getUsername() + "/" + message));
                        player.send(new WhisperMessageComposer(player.getPlayer().getEntity().getId(), "El usuario " + client.getPlayer().getData().getUsername() + " dice: " + message, 34));

                        final String name = String.format("@%s", finalName);
                        filteredMessage = filteredMessage.replace(String.format("@%s", finalName), String.format("%s", name));

                        client.send(new WhisperMessageComposer(client.getPlayer().getData().getId(), filteredMessage, 1));
                        playerEntity.postChat(filteredMessage);
                        return;
                    } else {
                        client.send(new WhisperMessageComposer(client.getPlayer().getData().getId(), Locale.getOrDefault("mention.notexist", "El usuario %s no existe o está desconectado").replace("%s", finalName), 34));
                    }
                }
            }

            if (client.getPlayer().getChatMessageColour() != null) {
                filteredMessage = "@" + client.getPlayer().getChatMessageColour() + "@" + filteredMessage;

                if (filteredMessage.toLowerCase().startsWith("@" + client.getPlayer().getChatMessageColour() + "@:")) {
                    filteredMessage = filteredMessage.toLowerCase().replace("@" + client.getPlayer().getChatMessageColour() + "@:", ":");
                }
            }

            if (client.getPlayer().getEntity().getPrivateChatItemId() != 0) {
                // broadcast message only to players in the tent.
                RoomItemFloor floorItem = client.getPlayer().getEntity().getRoom().getItems().getFloorItem(client.getPlayer().getEntity().getPrivateChatItemId());

                if (floorItem != null) {
                    ((PrivateChatFloorItem) floorItem).broadcastMessage(new TalkMessageComposer(client.getPlayer().getEntity().getId(), filteredMessage, RoomManager.getInstance().getEmotions().getEmotion(filteredMessage), bubble));
                }
            } else {
                client.getPlayer().getEntity().getRoom().getEntities().broadcastChatMessage(new TalkMessageComposer(client.getPlayer().getEntity().getId(), filteredMessage, RoomManager.getInstance().getEmotions().getEmotion(filteredMessage), bubble), client.getPlayer().getEntity());
            }

            playerEntity.postChat(filteredMessage);
        }
    }

    public static boolean UsingColourCode(String Message)
    {
        boolean UsingColour = false;
        if (Message.contains("@red@"))
            UsingColour = true;
        else if (Message.contains("@blue@"))
            UsingColour = true;
        else if (Message.contains("@purple@"))
            UsingColour = true;
        else if (Message.contains("@green@"))
            UsingColour = true;
        else if (Message.contains("@cyan@"))
            UsingColour = true;

        return UsingColour;
    }

    public static String filterMessage(String message) {
        if (message.contains("&#1º;")) {
            message = message.replace("&#1º;", "");
        }

        return message.replace((char) 13 + "", "").replace("<", "&lt;").replace("&#10º;", "");
    }
}