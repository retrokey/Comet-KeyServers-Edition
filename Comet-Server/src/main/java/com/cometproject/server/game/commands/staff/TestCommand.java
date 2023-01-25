package com.cometproject.server.game.commands.staff;

import com.cometproject.server.boot.Comet;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.messages.outgoing.room.alerts.ConfirmableAlertMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.sessions.Session;

import java.util.Random;

public class TestCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        /*client.send(new ConfirmableAlertMessageComposer(client.getPlayer().getData().getUsername(), 1, false));
        client.getPlayer().setShadow(1);*/
        //client.send(new SeasonalCalendarMessageComposer(Integer.parseInt(params[0])));
        /*client.send(new UpdateActivityPointsMessageComposer(6000, 6000, 0));
        client.send(new ConfirmableAlertMessageComposer(client.getPlayer().getData().getUsername(), 1, false));*/
        client.getPlayer().getData().increaseAchievementPoints(100);

        if(params[0].equals("body")){
            client.getPlayer().getEntity().setBodyRotating(true);
        } else {
            client.getPlayer().getEntity().setHeadRotating(true);
        }

        /*if(client.getPlayer().getRP().hasAttribute("death")){
            client.getPlayer().sendBubble("", "Tracked attribute");
            client.getPlayer().getRP().removeAttribute("death");
        } else {
            client.getPlayer().getRP().setAttribute("death", true);
        }*/
    }

    @Override
    public String getPermission() {
        return "test_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.test.description");
    }

    private int result(){
        Random result = new Random();
        result.setSeed(Comet.getTime());

        return result.nextInt(37);
    }
}
