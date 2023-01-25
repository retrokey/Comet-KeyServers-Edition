package com.cometproject.server.game.polls.types.questions;

public class InfobusQuestion extends MultipleChoiceQuestion {
    public InfobusQuestion(String question, String questionData) {
        super(question, questionData);
    }

    @Override
    public int getType() {
        return 4;
    }
}