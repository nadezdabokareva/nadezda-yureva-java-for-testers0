package ru.stqa.manits.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class UserApiCreationTest extends TestBase {

    @Test
    public void canCreateUser(){
        String login = RandomStringUtils.randomAlphabetic(8);
        var email = login + "@localhost";
        var password = "password";
        app.jamesApi().addUser(email, password);
    }


}
