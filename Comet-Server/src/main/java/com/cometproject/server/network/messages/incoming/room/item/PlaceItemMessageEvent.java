package com.cometproject.server.network.messages.incoming.room.item;

import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.api.game.players.data.components.inventory.PlayerItem;
import com.cometproject.api.game.quests.QuestType;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.achievements.BattlePassGlobals;
import com.cometproject.server.game.achievements.types.BattlePassMission;
import com.cometproject.server.game.achievements.types.BattlePassMissionEnums;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.mapping.RoomTile;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.nuxs.EmailVerificationWindowMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;
import com.google.common.collect.Maps;

import java.util.Map;


public class PlaceItemMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) {
        if (client.getPlayer() == null || client.getPlayer().getEntity() == null) {
            return;
        }

        if(client.getPlayer().getPermissions().getRank().modTool() && !client.getPlayer().getSettings().isPinSuccess()) {
            client.getPlayer().sendBubble("pincode", Locale.getOrDefault("pin.code.required", "Debes verificar tu PIN antes de realizar cualquier acción."));
            client.send(new EmailVerificationWindowMessageComposer(1,1));
            return;
        }

        String data = msg.readString();

        if (client.getPlayer() == null || client.getPlayer().getInventory() == null) return;

        String[] parts = data.split(" ");
        int id = Integer.parseInt(parts[0].replace("-", ""));

        if (!client.getPlayer().getEntity().getRoom().getRights().canPlaceFurniture(client.getPlayer().getId())
                && !client.getPlayer().getPermissions().getRank().roomFullControl() && !client.getPlayer().getRentable().hasRent()) {
            Map<String, String> notificationParams = Maps.newHashMap();

            notificationParams.put("message", "${room.error.cant_set_not_owner}");

            client.send(new NotificationMessageComposer("furni_placement_error", notificationParams));
            return;
        }

        try {
            if (parts.length > 1 && parts[1].startsWith(":")) {
                String position = Position.validateWallPosition(parts[1] + " " + parts[2] + " " + parts[3]);

                if (position == null) {
                    return;
                }

                Long itemId = ItemManager.getInstance().getItemIdByVirtualId(id);

                if (itemId == null) {
                    return;
                }

                PlayerItem item = client.getPlayer().getInventory().getItem(itemId);

                if (item == null) {
                    return;
                }

                client.getPlayer().getEntity().getRoom().getItems().placeWallItem(item, position, client.getPlayer());
            } else {
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);
                int rot = Integer.parseInt(parts[3]);

                Long itemId = ItemManager.getInstance().getItemIdByVirtualId(id);

                if (itemId == null) {
                    return;
                }

                PlayerItem item = client.getPlayer().getInventory().getItem(itemId);

                if (item == null) {
                    return;
                }

                if(item.getDefinition().getInteraction().equals("monsterplant_seed") && client.getPlayer().getEntity().getRoom().getGroup() != null  && !client.getPlayer().getGroups().contains(client.getPlayer().getEntity().getRoom().getGroup().getId())){
                    client.send(new WhisperMessageComposer(-1, Locale.getOrDefault("requires.mafia.room", "La sala en la que estás intentando colocar este objeto no es la de tu mafia."), 34));
                    return;
                }

                client.getPlayer().getEntity().getRoom().getItems().placeFloorItem(item, x, y, rot, client.getPlayer(), false);

                if (client.getPlayer().getEntity().getRoom().getItems() == null) {
                    return;
                }

                RoomItemFloor floorItem = client.getPlayer().getEntity().getRoom().getItems().getFloorItem(item.getId());

                if (floorItem != null) {
                    RoomTile tile = floorItem.getTile();

                    if (tile != null) {
                        if (tile.getItems().size() > 1) {
                            client.getPlayer().getQuests().progressQuest(QuestType.FURNI_STACK);
                        }
                    }
                }

                BattlePassMission ms = BattlePassGlobals.battlePassMissions.stream().filter(xz -> xz.type == BattlePassMissionEnums.MissionType.PLACEFURNI).findAny().orElse(null);
                if(ms != null){
                    if(client.getPlayer().getData().battlePass != null) client.getPlayer().getData().battlePass.addExperiencePoint(ms.id);
                }
            }
        } catch (Exception e) {
            client.getLogger().error("Error while placing item", e);
        }

        client.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_45, 1);
        client.getPlayer().getQuests().progressQuest(QuestType.FURNI_PLACE);
    }
}
