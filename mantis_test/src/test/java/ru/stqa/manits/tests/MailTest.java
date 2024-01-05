package ru.stqa.manits.tests;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MailTest extends TestBase{
    @Test
    public void canReceiveEmail() throws InterruptedException {
        var messages = app.mail().receive("user2@localhost", "password", Duration.ofSeconds(60));
        assertEquals(1, messages.size());
        System.out.println(messages);
    }

    @Test
    public void canDrainInbox(){
        app.mail().drain("user2@localhost", "password");
    }

    @Test
    public void canExtractUrl() throws InterruptedException {
        var messages = app.mail().receive("user2@localhost", "password", Duration.ofSeconds(60));
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (matcher.find()) {
            var url = text.substring(matcher.start(), matcher.end());
            System.out.println(url);
        }
    }
}
