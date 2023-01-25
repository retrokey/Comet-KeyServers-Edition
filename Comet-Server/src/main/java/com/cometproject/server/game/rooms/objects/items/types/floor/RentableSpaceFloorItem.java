package com.cometproject.server.game.rooms.objects.items.types.floor;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.items.crafting.CraftingMachine;
import com.cometproject.server.game.players.types.Player;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.crafting.CraftableProductsMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.AlertMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.MassEventMessageComposer;
import com.cometproject.server.network.messages.outgoing.rentables.RentableDataMessageComposer;
import com.cometproject.server.storage.queries.items.ItemDao;
import com.cometproject.server.storage.queries.player.PlayerDao;

public class RentableSpaceFloorItem  extends RoomItemFloor {

    public RentableSpaceFloorItem (RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    @Override
    public boolean onInteract(RoomEntity entity, int requestData, boolean isWiredTrigger) {
        if (isWiredTrigger) {
            return false;
        }

        PlayerEntity p = ((PlayerEntity) entity);

        if(p == null) return false;

        if(ItemDao.getRentableData((int)getId()) < 1){
            p.getPlayer().getSession().send(new RentableDataMessageComposer(false, 0, 100));
            return true;
        }

        int renter = ItemDao.getRenterBySpace((int)getId());
        String renterName = PlayerDao.getRenterById(renter);

        p.getPlayer().getSession().send(new RentableDataMessageComposer(true, 0, renter, renterName, 100));
        return true;
    }

    @Override
    public void onPlaced() {
        this.getItemData().setData("48");
        this.sendUpdate();

        this.saveData();
    }

    @Override
    public void onItemAddedToStack(RoomItemFloor f){
    }

    public boolean verifyStatus(Player p){
        // Create Rentable component for player and check.
        return true;
    }
    @Override
    public void onTickComplete() {
    }
}