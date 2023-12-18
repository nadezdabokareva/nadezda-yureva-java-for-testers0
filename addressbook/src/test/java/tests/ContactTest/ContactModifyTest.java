package tests.ContactTest;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModifyTest extends TestBase {

    @Test
    public void canModifyContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().addNewContact(new ContactData
                    (" ",
                     "first name",
                    "middle name",
                    "last name",
                            ""));
        }

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        var oldContacts = app.contacts().getList();
        oldContacts.sort(compareById);

        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());

        var testData = new ContactData().withFirstName("modification");

        app.contacts().modifyContact(oldContacts.get(index), testData);

        var newContacts = app.contacts().getList();
        newContacts.sort(compareById);

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));
        expectedList.sort(compareById);

        Assertions.assertEquals(newContacts, expectedList);
    }
}
