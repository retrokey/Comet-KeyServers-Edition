package com.cometproject.server.game.commands.user;

import com.cometproject.api.config.CometSettings;
import com.cometproject.server.api.DiscordClient;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.messages.outgoing.notification.MassEventMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.sessions.Session;

import java.io.IOException;

public class HelpCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        int time = (int) Comet.getTime();
        int timeSinceLastUpdate = time - client.getPlayer().getLastCFH();

        if(timeSinceLastUpdate >= CometSettings.callForHelpCooldown){
            client.send(new MassEventMessageComposer("help/tour"));
            client.getPlayer().setLastCFH(time);

            String messageToClient = "El usuario **" + client.getPlayer().getEntity().getUsername() + "** está solicitando ayuda de un Staff. `:Follow " + client.getPlayer().getEntity().getUsername() + "` para seguir a este jugador.";

            try {
                DiscordClient dcClient = new DiscordClient("https://discord.com/api/webhooks/930300436454457424/KZfuyxHJWLNHXky2myhlwvEqQ8o3S9PX5ox8y1HQeYipp8Ki-7TjdxTi1Fkd4AYe_XAR");
                dcClient.setAvatarUrl("https://i.imgur.com/bA7O9aA.png");
                dcClient.setContent(messageToClient + " @everyone");
                dcClient.setUsername("Soporte a Usuario");
                dcClient.execute();

                client.send(new WhisperMessageComposer(client.getPlayer().getEntity().getId(), "Acabas de enviar un Ticket a los Soportes del Hotel, pronto te atenderan!", 2));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        client.send(new NotificationMessageComposer("ambassador", "Todavía debes esperar " + (300 - timeSinceLastUpdate) + " segundos para volver a pedir ayuda.", ""));
    }

    @Override
    public String getPermission() {
        return "help_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.help.description");
    }
}
