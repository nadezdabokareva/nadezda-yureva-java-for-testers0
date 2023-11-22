package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {

    public ContactHelper (ApplicationManager manager) {
        super(manager);
    }

    public void addNewContact(ContactData contact) {
        initAddNewGroup();
        fillContactFields(contact);
        submitAddContact();
        returnToHomePage();
    }

    public void initAddNewGroup() {
        manager.driver.findElement(By.linkText("add new")).click();
    }

    public void fillContactFields(ContactData contact) {
//        manager.driver.findElement(By.name("firstname")).click();
//        manager.driver.findElement(By.name("firstname")).sendKeys(contact.firstName());

        click(By.name("firstname"));
        type(By.name("firstname"), contact.firstName());

        manager.driver.findElement(By.name("middlename")).click();
        manager.driver.findElement(By.name("middlename")).sendKeys(contact.middleName());

        manager.driver.findElement(By.name("lastname")).click();
        manager.driver.findElement(By.name("lastname")).sendKeys(contact.lastName());
    }

    public void submitAddContact() {
        manager.driver.findElement(By.name("submit")).click();
    }

    public void returnToHomePage() {
        manager.driver.findElement(By.linkText("home page")).click();
    }
}
