package tests.ContactTest;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Random;

public class DeleteContactTest extends TestBase {

    @Test
    public void canDeleteContact() throws InterruptedException {
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());

        app.contacts().deleteContact(oldContacts.get(index));
        Thread.sleep(3000);
        app.contacts().returnToHomePage();

        var newContacts = app.contacts().getList();


        var expectedContactsList = new ArrayList<>(oldContacts);
        expectedContactsList.remove(index);

        Assertions.assertEquals(newContacts, expectedContactsList);
    }
}
