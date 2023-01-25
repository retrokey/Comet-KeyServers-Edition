package com.cometproject.server.game.rooms.objects.entities.types.ai.bots;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.RoomEntityType;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.entities.types.ai.AbstractBotAI;
import com.cometproject.server.game.rooms.objects.entities.types.data.types.SpyBotData;

public class SpyAI extends AbstractBotAI {

    private boolean hasSaidYes = false;

    public SpyAI(RoomEntity entity) {
        super(entity);
    }

    @Override
    public boolean onPlayerEnter(PlayerEntity playerEntity) {
        if (playerEntity.getPlayerId() != this.getBotEntity().getData().getOwnerId()) {
            if (!playerEntity.getPlayer().isInvisible()) {
                if (!((SpyBotData) this.getBotEntity().getDataObject()).getVisitors().contains(playerEntity.getUsername())) {
                    ((SpyBotData) this.getBotEntity().getDataObject()).getVisitors().add(playerEntity.getUsername());
                }
            }
        } else {
            if (((SpyBotData) this.getBotEntity().getDataObject()).getVisitors().size() == 0) {
                this.getBotEntity().say(Locale.getOrDefault("comet.game.bot.spy.noVisitors", "There have been no visitors while you've been away!!!"));
                this.hasSaidYes = true;
            } else {
                this.getBotEntity().say(Locale.getOrDefault("comet.game.bot.spy.sayYes", "Nice to see you Sir! Please say yes if you'd like me to tell who have visited room while you've been gone."));
                this.hasSaidYes = false;
            }
        }

        return false;
    }

    @Override
    public boolean onTalk(PlayerEntity entity, String message) {
        if (this.hasSaidYes) {
            return false;
        }

        if (entity.getPlayerId() == this.getBotEntity().getData().getOwnerId()) {
            if (message.equals("yes") || message.equals("si") || message.equals("Sí") || message.equals("ya") || message.equals(Locale.getOrDefault("comet.game.bot.yes", "yes"))) {
                StringBuilder stillIn = new StringBuilder();
                StringBuilder left = new StringBuilder();

                for (String username : ((SpyBotData) this.getBotEntity().getDataObject()).getVisitors()) {
                    boolean isLast = ((SpyBotData) this.getBotEntity().getDataObject()).getVisitors().indexOf(username) == (((SpyBotData) this.getBotEntity().getDataObject()).getVisitors().size() - 1);

                    if (this.getBotEntity().getRoom().getEntities().getEntityByName(username, RoomEntityType.PLAYER) != null) {
                        if (isLast) {
                            stillIn.append(username).append(stillIn.toString().equals("") ? Locale.getOrDefault("comet.game.bot.spy.stillInRoom.single", " is still in the room") : Locale.getOrDefault("comet.game.bot.spy.stillInRoom.multiple", " are still in the room"));
                        } else {
                            stillIn.append(username).append(", ");
                        }
                    } else {
                        if (isLast) {
                            left.append(username).append(left.toString().equals("") ? Locale.getOrDefault("comet.game.bot.spy.leftRoom.single", " has left") : Locale.getOrDefault("comet.game.bot.spy.leftRoom.multiple", " have left"));
                        } else {
                            left.append(username).append(", ");
                        }
                    }
                }

                if (!left.toString().equals("")) {
                    this.getBotEntity().say(left.toString());
                }

                if (!stillIn.toString().equals("")) {
                    this.getBotEntity().say(stillIn.toString());
                }

                ((SpyBotData) this.getBotEntity().getDataObject()).getVisitors().clear();
                this.getBotEntity().saveDataObject();
                this.hasSaidYes = true;
            }
        }
        return false;
    }

    @Override
    public boolean onPlayerLeave(PlayerEntity entity) {
        if (entity.getPlayerId() == this.getBotEntity().getData().getOwnerId()) {
            this.hasSaidYes = false;
        }

        return false;
    }

    @Override
    public boolean onAddedToRoom() {
        this.getBotEntity().say(Locale.getOrDefault("comet.game.bot.spy.addedToRoom", "Hi! Next time you enter the room, I'll let you know who visited while you were away.."));
        return false;
    }
}
