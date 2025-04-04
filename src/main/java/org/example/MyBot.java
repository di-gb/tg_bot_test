package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
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


    public static final String TOKEN = System.getenv("TOKEN");
    private List<Question> questions;
    private final Map<Long, BotUser> USER_MAP;
    public static final long GROUP_ID = -1002457027813l;

    public MyBot() {
        this.USER_MAP = new HashMap<>();
        this.questions = new ArrayList<>();


        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "перезапуск"));
//               listOfCommands.add(new BotCommand(ActButton.NEW_ORDER.getCommand(), ActButton.NEW_ORDER.getName()));
//            listOfCommands.add(new BotCommand(ActButton.CANCEL.getCommand(), ActButton.CANCEL.getName()));
        try {
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().getChatId().equals(GROUP_ID)) {
            return;
        }
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            if (text.equals("/start") ) {
                BotUser botUser = USER_MAP.get(chatId);
                if (botUser !=null) {
                    botUser.reset();

                    questions = botUser.getNewQuestions(); // Получаем новые вопросы
                    sendMessageWithButtons(chatId, questions.get(0).getNameQuestion(), questions.get(0).getAnswers());
                    return;
                }
            }
        }

        if (update.hasCallbackQuery()) {
            String answer = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            BotUser botUser = USER_MAP.get(chatId);

            if ("restart_test".equals(answer)) { // Проверяем, была ли нажата кнопка "Перезапустить тест"
                botUser.reset();
                questions = botUser.getNewQuestions(); // Получаем новые вопросы
                sendMessageWithButtons(chatId, questions.get(0).getNameQuestion(), questions.get(0).getAnswers());
                return;
            }

            int lastQuestion = botUser.getLastQuestion();
            Question question = questions.get(lastQuestion);
            String rightAnswer = question.getRightAnswer();

            if (rightAnswer.equals(answer)) {
                botUser.setNumberOfRightQuestion(botUser.getNumberOfRightQuestion() + 1);
                sendMassage(chatId, "правильный ответ!");
            } else {
                sendMassage(chatId, "это не тот ответ которого мы ожидали!");
            }

            botUser.setLastQuestion(botUser.getLastQuestion() + 1);

            if (botUser.getLastQuestion() == questions.size()) {
                sendMassage(chatId, botUser.result());
                sendMassage(GROUP_ID, "\nПользователь " + "@" + botUser.getNickName() + " " + botUser.getFirstName() +
                        "\nID пользователя: " + botUser.getID() +
                        "\nполучил результат на опрос по java:" + botUser.resultResend() + "\n" + sdf.format(date));
                sendPhoto(chatId, "src/main/java/org/example/resources/wallpaper/изображение_viber_2025-02-19_18-50-37-324.jpg", "картинка");

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
                sendMessageWithButtons(chatId, questions.get(0).getNameQuestion(), questions.get(0).getAnswers());
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