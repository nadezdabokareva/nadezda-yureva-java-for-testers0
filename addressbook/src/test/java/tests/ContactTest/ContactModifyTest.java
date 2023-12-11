package tests.ContactTest;

import model.ContactData;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static tests.TestBase.app;

public class ContactModifyTest {

    @Test

    void canModifyContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().addNewContact(new ContactData(
                    " ", "first name",
                    "middle name",
                    "last name"));
        }

        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());

        var testData = new ContactData().withFirstName("first name");

        app.contacts().modifyContact(oldContacts.get(index), testData);

        var newContacts = app.groups().getList();


//        var expectedList = new ArrayList<>(oldContacts);
//        expectedList.set(index, testData.withFirstName(oldContacts.get(index).firstName()));
//
//        Comparator<GroupData> compareByFirstName = (o1, o2) -> {
//            return Integer.compare(Integer.parseInt(o1.firstName()), Integer.parseInt(o2.firstName()));
//        };
//
//        newContacts.sort(compareByFirstName);
//
//        expectedList.sort(compareByFirstName);
//
//        Assertions.assertEquals(newContacts, expectedList);
    }
}
