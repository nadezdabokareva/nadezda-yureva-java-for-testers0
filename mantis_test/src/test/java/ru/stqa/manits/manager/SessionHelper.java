package ru.stqa.manits.manager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase {

    public SessionHelper(ApplicationManager manager) {
        super(manager);
    }

    public void login(String login, String password) {
        type(By.id("username"), login);
        click(By.cssSelector("input[value=Login"));
        type(By.name("password"), password);
        click(By.cssSelector("input[value=Login"));
    }

    public boolean isLoggedIn() {
        return isElementPresent(By.cssSelector("span.user-info"));
    }
}
