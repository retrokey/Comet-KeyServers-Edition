package com.cometproject.server.network.messages.incoming.user.wardrobe;

import com.cometproject.api.config.CometSettings;
import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.api.game.quests.QuestType;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.achievements.BattlePassGlobals;
import com.cometproject.server.game.achievements.types.BattlePassMission;
import com.cometproject.server.game.achievements.types.BattlePassMissionEnums;
import com.cometproject.server.game.utilities.validator.PlayerFigureValidator;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.notification.AlertMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.details.AvatarAspectUpdateMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;


public class ChangeLooksMessageEvent implements Event {
    public void handle(Session client, MessageEvent msg) {
        String gender = msg.readString();
        String figure = msg.readString();

        if(client.getPlayer().getData() == null)
            return;

        if (figure.isEmpty()|| gender.isEmpty()) return;

        if (PlayerFigureValidator.CheckFilterChar(figure)) {
            figure = "lg-3023-1335.hr-828-45.sh-295-1332.hd-180-4.ea-3168-89.ca-1813-62.ch-235-1332";
        }

        if (figure.length() < 18) {
            client.send(new AlertMessageComposer(Locale.get("game.figure.invalid")));
            return;
        }

        if (!PlayerFigureValidator.isValidFigureCode(figure, gender.toLowerCase())) {
            client.send(new AlertMessageComposer(Locale.getOrDefault("game.figure.invalid", "That figure is invalid!")));
            return;
        }

        if (!gender.toLowerCase().equals("m") && !gender.toLowerCase().equals("f")) {
            return;
        }

        int timeSinceLastUpdate = ((int) Comet.getTime()) - client.getPlayer().getLastFigureUpdate();

        if (timeSinceLastUpdate >= CometSettings.playerChangeFigureCooldown) {
            client.getPlayer().getData().setGender(gender);
            client.getPlayer().getData().setFigure(figure);
            client.getPlayer().getData().save();

            client.getPlayer().poof();
            client.getPlayer().setLastFigureUpdate((int) Comet.getTime());
        }

        if(figure.contains("ha-1008")){
            client.getPlayer().getQuests().progressQuest(QuestType.EAS20_1, 1);
        }

        client.getPlayer().getAchievements().progressAchievement(AchievementType.ACH_3, 1);
        client.getPlayer().getQuests().progressQuest(QuestType.PROFILE_CHANGE_LOOK);
        client.send(new AvatarAspectUpdateMessageComposer(figure, gender));

        BattlePassMission ms = BattlePassGlobals.battlePassMissions.stream().filter(x -> x.type == BattlePassMissionEnums.MissionType.UPDATELOOK).findAny().orElse(null);
        if(ms != null){
            if(client.getPlayer().getData().battlePass != null) client.getPlayer().getData().battlePass.addExperiencePoint(ms.id);
        }
    }
}
