package com.cometproject.server.game.rooms.objects.items.types.floor;
import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.items.crafting.CraftingMachine;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.Room;

public class CraftingMachineFloorItem  extends RoomItemFloor {

    public CraftingMachineFloorItem (RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    @Override
    public boolean onInteract(RoomEntity entity, int requestData, boolean isWiredTrigger) {
        if (isWiredTrigger) {
            return false;
        }

        PlayerEntity p = ((PlayerEntity) entity);

        if(p == null)
            return false;

        CraftingMachine machine = ItemManager.getInstance().getCraftingMachine(this.getDefinition().getId());

        if(machine == null)
            return false;

        p.getPlayer().setLastCraftingMachine(machine);

        //p.getPlayer().getSession().send(new CraftableProductsMessageComposer(machine));
        return true;
    }

    @Override
    public void onPlaced() {
        this.getItemData().setData("1");
        this.sendUpdate();

        this.saveData();
    }

    @Override
    public void onTickComplete() {
    }
}