package ru.stqa.manits.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.stqa.manits.model.DeveloperMailUser;

public class UserDeveloperMailCreationTest extends TestBase {

    DeveloperMailUser user;

    @Test
    public void canCreateUserWithDeveloperMail(){
        String login = RandomStringUtils.randomAlphabetic(8);
        user = app.developerMail().addUser();
        var email = String.format(login + "%s@developermail.com", user.name());
        var password = "password";
    }

    @AfterEach
    public void deleteMailUser() {
        app.developerMail().deleteUser(user);
    }
}
