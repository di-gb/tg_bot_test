package org.example;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionsListTest {
    private static final String NAME_QUESTION = "Какой метод используется для преобразования строки в верхний регистр?";

    QuestionsList questionsList = new QuestionsList();

    @Test
    public void getQuestions() {
        List<Question> questions = questionsList.getQuestions();
        assertNotNull(questions);
        assertEquals(30, questions.size());
    }

    @Test
    public void hesQuestionTest() {
        boolean cont = false;
        for (Question question : questionsList.getQuestions()) {
            if (question.getNameQuestion().equals(NAME_QUESTION))
                cont = true;

        }
        assertTrue(cont);
    }
}

