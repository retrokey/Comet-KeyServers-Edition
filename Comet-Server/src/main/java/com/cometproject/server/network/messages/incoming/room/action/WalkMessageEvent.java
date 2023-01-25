package com.cometproject.server.network.messages.incoming.room.action;

import com.cometproject.api.game.players.data.components.inventory.PlayerItem;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.RoomEntityType;
import com.cometproject.server.game.rooms.objects.entities.pathfinding.Square;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.mapping.RoomTile;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.IdleStatusMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.SendFloorItemMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.SendShadowMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.SlideObjectBundleMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

import java.util.LinkedList;
import java.util.List;


public class WalkMessageEvent implements Event {
    public void handle(Session client, MessageEvent msg) {
        int goalX = msg.readInt();
        int goalY = msg.readInt();

        try {
            if (client.getPlayer().getEntity() == null) {
                return;
            }

            if(client.getPlayer().getEntity().hasAttribute("warping")){
                client.getPlayer().sendBubble("", "Movement is canceled now.");
            }

            PlayerEntity entity = client.getPlayer().getEntity();

            if (!entity.isVisible()) return;

            if (goalX == entity.getPosition().getX() && goalY == entity.getPosition().getY()) {
                return;
            }

            entity.getRoom().getEntities().broadcastMessage(new IdleStatusMessageComposer(entity, false));

            if (entity.isOneGate()){
                entity.setOverriden(false);
                entity.moveTo(goalX, goalY);
            }

            if (entity.hasAttribute("tpencours")) {
                return;
            }

            if (entity.hasAttribute(("tptpencours"))) {
                return;
            }

            if (entity.hasAttribute("teleport")) {
                Position newPosition = new Position(goalX, goalY);
                final RoomTile tile = client.getPlayer().getEntity().getRoom().getMapping().getTile(newPosition);

                newPosition.setZ(tile.getWalkHeight());

                entity.unIdle();

                if (entity.getMountedEntity() != null) {
                    entity.warp(newPosition);
                }

                entity.warp(newPosition);
                return;
            }


            if (entity.hasAttribute("control")) {
                RoomEntity p = entity.getRoom().getEntities().getEntityByName(entity.getAttribute("control").toString(), RoomEntityType.PLAYER);
                if (p != null) p.moveTo(goalX, goalY);
                return;
            }

            if (entity.hasAttribute("escort")) {
                RoomEntity p = entity.getRoom().getEntities().getEntityByName(entity.getAttribute("escort").toString(), RoomEntityType.PLAYER);
                if (p != null) {
                    Position goal = new Position(goalX, goalY).squareInFront(entity.getBodyRotation(), 1);

                        p.moveTo(goal.getX(), goal.getY());
                        p.lookTo(goal.getX(), goal.getY());
                        entity.moveTo(goalX, goalY);
                        return;
                }
            }

            if (!entity.sendUpdateMessage()) {
                entity.setSendUpdateMessage(true);
            }

            /*if(entity.getPlayer().getShadowStatus() > 0){
                RoomTile tile = entity.getRoom().getMapping().getTile(goalX, goalY);

                switch (entity.getPlayer().getShadowStatus()){
                    case 1:
                        entity.getPlayer().setShadow(2);
                        entity.getPlayer().getSession().send(new SendShadowMessageComposer(entity.getPlayerId(), tile.getPosition()));
                        break;
                    case 2:
                        entity.getPlayer().getSession().send(new SlideObjectBundleMessageComposer(entity.getPosition(), tile.getPosition(), entity.getPlayerId()));
                }
            }*/

            if (entity.canWalk() && !entity.isOverriden() && entity.isVisible())
                entity.moveTo(goalX, goalY);

            /*if(entity.hasAttribute("item")){
                entity.getRoom().getEntities().broadcastMessage(new SlideObjectBundleMessageComposer(new Position(entity.getPosition().getX(),entity.getPosition().getY()), new Position(goalX, goalY), 0, 0, Integer.MAX_VALUE - entity.getId()));
            }*/

        } catch (Exception e) {
            client.getLogger().error("Error while finding path", e);
        }
    }
}
