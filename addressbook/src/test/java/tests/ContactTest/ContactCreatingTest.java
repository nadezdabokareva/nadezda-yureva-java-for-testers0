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
                new ContactData(" ", "first name'", "", "", "")));
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

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        var oldContact = app.contacts().getList();
        oldContact.sort(compareById);

        app.contacts().addNewContact(contact);

        var newContact = app.contacts().getList();
        newContact.sort(compareById);

        var expectedList = new ArrayList<ContactData>(oldContact);

        expectedList.add(contact
                .withId(newContact.get(newContact.size() - 1).id()));
//                .withFirstName(newContact.get(newContact.size() - 1).firstName())
//                .withLastName(newContact.get(newContact.size() - 1).lastName()));

        expectedList.sort(compareById);

        Assertions.assertEquals(newContact, expectedList);
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void addContactWithPhoto() {
        var contact = new ContactData()
                .withFirstName(randomString(10))
                .withLastName(randomString(10))
                .withPhoto("src/test/resources/images/avatar.png");

        app.contacts().addNewContact(contact);
    }

}
