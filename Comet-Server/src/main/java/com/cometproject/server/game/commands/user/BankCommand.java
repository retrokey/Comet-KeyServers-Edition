package com.cometproject.server.game.commands.user;

import com.cometproject.api.config.CometSettings;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.players.data.PlayerData;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.AlertMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.nuxs.NuxGiftEmailViewMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.engine.RoomForwardMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.storage.queries.player.PlayerDao;


public class BankCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if(params.length != 3){
            BankCommand.sendNotif("Uso del comando incorrecto. :pay usuario cantidad moneda.", client);
            return;
        }

        if(((PlayerData)client.getPlayer().getData()).viewPoints <= 2){
            BankCommand.sendNotif("Debes de tener mas de dos horas jugadas para usar el pay", client);
            return;
        }

        try{
            int cantidad = Integer.parseInt(params[1]);
            Session kissedSession = NetworkManager.getInstance().getSessions().getByPlayerUsername(params[0]);
            if(kissedSession == null){
                BankCommand.sendNotif("Esta moneda no existe.", client);
                return;
            }
            if (kissedSession == null) {
                BankCommand.sendNotif(Locale.getOrDefault("command.user.offline", "\u00a1El usuario no est\u00e1 en l\u00ednea!"), client);
                return;
            }
            if (kissedSession.getPlayer().getEntity() == null) {
                BankCommand.sendNotif(Locale.getOrDefault("command.user.notinroom", "El usuario no est\u00e1 en ninguna sala."), client);
                return;
            }
            if (kissedSession.getPlayer().getData().getUsername().equals(client.getPlayer().getData().getUsername())) {
                BankCommand.sendNotif(Locale.getOrDefault("command.puke.himself", "No puedes darte amor a ti mismo."), client);
                return;
            }

            if(params[2].toLowerCase().contains("cometas")){
                if(cantidad < client.getPlayer().getData().getVipPoints()){
                    client.getPlayer().getData().decreaseVipPoints(cantidad);
                    kissedSession.getPlayer().getData().increaseVipPoints(cantidad);
                    client.getPlayer().sendBalance();
                    kissedSession.getPlayer().sendBalance();

                    BankCommand.sendNotif("Le has enviado " + cantidad + " cometas a " + params[0], client);
                    BankCommand.sendNotif(client.getPlayer().getEntity().getUsername() + " te ha enviado " + cantidad + " cometas.", kissedSession);
                }
            }
            else if(params[2].toLowerCase().contains("creditos")){
                if(cantidad < client.getPlayer().getData().getCredits()){
                    client.getPlayer().getData().decreaseCredits(cantidad);
                    kissedSession.getPlayer().getData().increaseCredits(cantidad);
                    client.getPlayer().sendBalance();
                    kissedSession.getPlayer().sendBalance();

                    BankCommand.sendNotif("Le has enviado " + cantidad + " creditos a " + params[0], client);
                    BankCommand.sendNotif(client.getPlayer().getEntity().getUsername() + " te ha enviado " + cantidad + " creditos.", kissedSession);
                }
            }
            else if(params[2].toLowerCase().contains("asteroides")){
                if(cantidad < client.getPlayer().getData().getActivityPoints()){
                    client.getPlayer().getData().decreaseActivityPoints(cantidad);
                    kissedSession.getPlayer().getData().increaseActivityPoints(cantidad);
                    client.getPlayer().sendBalance();
                    kissedSession.getPlayer().sendBalance();

                    BankCommand.sendNotif("Le has enviado " + cantidad + " asteroides a " + params[0], client);
                    BankCommand.sendNotif(client.getPlayer().getEntity().getUsername() + " te ha enviado " + cantidad + " asteroides.", kissedSession);
                }
            }
            else BankCommand.sendNotif("Esta moneda no existe.", client);
        }
        catch (Exception ex){
            BankCommand.sendNotif("Cantidad incorrecta.", client);
        }
    }

    @Override
    public String getPermission() {
        return "bank_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.parameter.username", "%username%");
    }

    @Override
    public String getDescription() {
        return Locale.get("command.bank.description");
    }
}
