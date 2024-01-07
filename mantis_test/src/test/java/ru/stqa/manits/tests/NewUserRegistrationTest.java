package ru.stqa.manits.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.manits.manager.MailHelper;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewUserRegistrationTest extends TestBase {

    @Test
    public void canRegisterUser() throws InterruptedException {
        //Создать адрес почты на сервере James
        String  login = RandomStringUtils.randomAlphabetic(6);
        String email = MailHelper.createNewMailAddres(login);


        //Заполнить форму создания пользователя и отправить ее --браузер
        app.session().login("administrator", "root");
        app.session().createUser(login, login, email);

        //Получаем почту Mail
        var messages = app.mail().receive(email, "password", Duration.ofSeconds(10));
        System.out.println(messages);

        //Получить ссылку
        String url = MailHelper.extractUrlFromMail();


        //Проходим по ссылке в браузере и завершаем регистрацию --браузер
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        app.session().endRegistration(login, "password", "password");

        //Проверка возможности логина Http
        app.http().login(email, "password");
        assertTrue(app.http().isLoggedIn());

    }


}
