package ru.stqa.manits.tests;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.manits.manager.MailHelper;
import ru.stqa.manits.model.DeveloperMailUser;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeveloperUserMailCreationTest extends TestBase {

    DeveloperMailUser user;

    @Test
    public void canCreateUserWithDeveloperMail() throws InterruptedException, MessagingException {
        //Регистрация нового адреса с помощью REST
        user = app.developerMail().addUser();
        var email = String.format(user.name() + "%s@developermail.com", user.name());
        var password = "password";

        //Регистрация нового пользователя в Мантис с помощью REST
        app.rest().registrationUser(user.name(),user.name(), email);

        var messages = app.developerMail().receive(user, Duration.ofSeconds(10));
        var url = MailHelper.extractUrlFromMail();

        WebDriver driver = new ChromeDriver();
        driver.get(messages);
        app.session().endRegistration(user.name(), "password", "password");

        app.http().login(user.name(), password);
        assertTrue(app.http().isLoggedIn());
    }


    @AfterEach
    public void deleteMailUser() {
        app.developerMail().deleteUser(user);
    }
}
