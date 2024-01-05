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

    public void createUser(String username, String realname, String email) {
        click(By.linkText("Manage"));
        click(By.linkText("Users"));
        click(By.linkText("Create New Account"));
        type(By.name("user-username"), username);
        type(By.name("user-realname"), realname);
        type(By.name("email-field"), email);
        click(By.cssSelector("input[value=Create User"));
    }
}
