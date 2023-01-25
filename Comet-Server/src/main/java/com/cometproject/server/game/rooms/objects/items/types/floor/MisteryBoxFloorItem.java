package com.cometproject.server.game.rooms.objects.items.types.floor;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.game.players.types.MisteryComponent;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFactory;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.user.mistery.MisteryBoxDataMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.mistery.MisteryBoxOpenMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.storage.queries.player.PlayerDao;

public class MisteryBoxFloorItem extends RoomItemFloor {
    private boolean isInUse = false;
    private RoomEntity interactingEntity;

    public MisteryBoxFloorItem(RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    @Override
    public boolean onInteract(RoomEntity entity, int requestData, boolean isWiredTrigger) {
        if (this.isInUse || isWiredTrigger) {
            return false;
        }

        Session s = NetworkManager.getInstance().getSessions().getByPlayerId(this.getItemData().getOwnerId());

        // If the owner is not online, there's no possible interaction.
        if(s == null){
            this.isInUse = false;
            return true;
        }

        this.isInUse = true;
        this.interactingEntity = entity;

        // If the owner of the box tries to open it and it's open, then give the reward.
        if(getItemData().getOwnerId() == (((PlayerEntity) entity).getPlayer().getId())){
            if(Integer.parseInt(getItemData().getData()) == verifyColor(((PlayerEntity) entity).getPlayer().getMistery()) + 1) {
                s.getPlayer().getMistery().setMisteryBox("");
                PlayerDao.updateMysteryBox("", s.getPlayer().getId());
                this.isInUse = false;
                return true;
            } else {
                this.isInUse = false;
                return true;
            }
        }

        // If the player tries to open a box that has not a matching color.
        if(!verifyColor(getItemData().getData()).contains(((PlayerEntity) entity).getPlayer().getMistery().getMisteryKey()) || ((PlayerEntity) entity).getPlayer().getMistery().getMisteryKey().isEmpty()){
            this.isInUse = false;
            return true;
        }

        // Where all the magic happens.
        factorData(entity);

        this.getItemData().setData(verifyColor(s.getPlayer().getMistery()) + 1);
        this.sendUpdate();
        this.isInUse = false;
        return true;
    }

    @Override
    public void onPlaced() {
      Session s = NetworkManager.getInstance().getSessions().getByPlayerId(this.getItemData().getOwnerId());
      this.getItemData().setData(verifyColor(s.getPlayer().getMistery()));
      this.sendUpdate();
    }

    @Override
    public void onTickComplete() {
        if (this.isInUse) {
            this.interactingEntity.setOverriden(false);
            this.setTicks(RoomItemFactory.getProcessTime(1.0));
        }

        this.getItemData().setData("0");
        this.sendUpdate();

        this.isInUse = false;
        this.interactingEntity = null;
    }

    private int verifyColor(MisteryComponent m) {
        int extradata = 0;
        switch (m.getMisteryBox()){
            case "green":
                extradata = 7;
                break;
            case "lilac":
                extradata = 13;
                break;
            case "orange":
                extradata = 16;
                break;
            case "red":
                extradata = 22;
                break;
        }
        return extradata;
    }

    private String verifyColor(String c) {
        String color = "";
        switch (c){
            case "7":
                color = "green";
                break;
            case "13":
                color = "lilac";
                break;
            case "16":
                color = "orange";
                break;
            case "22":
                color = "red";
                break;
        }
        return color;
    }

    private void factorData(RoomEntity p){
        ((PlayerEntity) p).getPlayer().getMistery().setMisteryKey("");
        PlayerDao.updateMysteryKey("",((PlayerEntity) p).getPlayer().getId());

        ((PlayerEntity) p).getPlayer().getSession().send(new MisteryBoxOpenMessageComposer());
        ((PlayerEntity) p).getPlayer().getSession().send(new MisteryBoxDataMessageComposer(((PlayerEntity) p).getPlayer().getMistery()));

    }
}