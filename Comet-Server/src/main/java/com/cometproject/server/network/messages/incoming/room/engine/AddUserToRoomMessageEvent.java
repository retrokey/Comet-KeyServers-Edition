package com.cometproject.server.network.messages.incoming.room.engine;

import com.cometproject.api.game.GameContext;
import com.cometproject.api.game.groups.types.IGroupData;
import com.cometproject.server.composers.group.GroupBadgesMessageComposer;
import com.cometproject.server.game.polls.PollManager;
import com.cometproject.server.game.polls.types.Poll;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.effects.PlayerEffect;
import com.cometproject.server.game.rooms.objects.entities.types.PetEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.triggers.WiredTriggerEnterRoom;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.room.avatar.*;
import com.cometproject.server.network.messages.outgoing.room.engine.RoomEntryInfoMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.FloorItemsMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.SendFloorTransformationMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.SendShadowMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.WallItemsMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.permissions.FloodFilterMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.pets.horse.HorseFigureMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.polls.InitializePollMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.polls.QuickPollMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.polls.QuickPollResultsMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.settings.RoomVisualizationSettingsMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;
import com.cometproject.server.storage.queries.polls.PollDao;

import java.util.HashMap;
import java.util.Map;


public class AddUserToRoomMessageEvent implements Event {
    public void handle(Session client, MessageEvent msg) {
        if(client.getPlayer().antiSpam("AddUserToRoomMessageEvent", 0.5))
            return;

        PlayerEntity avatar = client.getPlayer().getEntity();

        if (avatar == null) {
            return;
        }

        Room room = avatar.getRoom();

        if (room == null) {
            return;
        }

        if (!room.getProcess().isActive()) {
            room.getProcess().start();
        }

        if (!room.getItemProcess().isActive()) {
            room.getItemProcess().start();
        }

        if (client.getPlayer().getRoomFloodTime() >= 1) {
            client.sendQueue(new FloodFilterMessageComposer(client.getPlayer().getRoomFloodTime()));
        }

        Map<Integer, String> groupsInRoom = new HashMap<>();

        for (PlayerEntity playerEntity : room.getEntities().getPlayerEntities()) {
            if (playerEntity.getPlayer() != null && playerEntity.getPlayer().getData() != null) {
                if (playerEntity.getPlayer().getData().getFavouriteGroup() != 0) {
                    IGroupData groupData = GameContext.getCurrent().getGroupService().getData(playerEntity.getPlayer().getData().getFavouriteGroup());

                    if (groupData == null)
                        continue;

                    groupsInRoom.put(playerEntity.getPlayer().getData().getFavouriteGroup(), groupData.getBadge());
                }
            }
        }

        client.sendQueue(new GroupBadgesMessageComposer(groupsInRoom));
        client.sendQueue(new RoomEntryInfoMessageComposer(room.getId(), room.getData().getOwnerId() == client.getPlayer().getId() || client.getPlayer().getPermissions().getRank().roomFullControl()));
        client.sendQueue(new AvatarsMessageComposer(room));

        if (client.getPlayer().getInventory().getEquippedEffect() != -1) {
            client.getPlayer().getEntity().applyEffect(new PlayerEffect(client.getPlayer().getInventory().getEquippedEffect(), false));
        }

        if (room.getEntities().getAllEntities().size() > 0)
            client.sendQueue(new AvatarUpdateMessageComposer(room.getEntities().getAllEntities().values()));

        for (RoomEntity av : room.getEntities().getAllEntities().values()) {
            if (av.getCurrentEffect() != null) {
                client.sendQueue(new ApplyEffectMessageComposer(av.getId(), av.getCurrentEffect().getEffectId()));
            }

            if (av.getDanceId() != 0) {
                client.sendQueue(new DanceMessageComposer(av.getId(), av.getDanceId()));
            }

            if (av.getHandItem() != 0) {
                client.sendQueue(new HandItemMessageComposer(av.getId(), av.getHandItem()));
            }

            if (av.isIdle()) {
                client.sendQueue(new IdleStatusMessageComposer((PlayerEntity) av, true));
            }

            if (av.getAI() != null) {
                if (av instanceof PetEntity && ((PetEntity) av).getData().getTypeId() == 15) {
                    client.send(new HorseFigureMessageComposer(((PetEntity) av)));
                }

                av.getAI().onPlayerEnter(client.getPlayer().getEntity());
            }

            if(av.isTransformed()){
                client.sendQueue(new SendFloorTransformationMessageComposer((PlayerEntity)av));
            }
        }

        client.sendQueue(new RoomVisualizationSettingsMessageComposer(room.getData().getHideWalls(), room.getData().getWallThickness(), room.getData().getFloorThickness()));
        client.getPlayer().getMessenger().sendStatus(true, true);

        client.sendQueue(new FloorItemsMessageComposer(room));
        client.sendQueue(new WallItemsMessageComposer(room));

        if(client.getPlayer().getShadowStatus() > 0){
            client.sendQueue(new SendShadowMessageComposer(avatar.getPlayerId(), avatar.getPosition()));
            client.getPlayer().setShadow(2);
        }

        WiredTriggerEnterRoom.executeTriggers(client.getPlayer().getEntity());

        if (PollManager.getInstance().roomHasPoll(room.getId())) {
            Poll poll = PollManager.getInstance().getPollByRoomId(room.getId());

            if (!poll.getPlayersAnswered().contains(client.getPlayer().getId()) && !PollDao.hasAnswered(client.getPlayer().getId(), poll.getPollId())) {
                client.send(new InitializePollMessageComposer(poll.getPollId(), poll.getPollTitle(), poll.getThanksMessage()));
            }
        }

        if (room.getQuestion() != null) {
            client.send(new QuickPollMessageComposer(room.getQuestion()));

            if (room.getYesVotes().contains(client.getPlayer().getId()) || room.getNoVotes().contains(client.getPlayer().getId())) {
                client.send(new QuickPollResultsMessageComposer(room.getYesVotes().size(), room.getNoVotes().size()));
            }
        }

        client.flush();
        avatar.markNeedsUpdate();
    }
}
