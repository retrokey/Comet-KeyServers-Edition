package com.cometproject.server.game.commands.staff.bundles;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.sessions.Session;

public class VIPCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if (params.length != 1) return;

        final String username = params[0];
        Session session = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);

        if(session != null && session.getPlayer() != null) {
            session.send(new NotificationMessageComposer("vip_bundle", Locale.getOrDefault("vip.acquired", "Acabas de recibir el lote VIP, disfruta de tus 180 Rubíes para adquirir la subscripción. Haz click aquí para ir a la tienda."), "catalog/open/club_buy"));
            session.getPlayer().getData().increaseVipPoints(240);
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
        return Locale.getOrDefault("command.parameter.username", "%username%");
    }

    @Override
    public String getDescription() {
        return Locale.getOrDefault("command.vipbundle.description", "Da un pack de VIP al usuario seleccionado.");
    }
}
