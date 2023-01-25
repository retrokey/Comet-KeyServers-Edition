package com.cometproject.server.game.rooms.objects.entities.types.ai.bots;

import com.cometproject.api.game.bots.BotMode;
import com.cometproject.api.game.rooms.entities.RoomEntityStatus;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.BotEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.entities.types.ai.AbstractBotAI;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.messages.outgoing.room.avatar.DanceMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.utilities.RandomUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MinionAI extends AbstractBotAI {
    private final static Map<String, Consumer<BotEntity>> speechCommands = new HashMap<String, Consumer<BotEntity>>() {{
        put("minions leave", (entity) -> entity.leaveRoom(false, false, false));

        put("minions dance", (entity) -> {
            int danceId = RandomUtil.getRandomInt(1, 4);

            entity.setDanceId(danceId);
            entity.getRoom().getEntities().broadcastMessage(new DanceMessageComposer(entity.getId(), danceId));
        });

        put("minions stop dancing", (entity) -> {
            entity.setDanceId(0);
            entity.getRoom().getEntities().broadcastMessage(new DanceMessageComposer(entity.getId(), 0));
        });

        put("minions sit", (entity) -> {
            entity.addStatus(RoomEntityStatus.SIT, "0.5");
            entity.markNeedsUpdate();

            entity.getData().setMode(BotMode.RELAXED);
        });

        put("minions relax", (entity) -> {
            entity.getData().setMode(BotMode.RELAXED);
        });

        put("minions stand", (entity) -> {
            entity.removeStatus(RoomEntityStatus.SIT);
            entity.markNeedsUpdate();

            entity.getData().setMode(BotMode.DEFAULT);
        });

        put("minions follow", (entity) -> {
            PlayerEntity playerEntity = entity.getRoom().getEntities().getEntityByPlayerId(entity.getData().getOwnerId());

            if (playerEntity != null) {
                playerEntity.getFollowingEntities().add(entity);
            }
        });
    }};

    public MinionAI(RoomEntity entity) {
        super(entity);
    }

    @Override
    public boolean onTalk(PlayerEntity entity, String message) {
        if (message.startsWith(":")) return false;

        if (entity.getPlayerId() != ((BotEntity) this.getEntity()).getData().getOwnerId()) {
            return false;
        }

        if (speechCommands.containsKey(message.toLowerCase())) {
            speechCommands.get(message.toLowerCase()).accept(((BotEntity) this.getEntity()));
        } else {
            this.getEntity().getRoom().getEntities().broadcastMessage(new TalkMessageComposer(this.getEntity().getId(), message, ChatEmotion.NONE, 2));
        }

        return false;
    }

    @Override
    public boolean onPlayerLeave(PlayerEntity playerEntity) {
        if (playerEntity.getPlayerId() == ((BotEntity) this.getEntity()).getData().getOwnerId()) {
            this.getEntity().leaveRoom(false, false, false);
        }

        return false;
    }
}
