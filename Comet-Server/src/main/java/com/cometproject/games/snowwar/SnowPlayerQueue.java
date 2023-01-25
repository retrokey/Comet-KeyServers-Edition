package com.cometproject.games.snowwar;

import com.cometproject.api.config.CometSettings;
import com.cometproject.games.snowwar.gameevents.PlayerLeft;
import com.cometproject.games.snowwar.gameobjects.HumanGameObject;
import com.cometproject.games.snowwar.thread.SnowWarTask;
import com.cometproject.server.game.players.data.PlayerData;
import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.*;
import com.cometproject.server.network.messages.outgoing.room.permissions.YouArePlayingGameMessageComposer;
import com.cometproject.server.network.sessions.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SnowPlayerQueue {
    private static final Map<Integer, RoomQueue> roomQueue = new ConcurrentHashMap<>(100);
    private static int roomCounter = 0;

    public static void addPlayerInQueue(Session cn) {
        if(!CometSettings.snowStormEnabled) {
            cn.getPlayer().sendBubble("", "Las colas Beta de Snow Storm están desactivadas ahora mismo.");
            return;
        }

        PlayerData playerData = cn.getPlayer().getData();
        RoomQueue pickRoom = null;
        if (roomQueue.isEmpty()) {
            pickRoom = new RoomQueue(new SnowWarRoom(++roomCounter));
            roomQueue.put(pickRoom.room.roomId, pickRoom);
        } else {
            for (RoomQueue room : roomQueue.values()) {
                if (room.players.size() < 10) {
                    pickRoom = room;

                    break;
                }
            }
            if (pickRoom == null) {
                pickRoom = new RoomQueue(new SnowWarRoom(++roomCounter));
                roomQueue.put(pickRoom.room.roomId, pickRoom);
            }
        }
        if (pickRoom.players.isEmpty()) {
            pickRoom.room.Owner = playerData.getUsername();
        }
        cn.snowWarPlayerData.setHumanObject(new HumanGameObject(pickRoom.room, 0));
        cn.snowWarPlayerData.humanObject.status = 0;
        cn.snowWarPlayerData.setRoom(pickRoom.room);
//cn.getPlayer().getMessenger().sendNotification(FriendNotificationType.PLAYING_GAME, "snowwar", true, (cn.getPlayer().getEntity() != null));
        pickRoom.broadcast(new UserJoinedGameComposer(cn));
        pickRoom.players.put(playerData.getId(), cn);
        if (pickRoom.room.Owner.equals(playerData.getUsername())) {
            cn.getChannel().writeAndFlush(new GameCreatedComposer(pickRoom));
        } else {
            cn.getChannel().writeAndFlush(new GameLongDataComposer(pickRoom));
        }

        if (pickRoom.room.TimeToStart < 20 && pickRoom.room.STATUS == 1) {
            cn.send(new StartCounterComposer(pickRoom.room.TimeToStart));
        }
        if (pickRoom.players.size() >= CometSettings.snowStormMinPlayers) {
            startLoading(pickRoom);
        }
    }

    public static void playerExit(SnowWarRoom room, HumanGameObject playerObject) {
        RoomQueue queue = roomQueue.get(room.roomId);
        if (queue == null) {
            room.players.remove(playerObject.userId);
            room.TeamPlayers.get(playerObject.team).remove(playerObject.userId);
            if (room.STATUS == 5) {
                synchronized (room.gameEvents) {
                    room.gameEvents.add(new PlayerLeft(playerObject));
                }
                return;
            }
            room.broadcast(new UserLeftGameComposer(playerObject.userId));
        } else {
            queue.broadcast(new UserLeftGameComposer(playerObject.userId));
            queue.players.remove(playerObject.userId);
        }
        playerObject.cleanData();
    }

    public static void roomLoaded(SnowWarRoom room) {
        RoomQueue queue = roomQueue.remove(room.roomId);
        if (queue == null) {
            return;
        }
        int pickTeam = 0;
        for (Session cn : queue.players.values()) {
            room.players.put(cn.getPlayer().getId(), cn.snowWarPlayerData.humanObject);
            int team = 1 + ++pickTeam % SnowWar.TEAMS.length;
            cn.snowWarPlayerData.humanObject.team = team;
            (room.TeamPlayers.get(team)).put(cn.getPlayer().getData().getId(), cn.snowWarPlayerData.humanObject);
        }

        queue.broadcast(new GameStartedComposer(queue));
        queue.broadcast(new InArenaQueueComposer(1));
        queue.broadcast(new YouArePlayingGameMessageComposer(true));
        room.broadcast(new EnterArenaComposer(room));
        for (HumanGameObject player : room.players.values()) {
            player.status = 1;
            if (player.cn.getPlayer().getEntity() != null) {
                player.cn.getPlayer().getEntity().leaveRoom(false, false, false);
            }

            room.broadcast(new ArenaEnteredComposer(player));
        }
        room.broadcast(new StageLoadComposer());
    }

    private static void startLoading(RoomQueue queue) {
        SnowWarRoom room = queue.room;
        if (room.STATUS == 1) {
            return;
        }
        room.TimeToStart = 15;
        room.STATUS = 1;
        queue.broadcast(new StartCounterComposer(room.TimeToStart));
        SnowWarTask.addTask(new SnowWarTask(room), 0, 1000);
    }

    public static void cleanupQueues(){
        roomQueue.clear();
        roomCounter = 0;
    }
}