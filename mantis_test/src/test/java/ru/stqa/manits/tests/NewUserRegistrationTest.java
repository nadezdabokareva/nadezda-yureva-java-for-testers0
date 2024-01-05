package ru.stqa.manits.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewUserRegistrationTest extends TestBase {



    @Test
    public void canRegisterUser(String login) throws InterruptedException {
        //Создать адрес почты на сервере James
        login = RandomStringUtils.randomAlphabetic(6);
        var email = String.format("%s@localhost", login);
        app.jamesCli().addUser(
                email,
                "password");

        //Заполнить форму создания пользователя и отправить ее --браузер
        app.session().createUser(login, login, email);

        //Получаем почту Mail
        var messages = app.mail().receive(email, "password", Duration.ofSeconds(10));
        System.out.println(messages);




        //Проходим по ссылке в браузере и завершаем регистрацию --браузер

        //Проверка возможности логина Http
        app.http().login(email, "password");
        assertTrue(app.http().isLoggedIn());

    }


}
