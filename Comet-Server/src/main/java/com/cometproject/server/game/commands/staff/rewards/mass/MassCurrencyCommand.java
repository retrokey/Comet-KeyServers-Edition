package com.cometproject.server.game.commands.staff.rewards.mass;

import com.cometproject.api.networking.sessions.ISession;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.sessions.Session;
import org.apache.commons.lang.StringUtils;


public abstract class MassCurrencyCommand extends ChatCommand {

    private String logDesc = "";

    @Override
    public void execute(Session client, String[] params) {
        if (params.length < 1 || params[0].isEmpty() || !StringUtils.isNumeric(params[0]))
            return;

        final int amount = Integer.parseInt(params[0]);

        for (ISession session : NetworkManager.getInstance().getSessions().getSessions().values()) {
            try {
                if(session == null || session.getPlayer() == null)
                    continue;

                String currencyType = "coins";
                String image = "koins";

                if (this instanceof MassCoinsCommand) {
                    session.getPlayer().getData().increaseCredits(amount);
                } else if (this instanceof MassDucketsCommand) {
                    session.getPlayer().getData().increaseActivityPoints(amount);
                    currencyType = "activity.points";
                    image = "duckets";
                    this.logDesc = "El staff %s ha dado a todo el hotel %n Duckets."
                            .replace("%n", Integer.toString(amount)).replace("%s", client.getPlayer().getData().getUsername());

                } else if (this instanceof MassPointsCommand) {
                    session.getPlayer().getData().increaseVipPoints(amount);
                    currencyType = "vip.points";
                    image = "diamonds";
                    this.logDesc = "El staff %s ha dado a todo el hotel %n Diamantes."
                            .replace("%n", Integer.toString(amount)).replace("%s", client.getPlayer().getData().getUsername());

                } else if (this instanceof MassSeasonalCommand) {
                    session.getPlayer().getData().increaseSeasonalPoints(amount);
                    currencyType = "seasonal";
                    image = "game";
                    this.logDesc = "El staff %s ha dado a todo el hotel %n Puntos de Temporada."
                            .replace("%n", Integer.toString(amount)).replace("%s", client.getPlayer().getData().getUsername());
                } else if (this instanceof MassTokensCommand) {
                    session.getPlayer().getData().increaseBlackMoney(amount);
                    currencyType = "tokens";
                    image = "tokens";
                    this.logDesc = "El staff %s ha dado a todo el hotel %n Tokens."
                            .replace("%n", Integer.toString(amount)).replace("%s", client.getPlayer().getData().getUsername());
                }

                session.send(new NotificationMessageComposer(
                        image,
                        Locale.get("command.points.successmessage").replace("%amount%", String.valueOf(amount))
                                .replace("%type%", Locale.get(currencyType + ".name") + ".")
                ));

                session.getPlayer().getData().save();
                session.getPlayer().sendBalance();
            } catch (Exception ignored) {

            }
        }
    }

    @Override
    public boolean isAsync() {
        return true;
    }

    @Override
    public String getLoggableDescription(){
        return this.logDesc;
    }

    @Override
    public boolean Loggable(){
        return true;
    }
}
