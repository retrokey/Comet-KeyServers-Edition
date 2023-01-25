package com.cometproject.server.game.quests.types;

public class QuestDialog {
    private final int id;
    private final int botId;
    private final int questId;
    private final int step;
    private final String keyword;
    private final String answer;

    public QuestDialog(int id, int botId, int questId, int step, String keyword, String answer) {
        this.id = id;
        this.botId = botId;
        this.questId = questId;
        this.step = step;
        this.keyword = keyword;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public int getBotId() {
        return botId;
    }

    public int getQuestId() {
        return questId;
    }

    public int getStep() {
        return step;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getAnswer() {
        return answer;
    }
}
