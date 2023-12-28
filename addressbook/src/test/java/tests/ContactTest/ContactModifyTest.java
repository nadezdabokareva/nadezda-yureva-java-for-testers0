package tests.ContactTest;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactModifyTest extends TestBase {

    @Test
    public void canModifyContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().addNewContact(new ContactData
                    (" ",
                     "first name",
                    "middle name",
                    "last name",
                            "",
                            "", "", "", "", ""));
        }


        var oldContacts = app.contacts().getList();

        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());

        var testData = new ContactData().withFirstName("modification");

        app.contacts().modifyContact(oldContacts.get(index), testData);

        var newContacts = app.contacts().getList();

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));

       assertEquals(Set.copyOf(newContacts), Set.copyOf(expectedList));
    }

    @Test
    public void canModifyHbmContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData(
                    " ",
                    "first name",
                    "middle name",
                    "last name",
                    "",
                    "", "", "", "", ""));
        }


        var oldContacts = app.hbm().getContactList();

        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());

        var testData = new ContactData().withFirstName("modification");

        app.contacts().modifyContact(oldContacts.get(index), testData);

        var newContacts = app.contacts().getList();

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));

        assertEquals(Set.copyOf(newContacts), Set.copyOf(expectedList));
    }
}
