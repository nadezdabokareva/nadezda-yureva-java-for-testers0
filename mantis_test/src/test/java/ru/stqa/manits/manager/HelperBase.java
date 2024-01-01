package ru.stqa.manits.manager;

import org.openqa.selenium.By;

import java.nio.file.Paths;

public class HelperBase {
    protected ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    //Кликнуть по элементу
    protected void click(By locator) {
        manager.driver().findElement(locator).click();
    }

    //Метод редактирования полей в форме группы
    protected void type(By locator, String text) {
        click(locator);
        manager.driver().findElement(locator).clear();
        manager.driver().findElement(locator).sendKeys(text);
    }

    //Метод добавления изображения
    protected void attach(By locator, String file) {
        manager.driver().findElement(locator).sendKeys(Paths.get(file).toAbsolutePath().toString());
    }

    protected boolean isElementPresent(By locator){
        return manager.driver().findElements(locator).size() > 0;
    }
}
