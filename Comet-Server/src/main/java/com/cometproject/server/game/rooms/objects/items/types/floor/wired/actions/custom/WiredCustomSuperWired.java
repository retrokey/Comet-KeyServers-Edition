package com.cometproject.server.game.rooms.objects.items.types.floor.wired.actions.custom;

import com.cometproject.api.game.rooms.entities.RoomEntityStatus;
import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.api.networking.messages.IMessageComposer;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.rooms.objects.entities.effects.PlayerEffect;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.base.WiredActionItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.events.WiredItemEvent;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.messages.outgoing.notification.AlertMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.DanceMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.engine.RoomForwardMessageComposer;
import org.apache.commons.lang.StringUtils;

import static com.cometproject.api.game.rooms.entities.RoomEntityStatus.LAY;
import static com.cometproject.api.game.rooms.entities.RoomEntityStatus.SIT;

public class WiredCustomSuperWired extends WiredActionItem {

    public WiredCustomSuperWired(RoomItemData itemData, Room room) {
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

        String str = this.getWiredData().getText();
        String[] FinalText = str.split(":");
        switch (FinalText[0].toLowerCase()) {
            case "enable": {
                if (!StringUtils.isNumeric((String)FinalText[1])) {
                    return;
                }
                int enableId = Integer.parseInt(FinalText[1]);
                if (playerEntity.getCurrentEffect() != null) {
                    if (playerEntity.getCurrentEffect().getEffectId() == enableId) {
                        playerEntity.applyEffect(new PlayerEffect(0, 0));
                        break;
                    }
                    playerEntity.applyEffect(new PlayerEffect(enableId, 0));
                    break;
                }
                playerEntity.applyEffect(new PlayerEffect(enableId, 0));
                break;
            }
            case "handitem": {
                if (!StringUtils.isNumeric((String)FinalText[1])) {
                    return;
                }
                int handitemId = Integer.parseInt(FinalText[1]);
                if (playerEntity.getHandItem() == handitemId) {
                    playerEntity.carryItem(0, false);
                    break;
                }
                playerEntity.carryItem(handitemId, false);
                break;
            }
            case "freeze": {
                if (FinalText[1].equalsIgnoreCase("true")) {
                    playerEntity.cancelWalk();
                    playerEntity.setCanWalk(false);
                }
                if(FinalText[1].equalsIgnoreCase("false")){
                    playerEntity.cancelWalk();
                    playerEntity.setCanWalk(true);
                }
                break;
            }
            case "sit": {
                if (playerEntity.hasStatus(RoomEntityStatus.SIT) && playerEntity.hasStatus(RoomEntityStatus.LAY)) {
                    playerEntity.removeStatus(RoomEntityStatus.LAY);
                    playerEntity.removeStatus(RoomEntityStatus.SIT);
                }
                if (playerEntity.hasStatus(RoomEntityStatus.SIT)) {
                    playerEntity.removeStatus(RoomEntityStatus.SIT);
                    playerEntity.markNeedsUpdate();
                    break;
                }
                playerEntity.sit(0.5, playerEntity.getBodyRotation());
                break;
            }
            case "lay": {
                if (playerEntity.hasStatus(RoomEntityStatus.LAY)) {
                    playerEntity.removeStatus(RoomEntityStatus.LAY);
                    playerEntity.markNeedsUpdate();
                    break;
                }
                if (playerEntity.hasStatus(RoomEntityStatus.SIT)) {
                    playerEntity.removeStatus(RoomEntityStatus.SIT);
                    playerEntity.addStatus(RoomEntityStatus.LAY, "0.5");
                    playerEntity.markNeedsUpdate();
                    break;
                }
                playerEntity.addStatus(RoomEntityStatus.LAY, "0.5");
                playerEntity.markNeedsUpdate();
                break;
            }
            case "dance": {
                if (!StringUtils.isNumeric((String)FinalText[1])) {
                    return;
                }
                int danceId = Integer.parseInt(FinalText[1]);
                playerEntity.setDanceId(danceId);
                playerEntity.getRoom().getEntities().broadcastMessage(new DanceMessageComposer(playerEntity.getId(), danceId));
                break;
            }
            case "moonwalk": {
                playerEntity.applyEffect(new PlayerEffect(136, 0));
                break;
            }
            case "toroom": {
                if (StringUtils.isNumeric(FinalText[1])) {
                    playerEntity.getPlayer().bypassRoomAuth(true);
                    playerEntity.getPlayer().getSession().send(new RoomForwardMessageComposer(Integer.parseInt(FinalText[1])));
                }
                break;
            }
            case "fastwalk": {
                if (StringUtils.isNumeric(FinalText[1])) {
                    if (Integer.parseInt(FinalText[1]) == 1) {
                        playerEntity.getPlayer().getEntity().setFastWalkEnabled(true);
                        break;
                    }
                    if (Integer.parseInt(FinalText[1]) == 0) {
                        playerEntity.getPlayer().getEntity().setFastWalkEnabled(false);
                        break;
                    }
                }
            }
            case "roommute": {
                if (FinalText[1].equalsIgnoreCase("true")) {
                    playerEntity.getRoom().setRoomMute(true);
                    playerEntity.getPlayer().getSession().send(new AlertMessageComposer("la sala muteada", null));
                    break;
                }
                if (FinalText[1].equalsIgnoreCase("false")) {
                    playerEntity.getRoom().setRoomMute(false);
                    break;
                }
            }
            case "points": {
                if (StringUtils.isNumeric(FinalText[1])) {
                    if (playerEntity.getAttribute("points_game") != null) {
                        int points = Integer.parseInt(String.valueOf(playerEntity.getAttribute("points_game"))) + Integer.parseInt(FinalText[1]);
                        playerEntity.setAttribute("points_game", points);
                        break;
                    }
                    playerEntity.setAttribute("points_game", FinalText[1]);
                }
                break;
            }
            case "removepoints": {
                if (StringUtils.isNumeric(FinalText[1])) {
                    int points = Integer.parseInt(String.valueOf(playerEntity.getAttribute("points_game"))) - Integer.parseInt(FinalText[1]);
                    playerEntity.setAttribute("points_game", points);
                }
               break;
            }
            case "resetpoints": {
                if (playerEntity.getAttribute("points_game") != null){
                    playerEntity.removeAttribute("points_game");
                }
                break;
            }
            case "verifypoints": {
                int points = Integer.parseInt(String.valueOf(playerEntity.getAttribute("points_game")));
                if (FinalText[1].equalsIgnoreCase("true")) {
                    playerEntity.getPlayer().getSession().send(new TalkMessageComposer(playerEntity.getPlayerId(), "Tus puntos" + points, ChatEmotion.NONE, 1));
                }
            }
        }
    }
}