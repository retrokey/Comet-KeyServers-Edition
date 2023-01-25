package com.cometproject.server.game.commands.vip;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.pets.PetManager;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.network.messages.outgoing.room.avatar.AvatarsMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.LeaveRoomMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.RemoveFloorItemMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.SendFloorItemMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.SendFloorTransformationMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.storage.queries.items.ItemDao;


public class TransformCommand extends ChatCommand {
    public static void composeTransformation(IComposer msg, String[] transformationData, PlayerEntity entity) {
        // TODO: create global composer for entity types maybe
        msg.writeInt(entity.getPlayerId());
        msg.writeString(entity.getUsername());
        msg.writeString(entity.getMotto());
        msg.writeString(transformationData[0]);
        msg.writeInt(entity.getId());

        msg.writeInt(entity.getPosition().getX());
        msg.writeInt(entity.getPosition().getY());
        msg.writeDouble(entity.getPosition().getZ());

        msg.writeInt(0); // 2 = user 4 = bot 0 = pet ??????
        msg.writeInt(2); // 1 = user 2 = pet 3 = bot ??????n

        msg.writeInt(Integer.parseInt(transformationData[1]));
        msg.writeInt(entity.getPlayerId());
        msg.writeString("Leon"); // TODO: this :P
        msg.writeInt(1);
        msg.writeBoolean(true); // has saddle
        msg.writeBoolean(false); // has rider?

        msg.writeInt(0);
        msg.writeInt(0);
        msg.writeString("");
    }

    @Override
    public void execute(Session client, String[] params) {

        String type = params[0];

        if (type.toLowerCase().equals("item")) {
            if(params[1].equals("remove")){
                client.getPlayer().getEntity().removeAttribute("item");
                client.getPlayer().getEntity().setTransformed(false);
                client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new RemoveFloorItemMessageComposer(2147418112 - client.getPlayer().getEntity().getId(), 0, 0));
                client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new AvatarsMessageComposer(client.getPlayer().getEntity()));
                return;
            }

            String item = ItemDao.getSpriteByName(params[1]) + "";

            client.getPlayer().getEntity().setAttribute("item", item);
            client.getPlayer().getEntity().setTransformed(true);
            client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new LeaveRoomMessageComposer(client.getPlayer().getEntity().getId()));
            client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new SendFloorTransformationMessageComposer(client.getPlayer().getEntity()));
            return;
        }
        if (type.toLowerCase().equals("human")) {
            client.getPlayer().getEntity().removeAttribute("transformation");
        } else {
            String data = PetManager.getInstance().getTransformationData(params[0].toLowerCase());

            if (data == null || data.isEmpty()) {
                sendWhisper(Locale.getOrDefault("command.transform.notexists", "Oops! This Pet name does not exists."), client);
                return;
            }

            client.getPlayer().getEntity().setAttribute("transformation", data);
        }

        client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new LeaveRoomMessageComposer(client.getPlayer().getEntity().getId()));
        client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new AvatarsMessageComposer(client.getPlayer().getEntity()));
        isExecuted(client);
    }

    @Override
    public String getPermission() {
        return "transform_command";
    }

    @Override
    public String getParameter() {
        return "%petname%";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.transform.description");
    }

    @Override
    public boolean canDisable() {
        return true;
    }
}
