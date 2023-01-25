package com.cometproject.server.game.commands.staff.bundles;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.sessions.Session;

public class RubyCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if (params.length < 2) return;


        final String username = params[0];

        final int type = Integer.parseInt(params[1]);
        int amount = 0;

        switch(type){
            case 1:
                amount = 9;
                break;
            case 2:
                amount = 19;
                break;
            case 3:
                amount = 40;
                break;
        }

        Session session = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);

        if(session != null && session.getPlayer() != null) {
            session.send(new NotificationMessageComposer("ruby_bundle", Locale.getOrDefault("ruby.acquired", "Acabas de recibir el lote de Rubíes %type%, disfruta de tus %amount% Rubíes. Haz click aquí para ir a la tienda.").replace("%type%", type + "").replace("%amount%", amount + ""), "catalog/open/club_buy"));
            session.getPlayer().getData().increaseVipPoints(amount);
            client.getPlayer().sendBalance();
            client.getPlayer().getData().save();
        }

    }

    @Override
    public String getPermission() {
        return "vipbundle_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.parameter.username", "%username% %type%");
    }

    @Override
    public String getDescription() {
        return Locale.getOrDefault("command.vipbundle.description", "Da un pack de Rubíes al usuario seleccionado.");
    }
}
