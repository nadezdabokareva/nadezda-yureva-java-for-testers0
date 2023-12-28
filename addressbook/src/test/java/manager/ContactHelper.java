package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public void addNewContactInGroup(ContactData contact, GroupData group) {
        initAddNewContact();
        fillContactFields(contact);
        selectGroupById(group);
        submitAddContact();
        returnToHomePage();
    }

    public void addExistContactToGroup(ContactData contact, GroupData group) {
    selectContactById(contact);
    selectGroupToAddContact(group);
    addContact();
    }

    private void addContact() {
        click(By.name("add"));
    }

    private void selectGroupToAddContact(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    private void selectGroupById(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }
    private void selectExistedGroupById(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }
    private void selectContactById(ContactData contact) {
        click(By.cssSelector(String.format("input[id='%s']", contact.id())));
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

    //Метод модификации контакта - был изменен 15.12
    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openContactCard(contact);
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

    private void openContactCard(ContactData contact) {
        click(By.xpath(String.format("//a[@href='view.php?id=%s']", contact.id())));
    }


    public List<ContactData> getList() {
        var contacts = new ArrayList<ContactData>();
        var spans = manager.driver.findElements(By.name("entry"));
        for (int i = 0; i < spans.size(); i++) {
            var span = spans.get(i);
            var checkBox = span.findElement(By.name("selected[]"));
            var firstName = manager.driver.findElement(By.xpath(String.format("//*[@id='maintable']/tbody/tr[%s]/td[3]",i+2 ))).getText();
            var lastName = manager.driver.findElement(By.xpath(String.format("//*[@id='maintable']/tbody/tr[%s]/td[2]",i+2 ))).getText();
            var id = checkBox.getAttribute("value");
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
        }
        return contacts;
    }

    public void deleteContactFromGroup( ContactData contact) {
        selectContactById(contact);
        removeContactFromGroup();
    }

    public void selectGroupFromList(GroupData group) {
        openDropDownGroupList();
        selectExistedGroupById(group);
    }

    private void removeContactFromGroup() {
        click(By.name("remove"));
    }

    private void openDropDownGroupList() {
        click(By.name("group"));
    }

    public String getPhones(ContactData contact) {
       return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]", contact.id())))
                .getText();
    }

    public Map<String, String> getPhones() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }
}
