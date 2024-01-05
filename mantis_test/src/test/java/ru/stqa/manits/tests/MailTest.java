package ru.stqa.manits.tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MailTest extends TestBase{
    @Test
    public void canReceiveEmail(){
        var messages = app.mail().receive("user2@localhost", "password");
        assertEquals(1, messages.size());
        System.out.println(messages);
    }
}
