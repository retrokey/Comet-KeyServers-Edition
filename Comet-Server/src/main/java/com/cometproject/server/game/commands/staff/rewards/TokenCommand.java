package com.cometproject.server.game.commands.staff.rewards;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.user.purse.UpdateActivityPointsMessageComposer;
import com.cometproject.server.network.sessions.Session;
import org.apache.commons.lang.StringUtils;

public class TokenCommand extends ChatCommand {

    @Override
    public void execute(Session client, String[] params) {
        if (params.length < 2)
            return;

        String username = params[0];
        String amount = params[1];

        Session session = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);

        if (session == null || params[0] == null || params[1] == null) {
            return;
        }

        if (!StringUtils.isNumeric(params[1]))
            return;

        if(amount.contains("-")){
            int retire = Integer.parseInt(params[1].split("-")[1]);

            if(session.getPlayer().getData().getBlackMoney() <= 0)
                return;

            session.getPlayer().getData().decreaseBlackMoney(retire);
            session.getPlayer().getData().save();
            session.send(new UpdateActivityPointsMessageComposer(session.getPlayer().getData().getBlackMoney(), -retire, 105));

            session.getPlayer().sendBubble(Locale.getOrDefault("command.token.image", "token"), Locale.getOrDefault("command.token.successmessage", "Te acaban de retirar %amount% " + Locale.getOrDefault("currency_tokens_name", "Tokens") + ".").replace("%amount%", amount));
            return;
        }

        int g = Integer.parseInt(params[1]);
        session.getPlayer().getData().increaseBlackMoney(g);
        session.send(new UpdateActivityPointsMessageComposer(session.getPlayer().getData().getBlackMoney(), g, 105));
        session.getPlayer().getData().save();

        session.getPlayer().sendBubble(Locale.getOrDefault("command.token.image", "game"),
        Locale.getOrDefault("command.seasonal.successmessage", "Has recibido %amount% " +
        Locale.getOrDefault("currency_tokens_name", "Tokens") + ".").replace("%amount%", amount));

        client.getPlayer().sendBubble(Locale.getOrDefault("command.seasonal.image", "pumpkin"),
        Locale.getOrDefault("command.seasonal.giver.successmessage", "Le has dado %amount% Tokens a %username%.").replace("%amount%", amount).replace("%username%", session.getPlayer().getData().getUsername()));
    }

    @Override
    public String getPermission() {
        return "token_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.parameter.username" + " " + "command.parameter.amount", "%username% %amount%");
    }

    @Override
    public String getDescription() {
        return Locale.getOrDefault("command.token.description", "Da Tokens al usuario indicado.");
    }
}
