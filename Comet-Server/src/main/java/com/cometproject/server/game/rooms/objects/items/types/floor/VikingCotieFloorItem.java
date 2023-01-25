package com.cometproject.server.game.rooms.objects.items.types.floor;

import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.items.crafting.CraftingMachine;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.outgoing.crafting.CraftableProductsMessageComposer;

public class VikingCotieFloorItem extends RoomItemFloor {
    public VikingCotieFloorItem (RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    @Override
    public boolean onInteract(RoomEntity entity, int requestData, boolean isWiredTrigger) {
        if (isWiredTrigger) {
            return false;
        }

        PlayerEntity p = ((PlayerEntity) entity);

        if (this.getPosition().distanceTo(new Position(entity.getPosition().getX(), entity.getPosition().getY())) > 3) {
            entity.moveTo(this.getPosition().squareInFront(this.getRotation()).getX(), this.getPosition().squareInFront(this.getRotation()).getY());
            return false;
        }

        if(p.getCurrentEffect().getEffectId() == 5) {
            int count = Integer.parseInt(this.getItemData().getData());

            if(count < 5){
                count++;
                this.getItemData().setData(count);
                p.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_65, 1);
            }

            this.sendUpdate();
            this.saveData();
        }
        return true;
    }

    @Override
    public void onPlaced() {
        this.getItemData().setData("0");
        this.sendUpdate();
        this.saveData();
    }

    @Override
    public void onTickComplete() {
    }
}
