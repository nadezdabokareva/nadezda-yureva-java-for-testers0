package manager;

import com.codeborne.selenide.commands.FindByXpath;
import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class ContactHelper extends HelperBase {

    public ContactHelper (ApplicationManager manager) {
        super(manager);
    }

    public void addNewContact(ContactData contact) {
        initAddNewContact();
        fillContactFields(contact);
        submitAddContact();
        returnToHomePage();
    }

    //Нажать кнопку добавить новый контакт Add new
    public void initAddNewContact() {
        click(By.linkText("add new"));
    }

    //Заполнить поля в форме контакта
    public void fillContactFields(ContactData contact) {
        click(By.name("firstname"));
        type(By.name("firstname"), contact.firstName());

        click(By.name("middlename"));
        type(By.name("middlename"), contact.middleName());

        click(By.name("lastname"));
        type(By.name("lastname"), contact.lastName());

    }

    //Удаление контакта
    public void deleteContact(ContactData contactData) {
        selectContact(contactData);
        initDeleteContact();
        acceptDeleteContact();
    }

    //Подтвердить создание контакта Enter
    public void submitAddContact() {
       click(By.name("submit"));
    }

    //Вернуться на домашнюю страницу
    public void returnToHomePage() {
        click(By.linkText("home"));
    }

    //Выбрать контакт
    public void selectContact(ContactData contactData) {
        click(By.name("selected[]"));
    }

    //Нажать кнопку Delete
    public void initDeleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    //Подтвердить удаление
    private void acceptDeleteContact() {
        manager.driver.switchTo().alert().accept();
    }

    public int getCount() {
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getList() {
        var contacts = new ArrayList<ContactData>();
        var spans = manager.driver.findElements(By.name("entry"));
        for (int i = 0; i < spans.size(); i++) {
            var span = spans.get(i);
            var checkBox = span.findElement(By.name("selected[]"));
            var firstName = span.findElement(By.xpath(String.format("//*[@id='maintable']/tbody/tr[%s]/td[3]",i+2 ))).getText();
            var lastName = span.findElement(By.xpath(String.format("//*[@id='maintable']/tbody/tr[%s]/td[2]",i+2 ))).getText();
            var id = checkBox.getAttribute("value");
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
        }
        return contacts;
    }


}
