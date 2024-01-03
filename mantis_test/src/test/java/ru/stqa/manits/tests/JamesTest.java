package ru.stqa.manits.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

public class JamesTest extends TestBase{

    @Test
    void canCreateUser(){
        app.jamesCli().addUser(
                RandomStringUtils.randomAlphabetic(6) +"@localhost",
                "password");
    }
}
