package com.cometproject.server.game.rooms.objects.items.types.floor.wired.actions.custom;

import com.cometproject.api.game.quests.IQuest;
import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.quests.QuestManager;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.effects.PlayerEffect;
import com.cometproject.server.game.rooms.objects.entities.types.PetEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.base.WiredActionItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.events.WiredItemEvent;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.outgoing.room.avatar.DanceMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import org.apache.commons.lang.StringUtils;


public class WiredCustomStartQuest extends WiredActionItem {

    public WiredCustomStartQuest(RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    @Override
    public boolean requiresPlayer() {
        return true;
    }

    @Override
    public int getInterface() {
        return 7;
    }

    @Override
    public void onEventComplete(WiredItemEvent event) {
        if (!(event.entity instanceof PlayerEntity)) {
            return;
        }

        PlayerEntity playerEntity = ((PlayerEntity) event.entity);

        if (playerEntity.getPlayer() == null || playerEntity.getPlayer().getSession() == null) {
            return;
        }

        if (this.getWiredData() == null || this.getWiredData().getText() == null) {
            return;
        }

        if (!StringUtils.isNumeric(this.getWiredData().getText())) {
            return;
        }

        int questId = Integer.parseInt(this.getWiredData().getText());

        if (playerEntity.getPlayer().getQuests().hasStartedQuest(questId)) {
            // Already started it!
            return;
        }

        if (playerEntity.getPlayer().getData().getQuestId() != 0) {
            // We need to cancel their instance one.
            if (!playerEntity.getPlayer().getQuests().hasCompletedQuest(playerEntity.getPlayer().getData().getQuestId())) {
                playerEntity.getPlayer().getQuests().cancelQuest(playerEntity.getPlayer().getData().getQuestId());
            }
        }

        final IQuest quest = QuestManager.getInstance().getById(questId);

        if (quest == null) {
            return;
        }

        playerEntity.getPlayer().getQuests().startQuest(quest);
    }
}
