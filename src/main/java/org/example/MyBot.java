package org.example;

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

    public static final String USER_NAME = "les_edu_3o8o58o_bot";


    public static final String TOKEN = System.getenv("SUPPORT_TOKEN");
    private List<Question> questions;
    private final Map<Long, BotUser> USER_MAP;
    public static final long GROUP_ID = -1002457027813l;

    public MyBot() {
        this.USER_MAP = new HashMap<>();
        this.questions = new ArrayList<>();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().getChatId().equals(GROUP_ID)) {
            return;
        }

        if (update.hasCallbackQuery()) {
            String answer = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            BotUser botUser = USER_MAP.get(chatId);

            if ("restart_test".equals(answer)) { // Проверяем, была ли нажата кнопка "Перезапустить тест"
                botUser.reset();
                questions = botUser.getNewQuestions(); // Получаем новые вопросы
                sendMessageWithButtons(chatId, questions.getFirst().getNameQuestion(), questions.getFirst().getAnswers());
                return;
            }

            int lastQuestion = botUser.getLastQuestion();
            Question question = questions.get(lastQuestion);
            String rightAnswer = question.getRightAnswer();

            if (rightAnswer.equals(answer)) {
                botUser.setNumberOfRightQuestion(botUser.getNumberOfRightQuestion() + 1);
            }

            botUser.setLastQuestion(botUser.getLastQuestion() + 1);

            if (botUser.getLastQuestion() == questions.size()) {
                sendMassage(chatId, botUser.result());
                sendMassage(GROUP_ID, "\nПользователь " + "@" + botUser.getNickName() + " " + botUser.getFirstName() +
                        "\nID пользователя: " + botUser.getID() +
                        "\nполучил результат на опрос по java:" + botUser.resultResend() + "\n" + sdf.format(date));
                sendPhoto(chatId, "src/main/java/org/example/resources/wallpaper/изображение_viber_2025-02-19_19-14-03-997.jpg", "картинка");

                sendRestartButton(chatId);
            } else {
                sendMessageWithButtons(chatId, questions.get(botUser.getLastQuestion()).getNameQuestion(), questions.get(botUser.getLastQuestion()).getAnswers());
            }
        }

        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            Message message = update.getMessage();

            if (USER_MAP.get(chatId) == null) {
                BotUser botUser = new BotUser(message.getFrom().getId(), message.getFrom().getFirstName(), message.getFrom().getUserName());
                USER_MAP.put(chatId, botUser);

                sendMassage(chatId, "\nПривет " + message.getFrom().getFirstName() + "! Пройди тест по Java ");
                questions = botUser.getNewQuestions();
                sendMessageWithButtons(chatId, questions.getFirst().getNameQuestion(), questions.getFirst().getAnswers());
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

    private void sendRestartButton(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Перезапустить тест");
        button.setCallbackData("restart_test"); // Уникальное значение для перезапуска
        row.add(button);

        rows.add(row);
        keyboardMarkup.setKeyboard(rows);

        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText("Тест завершен! Нажмите, чтобы начать снова.");
        message.setReplyMarkup(keyboardMarkup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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