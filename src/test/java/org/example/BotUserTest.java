package org.example;

import lombok.Getter;
import lombok.Setter;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

@Setter
@Getter
class BotUserTest {
    public static final String PROXY_HOST = "167.172.86.46";
    private static final long ID = 1L;
    private static final String FIRST_NAME = "user";
    private static final String NICK_NAME = "U$ERRR";
    private static BotUser botUset;
    private static BotUser botUset1;
    private static String line = "[Никнейм отсутствует]";


    @BeforeAll
    static void setUp() {
        botUset = new BotUser(ID, FIRST_NAME, NICK_NAME);
        botUset1 = new BotUser(ID, FIRST_NAME, null);
    }

    @Test
    void test() {
        long expectedId = botUset.getId();
        assertEquals(expectedId, ID);
    }

    @Test
    void del() {
        String isProxy = PROXY_HOST;
        assertEquals(isProxy, PROXY_HOST);
        assertNotNull(PROXY_HOST);

    }

    @Test
    void getNickName() {
        String s = botUset1.getNickName();
        assertEquals(s, line);
    }


}