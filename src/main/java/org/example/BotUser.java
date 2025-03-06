package org.example;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class BotUser {
    private final long id;
    private String firstName;
    private int lastQuestion;
    private int numberOfRightQuestion;
    private String nickName;

    QuestionsList questionsList = new QuestionsList();
    private List<Question> questions = questionsList.getQuestions();

    public String getNickName() {
        if (nickName == null || nickName.isEmpty() || nickName.equals(" ")) {
            return "[Никнейм отсутствует]";
        }
        return nickName;
    }

    public String getFirstName() {
        return firstName;
    }
    public long getID() { return id; }
    //
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getNumberOfRightQuestion() {
        return numberOfRightQuestion;
    }

    public void setLastQuestion(int lastQuestion) {
        this.lastQuestion = lastQuestion;
    }

    public int getLastQuestion() {
        return lastQuestion;
    }

    public void setNumberOfRightQuestion(int numberOfRightQuestion) {
        this.numberOfRightQuestion = numberOfRightQuestion;
    }

    public BotUser(long id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }

    public String result() {
        if (numberOfRightQuestion <=2) {
            return String.format("Ты ничего не знаешь!\nТы ответил на %d вопрос(а)!",numberOfRightQuestion);
        } else if (numberOfRightQuestion > 3 && numberOfRightQuestion <= 5) {
            return String.format("Ты что-то знаешь, но этого мало!\nТы ответил на %d вопроса(ов)!",numberOfRightQuestion);
        } else if (numberOfRightQuestion > 5 && numberOfRightQuestion <= 9) {
            return String.format("Хорошие знания!\nТы ответил на %d вопросов!",numberOfRightQuestion);
        } else {
            return String.format("Отлично, ты ответил на все вопросы! " + "\nТы ответил на %d вопросов!",numberOfRightQuestion);
        }
    }

    public String resultResend() {
        if (numberOfRightQuestion <=2) {
            return String.format("\n\"Ты ничего не знаешь!\" ответив на %d вопрос(а)!",numberOfRightQuestion);
        } else if (numberOfRightQuestion >3 && numberOfRightQuestion <= 15) {
            return String.format("\n\"Ты что-то знаешь, но этого мало!\" ответив на %d вопроса(ов)!", numberOfRightQuestion);
        } else if (numberOfRightQuestion > 15 && numberOfRightQuestion <= 29) {
            return String.format("\n\"Хорошие знания!\" ответив на %d вопросов!", numberOfRightQuestion);
        } else {
            return String.format("\n\"Отлично, ты ответил на все вопросы!\" " + "\nВсего правильных ответов %d!",numberOfRightQuestion);
        }
    }

    public String getPhoto(Long chatId, String ImgUrl) throws MalformedURLException {
        URL url = new URL(ImgUrl);
        InputFile photo = new InputFile(String.valueOf(url));
        SendPhoto sPhoto = new SendPhoto();
        sPhoto.setPhoto(photo);
        sPhoto.setChatId(chatId.toString());
        sPhoto.setCaption("test");
        return String.valueOf(sPhoto);
    }


    public List<Question> getNewQuestions() {
        //функция random зависит от времени
            List<Question> newQuestions = new ArrayList<>();
            List<Question> shuffledQuestions = new ArrayList<>(questions);
            while (newQuestions.size()<5) {
                Collections.shuffle(shuffledQuestions, ThreadLocalRandom.current());
                if(!newQuestions.contains(shuffledQuestions.get(0))) {
                    newQuestions.add(shuffledQuestions.get(0));
                }
            }
            return newQuestions;

    }

    public void reset() {
        this.lastQuestion = 0;
        this.numberOfRightQuestion = 0;
    }

}
