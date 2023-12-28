package tests.ContactTest;

import common.RandomStringGenerator;
import model.ContactData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactInfoTest extends TestBase {

    @Test
    public void testPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData(
                    " ",
                    "first name",
                    "middle name",
                    "last name",
                    "",
                    "", "", "", "", ""));
        }

        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var phonesFromContactList = app.contacts().getPhones(contact);

        var expectedPhoneList = Stream.of(contact.home(),
                contact.mobile(),
                contact.work(),
                contact.phone2())
                .filter(s-> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));

        assertEquals(expectedPhoneList, phonesFromContactList);
    }
}
