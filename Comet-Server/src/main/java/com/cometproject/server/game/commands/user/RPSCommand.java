package com.cometproject.server.game.commands.user;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.nuxs.NuxGiftSelectionViewMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.sessions.Session;
import org.apache.commons.lang.StringUtils;

public class RPSCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if (params.length == 2 && !StringUtils.isNumeric(params[1]))
            return;

        String username = params[0];
        String info = "<b>%u</b> te ha retado a una partida de Piedra, Papel o Tijera, escribe <b>:rps %u</b> para aceptar el desafío.";

        if(username.equals("reset")){
            Session enemy = NetworkManager.getInstance().getSessions().getByPlayerUsername(client.getPlayer().getRPSRival());

            if(enemy != null){
                enemy.getPlayer().resetRPS();
                enemy.send(new TalkMessageComposer(-1, client.getPlayer().getData().getUsername() + " acaba de cancelar la partida de Piedra, Papel o Tijera.", ChatEmotion.SHOCKED, 1));
            }

            client.getPlayer().resetRPS();
            client.send(new TalkMessageComposer(-1, "Acabas de resetear tu rival de Piedra, Papel o Tijera.", ChatEmotion.SHOCKED, 1));
            return;
        }

        Session session = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);

        if (session == null) {
            return;
        }

        int amount = 0;

        if(params.length == 3) {
            amount = 0;//Integer.parseInt(params[1]);
            info += " a <b>%a</b> Koins";
        }

        if(client.getPlayer().getData().getCredits() < amount || session.getPlayer().getData().getCredits() < amount){
            client.send(new WhisperMessageComposer(-1, "No puedes apostar " + amount + " Koins en esta partida ya que uno de los integrantes no dispone de esa cantidad.",18));
            return;
        }

        session.send(new TalkMessageComposer(-1, Locale.getOrDefault("rps.game.toggle",info + ".").replace("%u", client.getPlayer().getEntity().getUsername()).replace("%a", amount + ""), ChatEmotion.SHOCKED, 1));

        client.getPlayer().setRPSRival(username);
        client.getPlayer().setRPSamount(amount);

        if(client.getPlayer().getRPSRival().contains(session.getPlayer().getData().getUsername()) && session.getPlayer().getRPSRival().contains(client.getPlayer().getData().getUsername()) && session.getPlayer().getRPSamount() == client.getPlayer().getRPSamount()) {
            client.send(new NuxGiftSelectionViewMessageComposer(4));
            session.send(new NuxGiftSelectionViewMessageComposer(4));
        }
    }

    @Override
    public String getPermission() {
        return "rps_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.parameter.username" + " " + "command.parameter.amount", "%username% %amount%");
    }

    @Override
    public String getDescription() {
        return Locale.get("command.rps.description");
    }
}
