package org.example;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;



public class TG_bot {

    public static final String PROXY_HOST = "167.172.86.46";
    public static final int PROXY_PORT = 10471;

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

        DefaultBotOptions botOptions = new DefaultBotOptions();
        botOptions.setProxyHost(PROXY_HOST);
        botOptions.setProxyPort(PROXY_PORT);
        botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS4);



        MyBot myBot = new MyBot();
        try {
            telegramBotsApi.registerBot(myBot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
