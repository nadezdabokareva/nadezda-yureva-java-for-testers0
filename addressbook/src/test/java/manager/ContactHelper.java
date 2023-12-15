package manager;

import com.codeborne.selenide.SelenideElement;
import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.$;


public class ContactHelper extends HelperBase {

    public ContactHelper (ApplicationManager manager) {
        super(manager);
    }

    public void openConPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
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

    public void deleteAllContact() {
        selectAllContact();
        initDeleteContact();
        acceptDeleteContact();
    }

    //Подтвердить создание контакта Enter
    public void submitAddContact() {
       click(By.name("submit"));
    }

    public void submitUpdateContact() {
        click(By.name("update"));
    }

    //Вернуться на домашнюю страницу
    public void returnToHomePage() {
        click(By.linkText("home"));
    }

    //Выбрать контакт
    public void selectContact(ContactData contactData) {
        click(By.name("selected[]"));
    }

    private void selectAllContact() {
        var checkBoxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkBox : checkBoxes) {
            checkBox.click();
        }
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
        returnToHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    //Метод модификации контакта
    public void modifyContact(ContactData contact, String index, ContactData modifiedContact) {
        selectContact(contact);
        openContactCard(Integer.parseInt(index));
        initModifyContact();
        fillContactFields(modifiedContact);
        submitUpdateContact();
        returnToHomePage();
    }

    //Нажать кнопку Modify в карточке контакта
    private void initModifyContact() {
        //вероятно, есть ошибка в названии кнопки в коде стараницы
        click(By.name("modifiy"));
    }

    //Открытие карточки контакта по индексу (генерируется в тесте)
    private void openContactCard(int index) {
        if (index != 0) {
            click(By.xpath("//*[@id='maintable']/tbody/tr[" + index + "]/td[7]"));
        } else if (index == 0) {
            click(By.xpath("//*[@id='maintable']/tbody/tr[" + (index + 2) + "]/td[7]"));
        }
    }

    //Редактирование строки с контактом по индексу (генерируется в тесте)
    private void editContactByIndex(String index) {
        click(By.xpath("//*[@id='maintable']/tbody/tr[" + index + "]/td[8]"));
    }

    public List<ContactData> getList() {
        var contacts = new ArrayList<ContactData>();
        var spans = manager.driver.findElements(By.name("entry"));
        for (int i = 0; i < spans.size(); i++) {
            var span = spans.get(i);
            var checkBox = span.findElement(By.name("selected[]"));
            var firstName = manager.driver.findElement(By.xpath(String.format("//*[@id='maintable']/tbody/tr[%s]/td[3]",i+2 ))).getText();
            var lastName = manager.driver.findElement(By.xpath(String.format("//*[@id='maintable']/tbody/tr[%s]/td[2]",i+2 ))).getText();
//            var firstName = span.findElement(By.xpath(String.format("//*[@id='maintable']/tbody/tr[%s]/td[3]",i+2 ))).getText();
//            var lastName = span.findElement(By.xpath(String.format("//*[@id='maintable']/tbody/tr[%s]/td[2]",i+2 ))).getText();
            var id = checkBox.getAttribute("value");
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
        }
        return contacts;
    }


}
