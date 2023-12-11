package tests.ContactTest;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
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
                new ContactData(" ", "first name'", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void cannotCreateContact(ContactData contact) {
        var oldContact = app.contacts().getList();

        app.contacts().addNewContact(contact);

        var newGroups = app.contacts().getList();

        Assertions.assertEquals(oldContact, newGroups);
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultiplyContact(ContactData contact) {
        var oldContact = app.contacts().getList();

        app.contacts().addNewContact(contact);

        var newContact = app.contacts().getList();

        Comparator<ContactData> compareByFirstName = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.firstName()), Integer.parseInt(o2.firstName()));
        };

        newContact.sort(compareByFirstName);

        var expectedList = new ArrayList<ContactData>(oldContact);

        Assertions.assertEquals(oldContact, newContact);

        expectedList.add(contact.withFirstName(newContact.get(newContact.size() - 1).firstName()).withLastName(" "));

        expectedList.sort(compareByFirstName);

        Assertions.assertEquals(newContact, expectedList);
    }
}
