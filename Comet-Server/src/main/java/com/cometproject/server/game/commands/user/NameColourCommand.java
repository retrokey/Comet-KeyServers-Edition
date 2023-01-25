package com.cometproject.server.game.commands.user;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.messages.outgoing.notification.MotdNotificationMessageComposer;
import com.cometproject.server.network.sessions.Session;

public class NameColourCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {

        boolean isVip = client.getPlayer().getData().getRank() >= 2;

        if (params.length != 1) {
            StringBuilder colorInfo = new StringBuilder();
            colorInfo.append("Colores disponibles: \n\n");
            colorInfo.append("<font color='#2a68c9'>Azul</font>\n");
            colorInfo.append("<font color='#41d1ca'>Cyan</font>\n");
            colorInfo.append("<font color='#d11717'>Rojo</font>\n");
            colorInfo.append("<font color='#7a0d0d'>Vino</font>\n");
            colorInfo.append("<font color='#e6c522'>Amarillo</font>\n");
            colorInfo.append("<font color='#000'>Negro</font>\n");

            if (isVip){
                colorInfo.append("Colores VIPS Disponibles: \n\n");
                colorInfo.append("<font color='#de3eab'>Rosa</font>\n");
                colorInfo.append("<font color='#fff'>Blanco</font>\n");
                colorInfo.append("<font color='#9c19b3'>Violeta</font>\n");
                colorInfo.append("<font color='#e69122'>Naranja</font>\n");
                colorInfo.append("<font color='#19b335'>Verde</font>\n");
            }

            client.send(new MotdNotificationMessageComposer(colorInfo.toString()));
            return;
        }

        if(params[0].toLowerCase().equals("azul")){
            client.getPlayer().getData().setNameColour("2a68c9");
            client.getPlayer().getData().save();
            sendNotif("Color de nombre actualizado.", client);
        } else if(params[0].toLowerCase().equals("cyan")){
            client.getPlayer().getData().setNameColour("41d1ca");
            client.getPlayer().getData().save();
            sendNotif("Color de nombre actualizado.", client);
        } else if(params[0].toLowerCase().equals("rosa") && isVip){
            client.getPlayer().getData().setNameColour("de3eab");
            client.getPlayer().getData().save();
            sendNotif("Color de nombre actualizado.", client);
        } else if(params[0].toLowerCase().equals("rojo")){
            client.getPlayer().getData().setNameColour("d11717");
            client.getPlayer().getData().save();
            sendNotif("Color de nombre actualizado.", client);
        } else if(params[0].toLowerCase().equals("vino")){
            client.getPlayer().getData().setNameColour("7a0d0d");
            client.getPlayer().getData().save();
            sendNotif("Color de nombre actualizado.", client);
        } else if(params[0].toLowerCase().equals("amarillo")){
            client.getPlayer().getData().setNameColour("e6c522");
            client.getPlayer().getData().save();
            sendNotif("Color de nombre actualizado.", client);
        } else if(params[0].toLowerCase().equals("naranja") && isVip){
            client.getPlayer().getData().setNameColour("e69122");
            client.getPlayer().getData().save();
            sendNotif("Color de nombre actualizado.", client);
        } else if(params[0].toLowerCase().equals("verde") && isVip){
            client.getPlayer().getData().setNameColour("19b335");
            client.getPlayer().getData().save();
            sendNotif("Color de nombre actualizado.", client);
        } else if(params[0].toLowerCase().equals("violeta") && isVip){
            client.getPlayer().getData().setNameColour("9c19b3");
            client.getPlayer().getData().save();
            sendNotif("Color de nombre actualizado.", client);
        } else if(params[0].toLowerCase().equals("negro")){
            client.getPlayer().getData().setNameColour("000");
            client.getPlayer().getData().save();
            sendNotif("Color de nombre actualizado.", client);
        } else if(params[0].toLowerCase().equals("blanco") && isVip){
            client.getPlayer().getData().setNameColour("fff");
            client.getPlayer().getData().save();
            sendNotif("Color de nombre actualizado.", client);
        } else {
            sendNotif("Color de nombre inválido, usa :color para ver la lista.", client);
        }
    }

    @Override
    public String getPermission() {
        return "namecolour_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.namecolour.param", "%colour%");
    }

    @Override
    public String getDescription() {
        return Locale.get("command.namecolour.description");
    }
}
