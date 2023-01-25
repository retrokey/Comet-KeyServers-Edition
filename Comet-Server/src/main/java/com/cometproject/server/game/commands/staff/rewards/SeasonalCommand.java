package com.cometproject.server.game.commands.staff.rewards;
import com.cometproject.api.config.CometSettings;
import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.api.game.quests.QuestType;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.purse.UpdateActivityPointsMessageComposer;
import com.cometproject.server.network.sessions.Session;
import org.apache.commons.lang.StringUtils;


public class SeasonalCommand extends ChatCommand {

    @Override
    public void execute(Session client, String[] params) {
        if (params.length < 2)
            return;

        boolean currencySystem = CometSettings.currencySystemEnabled;
        String username = params[0];
        String amount = params[1];

        Session session = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);

        if (session == null || params[0] == null || params[1] == null) {
            return;
        }

        if(amount.contains("-")){
            int retire = Integer.parseInt(params[1].split("-")[1]);

            if(session.getPlayer().getData().getSeasonalPoints() <= 0)
                return;

            session.getPlayer().getData().decreaseSeasonalPoints(retire);

            session.send(new NotificationMessageComposer(
                    Locale.getOrDefault("command.seasonal.image", "pumpkin"),
                    Locale.getOrDefault("command.seasonal.successmessage", "Te acaban de retirar %amount% " + Locale.getOrDefault("currency_name", "calabazas") + ".").replace("%amount%", String.valueOf(amount))
            ));

            session.getPlayer().getData().save();
            session.send(session.getPlayer().composeCurrenciesBalance());
            session.getPlayer().sendBalance();
            return;
        }

        if (!StringUtils.isNumeric(params[1]))
            return;

        int g = Integer.parseInt(params[1]);

        if(g > CometSettings.maxSeasonalRewardPoints) {
            return;
        }

        session.getPlayer().getData().increaseSeasonalPoints(g);
        session.getPlayer().getData().increaseAchievementPoints(CometSettings.seasonalRewardActivityPoints);
        session.getPlayer().getData().save();
        session.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_95, 1);

        session.send(new NotificationMessageComposer(
                Locale.getOrDefault("command.seasonal.image", "game"),
                Locale.getOrDefault("command.seasonal.successmessage", "Has recibido %amount% " + Locale.getOrDefault("currency_name", "Puntos de Juego") + ".").replace("%amount%", String.valueOf(amount))
        ));

        session.send(new UpdateActivityPointsMessageComposer(session.getPlayer().getData().getSeasonalPoints(), g, 103));

        client.send(new NotificationMessageComposer(
                Locale.getOrDefault("command.seasonal.image", "pumpkin"),
                Locale.getOrDefault("command.seasonal.giver.successmessage", "Le has dado %amount% Puntos de Juego a %username%.").replace("%amount%", String.valueOf(amount)).replace("%username%", session.getPlayer().getData().getUsername())));

        session.getPlayer().getQuests().progressQuest(QuestType.EAS20_4, 1);

        if(!client.getPlayer().getData().getUsername().equals(username)){
            NetworkManager.getInstance().getSessions().broadcast(new NotificationMessageComposer("/usr/look/" + username, Locale.getOrDefault("event_winner_notification", "%winner% has just won an event!").replace("%winner%", username)));
        }
    }

    @Override
    public String getPermission() {
        return "seasonal_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.parameter.username" + " " + "command.parameter.amount", "%username% %amount%");
    }

    @Override
    public String getDescription() {
        return Locale.get("command.seasonal.description");
    }
}
