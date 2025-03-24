package org.example;


import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter


public class Question {
    private String nameQuestion;
    private List<String> answers;
    private int numberAnswer;

    public Question(String nameQuestion, List<String> answer, int numberAnswer) {
        this.nameQuestion = nameQuestion;
        this.answers = answer;
        this.numberAnswer = numberAnswer;
    }

    public String getNameQuestion() {
        return nameQuestion;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getNumberAnswer() {
        return numberAnswer;
    }

    public String getRightAnswer() {
        return answers.get(numberAnswer);
    }
}