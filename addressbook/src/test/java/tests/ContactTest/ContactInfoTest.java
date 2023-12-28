package tests.ContactTest;

import model.ContactData;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactInfoTest extends TestBase {

    @Test
    public void testPhones() {
        checkThatContactExist();

        var contacts = app.hbm().getContactList();
        var expectedPhoneList = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
            //Что-то не так с полем home: когда номер телефона там заполнен, то он не воспринимется системой и не добавляется в стрим
            Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                    .filter(s -> s != null && !"".equals(s))
                    .collect(Collectors.joining("\n"))
        ));
        var phonesFromDbList = app.contacts().getPhones();
        assertEquals(expectedPhoneList, phonesFromDbList);
    }

    private static void checkThatContactExist() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("",
                    RandomStringUtils.randomAlphabetic(10),
                    RandomStringUtils.randomAlphabetic(10),
                    RandomStringUtils.randomAlphabetic(10),
                    "",
                    RandomStringUtils.randomAlphabetic(10),
                    "",
                    RandomStringUtils.randomNumeric(6),
                    "",
                    RandomStringUtils.randomNumeric(6)));
        }
    }
}
