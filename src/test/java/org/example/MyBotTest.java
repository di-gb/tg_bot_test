package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class MyBotTest {

    @Test
    void sendPhotoTest() {
        String fileName = "src/main/java/org/example/resources/wallpaper/изображение_viber_2025-02-19_19-14-03-997.jpg";
        MyBot myBot = Mockito.spy(MyBot.class);
        myBot.sendPhoto(191299776L, fileName, "caption");
        try {
            verify(myBot).execute(any(SendPhoto.class));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }
}