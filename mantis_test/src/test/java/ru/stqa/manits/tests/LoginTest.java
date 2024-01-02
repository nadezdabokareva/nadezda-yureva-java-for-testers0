package ru.stqa.manits.tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends TestBase{
    @Test
    public void canLogin(){
        app.session().login("administrator", "root");
        assertTrue(app.session().isLoggedIn());
    }
    @Test
    public void canHttpLogin(){
        app.http().login("administrator", "root");
        assertTrue(app.http().isLoggedIn());
    }
}
