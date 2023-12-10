package tests.ContactTest;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.util.ArrayList;
import java.util.List;

public class ContactCreatingTest extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstName : List.of("", "first name")) {
            for (var middleName : List.of("", "middle name")) {
                for (var lastName : List.of("", "last name")) {
                    result.add(new ContactData()
                            .withFirstName(firstName)
                            .withMiddleName(middleName)
                            .withLastName(lastName));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData()
                    .withFirstName(randomString(i * 10))
                    .withMiddleName(randomString(i * 10))
                    .withLastName(randomString(i * 10)));
        }
        return result;
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("first name'", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void cannotCreateContact(ContactData contact) {
        int contactCount = app.contacts().getCount();

        app.contacts().addNewContact(contact);

        int newContactCount = app.contacts().getCount();

        Assertions.assertEquals(contactCount, newContactCount);
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultiplyContact(ContactData contact) {
        int contactCount = app.contacts().getCount();

        app.contacts().addNewContact(contact);

        int newContactCount = app.contacts().getCount();

        Assertions.assertEquals(contactCount + 1, newContactCount);
    }
}
