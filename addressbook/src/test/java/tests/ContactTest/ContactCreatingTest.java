package tests.ContactTest;

import model.ContactData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class ContactCreatingTest extends TestBase {

    @Test
    public void canCreateContact() {
        app.contacts().addNewContact(new ContactData("first name", "middle name", "last name"));
    }
}
