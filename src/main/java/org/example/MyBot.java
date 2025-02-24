package org.example;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.util.*;


public class MyBot extends TelegramLongPollingBot {
    Locale ru = new Locale("ru");
    DateFormat sdf = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, ru);
    Date date = new Date();
    //    URL url = new URL();
//    url ="C:\\Users\\stroi\\IdeaProjects\\Start\\src\\main\\java\\org\\example\\resources\\wallpaper";
    //имя бота.
    public static final String USER_NAME = "les_edu_3o8o58o_bot";
    //специальный токен для работы бота


    public static final String TOKEN = System.getenv("TOKEN");
    //лист вопросов и именем questions
    private final List<Question> QUESTIONS;
    //мапа с первым значением long и вторым значением ботЮзер с именем УзерМап
    private final Map<Long, BotUser> USER_MAP;
    //переменная хранящая ИД группый на которыйю будет отсылаться результат
    public static final long GROUP_ID = -1002457027813l;
//    private final List<Photo> PHOTOS;


    public MyBot() {

        this.USER_MAP = new HashMap<>();
        //  this.PHOTOS = new ArrayList<>();
        this.QUESTIONS = new ArrayList<>();

        QUESTIONS.add(new Question("В языке Java переменные типа boolean могут принимать значения",
                List.of("true, false", "правда, ложь", "ложь, истина", "success, failed"),
                0));
        QUESTIONS.add(new Question("В языке Java для отрицания(инверсии) логического выражения используется оператор:",
                List.of("!", "!=", "~", "not"),
                1));
        QUESTIONS.add(new Question("Строковые переменные a и b правильно сравниваются в выражении:",
                List.of(" a == b", "a.equals(b)", "a.length() == b.length()", "a.toUpperCase() == b.toUpperCase"),
                1));
        QUESTIONS.add(new Question("Правильно скомпилируется вариант",
                List.of("int x = 3.14;", "int x = (int) 3.14:", "int x = (double) 3.14;", "int x = (float) 3.14;"),
                2));
        QUESTIONS.add(new Question("Какой из примитивных типов данных есть в языке Java?",
                List.of("bool", "shar", "char", "varchar"),
                2));
        QUESTIONS.add(new Question("В классе объявили переменную String s, но еще не присвоили ей значение. Она содержит?",
                List.of("\"\"", "0", "null", "nill"),
                2));
        QUESTIONS.add(new Question("Новый объект в Java создается с помощью ключевого слова",
                List.of("create", "alloc", "new", "return"),
                2));
        QUESTIONS.add(new Question("Какой из этих принципов есть в ООП?",
                List.of("Инкорпорация", "Перфекционизм", "Абстракционизм", "Наследование"),
                3));
        QUESTIONS.add(new Question("В каком из вариантов правильно описано наследование класса Собака и класса Волк?",
                List.of("class Волк extends Собака", "class Собака extends Волк", "class Волк implements Собака", "class Собака implements Волк"),
                1));
        QUESTIONS.add(new Question("Какой из вариантов содержит примитивные типы языка Java?",
                List.of("int, real, string", "int, integer, string", "int, double, real, string", "int, double, float"),
                3));
        QUESTIONS.add(new Question("тестовый вопрос",
                List.of("1", "2", "3", "4"),
                3));
        QUESTIONS.add(new Question("тестовый вопрос",
                List.of("1", "658", "3", "4"),
                4));
        QUESTIONS.add(new Question("тестовый вопрос",
                List.of("1", "856", "567", "568"),
                2));
        QUESTIONS.add(new Question("тестовый вопрос",
                List.of("1", "658", "3", "4"),
                1));
        QUESTIONS.add(new Question("тестовый вопрос",
                List.of("1", "856", "567", "568"),
                1));QUESTIONS.add(new Question("тестовый вопрос",
                List.of("1", "856", "567", "568"),
                1));QUESTIONS.add(new Question("тестовый вопрос",
                List.of("1", "856", "567", "568"),
                1));QUESTIONS.add(new Question("тестовый вопрос",
                List.of("1", "856", "567", "568"),
                1));QUESTIONS.add(new Question("тестовый вопрос",
                List.of("1", "856", "567", "568"),
                1));QUESTIONS.add(new Question("тестовый вопрос",
                List.of("1", "856", "567", "568"),
                1));
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()) {
            String answer = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            BotUser botUser = USER_MAP.get(chatId);
            int lastQuestion = botUser.getLastQuestion();
            Question question = QUESTIONS.get(lastQuestion);
            String rightAnswer = question.getRightAnswer();
            if (rightAnswer.equals(answer)) {
                botUser.setNumberOfRightQuestion(botUser.getNumberOfRightQuestion() + 1);
            }
            botUser.setLastQuestion(botUser.getLastQuestion() + 1);
            if (botUser.getLastQuestion() == QUESTIONS.size()) {

                sendMassage(chatId, botUser.result());
                sendMassage(chatId, botUser.restart());
                sendMassage(GROUP_ID, "\nПользователь " + botUser.getFirstName() + " получил результат на опрос по java:" + botUser.resultResend() + "\n" + sdf.format(date));
                sendPhoto(chatId, "src/main/java/org/example/resources/wallpaper/изображение_viber_2025-02-19_19-14-03-997.jpg", "картинка");
                //здесь будет отправляться в общий чат как ответил пользователь на вопросы
                sendMassage(chatId, "Хотете получить порцию информации для изучения ?");
            } else {
                sendMessageWithButtons(chatId, QUESTIONS.get(botUser.getLastQuestion()).getNameQuestion(), QUESTIONS.get(botUser.getLastQuestion()).getAnswers());
            }
        }


        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            Message message = update.getMessage();
            if (USER_MAP.get(chatId) == null) {
                USER_MAP.put(chatId, new BotUser(message.getFrom().getId(), message.getFrom().getFirstName()));

                String wave = EmojiParser.parseToUnicode(":wave:");
                sendMassage(chatId, "\nПривет " + wave + " " + message.getFrom().getFirstName() + "! Пройди тест по Java ");
                sendMessageWithButtons(chatId, QUESTIONS.get(0).getNameQuestion(), QUESTIONS.get(0).getAnswers());


            }
        }
    }


    private void sendMessageWithButtons(Long chatId, String text, List<String> buttons) {
        SendMessage replay = new SendMessage();
        replay.setChatId(chatId.toString());
        replay.setText(text);
        replay.setReplyMarkup(createButtons(buttons));
        if (buttons != null) {
            replay.setReplyMarkup(createButtons(buttons));
        }
        try {
            execute(replay);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            e.getMessage();
            e.getCause();
        }
    }

    public void sendPhoto(Long chatId, String fileName, String caption) {
        try {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId.toString());
            sendPhoto.setPhoto(new InputFile(new FileInputStream("./" + fileName), fileName));
            sendPhoto.setCaption(caption);
            execute(sendPhoto);

        } catch (FileNotFoundException | TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static InlineKeyboardMarkup createButtons(List<String> buttonsName) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        buttonsName.forEach(name -> {
            List<InlineKeyboardButton> rowInline = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(name);
            button.setCallbackData(name);
            rowInline.add(button);
            rowsInLine.add(rowInline);
        });

        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        return inlineKeyboardMarkup;
    }

    private void sendMassage(Long chatId, String text) {
        SendMessage reply = new SendMessage();
        reply.setChatId(chatId.toString());
        reply.setText(text);
        try {
            execute(reply);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            e.getMessage();
            e.getCause();
        }
    }

    @Override
    public String getBotUsername() {
        return USER_NAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }
}