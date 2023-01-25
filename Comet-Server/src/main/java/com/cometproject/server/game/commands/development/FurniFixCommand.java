package com.cometproject.server.game.commands.development;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import org.apache.commons.lang.StringUtils;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.storage.queries.rooms.FurniFixDao;

public class FurniFixCommand extends ChatCommand {

    @Override
    public void execute(Session client, String[] params) {
        if (params.length < 1) {
            sendAlert(Locale.getOrDefault("command.furnifix.info", "<b>¡FurniFix Types!</b>\rA continuación verás los tipos de valores que hay para editar los furnis\r\rPara editarlos debes estar parado encima de el, en caso de no poder usa el teleport  <b>:\r\n furnifix cansit 0/1 [0 para desactivar - 1 para activar]\r\n furnifix canwalk 0/1 [0 para desactivar, 1 para activar]\r\n furnifix width Número [Edita la anchura del item"), client);
        }
        final PlayerEntity playerEntity = client.getPlayer().getEntity();
        RoomItemFloor floorItem = playerEntity.getTile().getTopItemInstance();
        if (floorItem == null) {
            client.getPlayer().getSession().send(new TalkMessageComposer(client.getPlayer().getEntity().getId(), "Para fixear o arreglar un furni debes estar sobre el furni que quieres arreglar", ChatEmotion.NONE, 34));
            return;
        }
        final long itemId = floorItem.getItemData().getItemId();

        String type = params[0].toLowerCase();
        String option = params[1];
        String name = this.merge(params, 1);
        switch(type) {
            case "name":
                FurniFixDao.changeName(name, itemId);
                client.getPlayer().getSession().send(new TalkMessageComposer(playerEntity.getId(), "Has modificado el furni con id " + itemId + " para actualizar los cambios recoge el item y lo pones nuevamente", ChatEmotion.NONE, 34));
                ItemManager.getInstance().loadItemDefinitions();
                break;

            case "cansit":
                if (option.equals("1") || option.equals("0")) {
                    FurniFixDao.canSit(option, itemId);
                    client.getPlayer().getSession().send(new TalkMessageComposer(playerEntity.getId(), "Has modificado el furni con id " + itemId + " para actualizar los cambios recoge el item y lo pones nuevamente", ChatEmotion.NONE, 34));
                    ItemManager.getInstance().loadItemDefinitions();
                } else {
                    client.getPlayer().getSession().send(new TalkMessageComposer(client.getPlayer().getEntity().getId(), "Por favor introduce solo un valor entre 1 o 0, no otro.", ChatEmotion.NONE, 34));
                    return;
                }
                break;

            case "canwalk":
                if (option.equals("1") || option.equals("0")) {
                    FurniFixDao.isWalkable(option, itemId);
                    client.getPlayer().getSession().send(new TalkMessageComposer(playerEntity.getId(), "Has modificado el furni con id " + itemId + " para actualizar los cambios recoge el item y lo pones nuevamente", ChatEmotion.NONE, 34));
                    ItemManager.getInstance().loadItemDefinitions();
                } else {
                    client.getPlayer().getSession().send(new TalkMessageComposer(client.getPlayer().getEntity().getId(), "Por favor introduce solo un valor entre 1 o 0, no otro.", ChatEmotion.NONE, 34));
                    return;
                }
                break;

            case "width":
                if(StringUtils.isNumeric(option)) {
                    FurniFixDao.changeWidth(option, itemId);
                    client.getPlayer().getSession().send(new TalkMessageComposer(playerEntity.getId(), "Has modificado el furni con id " + itemId + " para actualizar los cambios recoge el item y lo pones nuevamente", ChatEmotion.NONE, 34));
                    ItemManager.getInstance().loadItemDefinitions();
                } else {
                    client.getPlayer().getSession().send(new TalkMessageComposer(client.getPlayer().getEntity().getId(), "Por favor introduce solo un valor númerico, no letras.", ChatEmotion.NONE, 34));
                    return;
                }
                break;

            case "length":
                if(StringUtils.isNumeric(option)) {
                    FurniFixDao.changeLength(option, itemId);
                    client.getPlayer().getSession().send(new TalkMessageComposer(playerEntity.getId(), "Has modificado el furni con id " + itemId + " para actualizar los cambios recoge el item y lo pones nuevamente", ChatEmotion.NONE, 34));
                    ItemManager.getInstance().loadItemDefinitions();
                } else {
                    client.getPlayer().getSession().send(new TalkMessageComposer(client.getPlayer().getEntity().getId(), "Por favor introduce solo un valor númerico, no letras.", ChatEmotion.NONE, 34));
                    return;
                }
                break;

            case "interaction":
                if(!StringUtils.isNumeric(option)) {
                    FurniFixDao.changeInteractionType(option, itemId);
                    client.getPlayer().getSession().send(new TalkMessageComposer(playerEntity.getId(), "Has modificado el furni con id " + itemId + " para actualizar los cambios recoge el item y lo pones nuevamente", ChatEmotion.NONE, 34));
                    ItemManager.getInstance().loadItemDefinitions();
                } else {
                    client.getPlayer().getSession().send(new TalkMessageComposer(client.getPlayer().getEntity().getId(), "Por favor introduce una interacción válida..", ChatEmotion.NONE, 34));
                    return;
                }
                break;

            case "interactioncount":
                if(StringUtils.isNumeric(option)) {
                    FurniFixDao.changeInteractionCount(option, itemId);
                    client.getPlayer().getSession().send(new TalkMessageComposer(playerEntity.getId(), "Has modificado el furni con id " + itemId + " para actualizar los cambios recoge el item y lo pones nuevamente", ChatEmotion.NONE, 34));
                    ItemManager.getInstance().loadItemDefinitions();
                } else {
                    client.getPlayer().getSession().send(new TalkMessageComposer(client.getPlayer().getEntity().getId(), "Por favor introduce un valor numérico valido, NO DECIMALES", ChatEmotion.NONE, 34));
                    return;
                }
                break;

            case "stackheight":
                try {
                    double options = Double.parseDouble(option);
                    FurniFixDao.changeStackHeight(options, itemId);
                    client.getPlayer().getSession().send(new TalkMessageComposer(playerEntity.getId(), "Has modificado el furni con id " + itemId + " para actualizar los cambios recoge el item y lo pones nuevamente", ChatEmotion.NONE, 34));
                    ItemManager.getInstance().loadItemDefinitions();
                } catch (Exception e) {
                    client.getPlayer().getSession().send(new TalkMessageComposer(client.getPlayer().getEntity().getId(), "Por favor introduce un valor numérico valido válido...", ChatEmotion.NONE, 34));
                    return;
                }
                break;

            case "canstack":
                if (option.equals("1") || option.equals("0")) {
                    FurniFixDao.canStack(option, itemId);
                    client.getPlayer().getSession().send(new TalkMessageComposer(playerEntity.getId(), "Has modificado el furni con id " + itemId + " para actualizar los cambios recoge el item y lo pones nuevamente", ChatEmotion.NONE, 34));
                    ItemManager.getInstance().loadItemDefinitions();
                } else {
                    client.getPlayer().getSession().send(new TalkMessageComposer(client.getPlayer().getEntity().getId(), "Por favor introduce solo un valor entre 1 o 0, no otro.", ChatEmotion.NONE, 34));
                    return;
                }
                break;
        }
    }

    @Override
    public String getPermission() {
        return "furnifix_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.furnifix.description");
    }
}