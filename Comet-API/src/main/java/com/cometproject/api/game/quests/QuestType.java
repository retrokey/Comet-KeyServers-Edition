package com.cometproject.api.game.quests;

public enum QuestType {
    FURNI_MOVE(0, "MOVE_ITEM"),
    FURNI_ROTATE(1, "ROTATE_ITEM"),
    FURNI_PLACE(2, "PLACE_ITEM"),
    FURNI_PICK(3, "PICKUP_ITEM"),
    FURNI_SWITCH(4, "SWITCH_ITEM_STATE"),
    FURNI_STACK(5, "STACK_ITEM"),
    FURNI_DECORATION_FLOOR(6, "PLACE_FLOOR"),
    FURNI_DECORATION_WALL(7, "PLACE_WALLPAPER"),
    SOCIAL_VISIT(8, "ENTER_OTHERS_ROOM"),
    SOCIAL_CHAT(9, "CHAT_WITH_SOMEONE"),
    SOCIAL_FRIEND(10, "REQUEST_FRIEND"),
    SOCIAL_RESPECT(11, "GIVE_RESPECT"),
    SOCIAL_DANCE(12, "DANCE"),
    SOCIAL_WAVE(13, "WAVE"),
    PROFILE_CHANGE_LOOK(14, "CHANGE_FIGURE"),
    PROFILE_CHANGE_MOTTO(15, "CHANGE_MOTTO"),
    PROFILE_BADGE(16, "WEAR_BADGE"),
    NOTHING(17, "FIND_STUFF"),
    WEEN_BITE_PLAYER(18, "BITE_PLAYER"),
    WEEN_THROW_CANDIES(19, "THROW_CANDIES"),
    WEEN_THREAT_PLAYER(20, "THREAT_PLAYER"),
    WEEN_PUKE_PLAYER(21, "PUKE_PLAYER"),
    WEEN_BURN_PLAYER(22, "BURN_PLAYER"),
    WEEN_CHECK_AKINATOR(23, "CHECK_AKINATOR"),
    WEEN_CHECK_LAB(24, "CHECK_LAB"),
    WEEN_FINAL(25, "WEEN_FINAL"),

    VIKINGS_ASGARD(26, "ASGARD"),
    VIKINGS_BURP(27, "BURP"),
    VIKINGS_SLAY(28, "SLAY"),

    BATTLE_1(29, "SURVIVAL_DESKTOP"),
    BATTLE_2(30, "SURVIVAL_PAPERS"),
    BATTLE_3(31, "SURVIVAL_ALARM"),

    LAB_1(32, "LAB_HINT"),
    LAB_2(33, "LAB_DOG"),
    LAB_3(34, "LAB_DOCUMENTS"),
    LAB_4(35, "LAB_CHEMICAL"),
    LAB_5(36, "LAB_OPERATION"),
    LAB_6(37, "LAB_BOSS"),

    BATTLE_4(38, "SURVIVAL_ISLAND"),
    BATTLE_5(39, "SURVIVAL_SAVE"),
    BATTLE_6(40, "SURVIVAL_MINES"),
    BATTLE_7(41, "SURVIVAL_TUTORIAL"),

    EXPLORE_1(42, "EXPLORE_DUEL"),
    EXPLORE_2(43, "EXPLORE_PUKE"),
    EXPLORE_3(44, "EXPLORE_RPS"),
    EXPLORE_4(45, "EXPLORE_EVENT"),
    EXPLORE_5(46, "EXPLORE_BAHIA"),
    EXPLORE_6(47, "EXPLORE_WEED"),
    EXPLORE_7(48, "EXPLORE_CASINO"),
    EXPLORE_8(49, "EXPLORE_BUSCAMINAS"),

    VAL20_1(50, "VAL20_Q1"),
    VAL20_2(51, "VAL20_Q2"),
    VAL20_3(52, "VAL20_Q3"),
    VAL20_4(53, "VAL20_Q4"),
    VAL20_5(54, "VAL20_Q5"),
    EXPLORE_FIND_ITEM(55, "VAL20_Q6"),

    EAS20_1(56, "EAS20_Q1"),
    EAS20_2(57, "EAS20_Q2"),
    EAS20_3(58, "EAS20_Q3"),
    EAS20_4(59, "EAS20_Q4"),
    EAS20_5(60, "EAS20_Q5"),
    EAS20_6(61, "EAS20_Q6"),
    EAS20_7(62, "EAS20_Q7"),

    ROLEPLAY_GENERAL_1(63, "RP_G_1"),
    ROLEPLAY_GENERAL_2(64, "RP_G_2"),
    ROLEPLAY_GENERAL_3(65, "RP_G_3"),
    ROLEPLAY_GENERAL_4(66, "RP_G_4"),
    ROLEPLAY_GENERAL_5(67, "RP_G_5"),
    ROLEPLAY_GENERAL_6(68, "RP_G_6"),
    ROLEPLAY_GENERAL_7(69, "RP_G_7"),
    ROLEPLAY_GENERAL_8(70, "RP_G_8"),
    ROLEPLAY_GENERAL_9(71, "RP_G_9"),

    XMAS20_1(72, "XMAS20_1"),
    XMAS20_2(73, "XMAS20_2"),
    XMAS20_3(74, "XMAS20_3"),
    XMAS20_4(75, "XMAS20_4"),
    XMAS20_5(76, "XMAS20_5"),
    XMAS20_6(77, "XMAS20_6"),
    XMAS20_7(78, "XMAS20_7"),

    VAL21_1(79, "VAL21_1"),
    VAL21_2(80, "VAL21_2"),
    VAL21_3(81, "VAL21_3"),
    VAL21_4(82, "VAL21_4"),
    VAL21_5(83, "VAL21_5"),
    VAL21_6(84, "VAL21_6"),
    VAL21_7(85, "VAL21_7"),
    GENERIC(0, "NONE");

    private int questType;
    private String action;

    QuestType(int type, String action) {
        this.questType = type;
        this.action = action;
    }

    public int getQuestType() {
        return this.questType;
    }

    public String getAction() {
        return this.action;
    }

    public static QuestType getById(int id) {
        for (QuestType type : values()) {
            if (type.getQuestType() == id) {
                return type;
            }
        }

        return QuestType.EXPLORE_FIND_ITEM; // default
    }
}
