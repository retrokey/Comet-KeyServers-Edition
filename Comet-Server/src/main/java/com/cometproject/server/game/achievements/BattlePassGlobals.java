package com.cometproject.server.game.achievements;

import com.cometproject.server.game.achievements.types.BattlePassMission;
import com.cometproject.server.game.achievements.types.BattlePassMissionEnums;
import com.cometproject.server.game.achievements.types.BattlePassRewardEnum;

import java.util.ArrayList;

public class BattlePassGlobals {
    public static ArrayList<BattlePassMission> battlePassMissions = new ArrayList<BattlePassMission>(){{
        add(new BattlePassMission(1, "Visita 10 salas del hotel", 10, BattlePassMissionEnums.MissionType.VISITROOM, BattlePassRewardEnum.RewardType.DIAMONDS, "30", "https://cdn.discordapp.com/attachments/882679902162288651/1007356969058840697/1er-nivel.png"));
        add(new BattlePassMission(2, "Actualiza tu mision 5 veces", 5, BattlePassMissionEnums.MissionType.CHANGEMISSION, BattlePassRewardEnum.RewardType.DIAMONDS, "35", "https://cdn.discordapp.com/attachments/882679902162288651/1007356969398575174/2do-nivel.png"));
        add(new BattlePassMission(3, "Crea tu sala y coloca 20 furnis", 20, BattlePassMissionEnums.MissionType.PLACEFURNI, BattlePassRewardEnum.RewardType.BADGE, "GLXF2", "https://cdn.discordapp.com/attachments/882679902162288651/1007356969759293531/3er-nivel.png"));
        add(new BattlePassMission(4, "Respeta a 10 personas", 10, BattlePassMissionEnums.MissionType.RESPECTUSER, BattlePassRewardEnum.RewardType.DIAMONDS, "50", "https://cdn.discordapp.com/attachments/882679902162288651/1007356970493280437/4to-nivel.png"));
        add(new BattlePassMission(5, "Juega al menos 5 partidas de la rouleta normal", 5, BattlePassMissionEnums.MissionType.PLAYCASINO, BattlePassRewardEnum.RewardType.DIAMONDS, "65", "https://cdn.discordapp.com/attachments/882679902162288651/1007356970803662979/5to-nivel.png"));
        add(new BattlePassMission(6, "Se respetado 9 veces", 9, BattlePassMissionEnums.MissionType.GETRESPECTS, BattlePassRewardEnum.RewardType.BADGE, "GLXF4", "https://cdn.discordapp.com/attachments/882679902162288651/1007356971139223703/6to-nivel.png"));
        add(new BattlePassMission(7, "Crea 6 salas nuevas", 6, BattlePassMissionEnums.MissionType.CREATEROOM, BattlePassRewardEnum.RewardType.DIAMONDS, "75", "https://cdn.discordapp.com/attachments/882679902162288651/1007356971466371082/7mo-nivel.png"));
        add(new BattlePassMission(8, "Besa 30 veces", 30, BattlePassMissionEnums.MissionType.KISSPLAYER, BattlePassRewardEnum.RewardType.DIAMONDS, "90", "https://cdn.discordapp.com/attachments/882679902162288651/1007356971797717042/8vo-nivel.png"));
        add(new BattlePassMission(9, "Realiza con exito al menos 8 tradeos", 8, BattlePassMissionEnums.MissionType.TRADE, BattlePassRewardEnum.RewardType.BADGE, "GLXF1", "https://cdn.discordapp.com/attachments/882679902162288651/1007356972116496456/9vo-nivel.png"));
        add(new BattlePassMission(10, "Obten 6 respetos", 6, BattlePassMissionEnums.MissionType.GETRESPECTS, BattlePassRewardEnum.RewardType.RARE, "486734677", "https://cdn.discordapp.com/attachments/882679902162288651/1007356972426854430/10mo-nivel.png"));

    }};
}
