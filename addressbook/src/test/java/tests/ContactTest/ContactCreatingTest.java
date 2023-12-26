package tests.ContactTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.RandomStringGenerator;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreatingTest extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
//        for (var firstName : List.of("", "first name")) {
//            for (var middleName : List.of("", "middle name")) {
//                for (var lastName : List.of("", "last name")) {
//                    result.add(new ContactData()
//                            .withFirstName(firstName)
//                            .withMiddleName(middleName)
//                            .withLastName(lastName));
//                }
//            }
//        }

        //чтение файла построчно
        var json = " ";
        try (var reader = new FileReader("contacts.json");
             var breader = new BufferedReader(reader)
        ) {
            var line = breader.readLine();
            while (line != null) {
                json = json + line;
                line = breader.readLine();
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<ContactData>>() {});
        result.addAll(value);
        return result;
    }

//    public static List<ContactData> negativeContactProvider() {
//        var result = new ArrayList<ContactData>(List.of(
//                new ContactData(" ",
//                        "firstName",
//                        " ",
//                        " ",
//                        "")));
//        return result;
//    }

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

        var oldContact = app.hbm().getContactList();
        oldContact.sort(compareById);

        app.contacts().addNewContact(contact);

        var newContact = app.hbm().getContactList();
        newContact.sort(compareById);

        var expectedList = new ArrayList<ContactData>(oldContact);

        expectedList.add(contact
                .withId(newContact.get(newContact.size() - 1).id()));

        expectedList.sort(compareById);

        Assertions.assertEquals(newContact, expectedList);
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void addContactWithPhoto() {
        var contact = new ContactData()
                .withFirstName(RandomStringGenerator.randomString(10))
                .withLastName(RandomStringGenerator.randomString(10))
                        .withPhoto(randomFile("src/test/resources/images"));

        app.contacts().addNewContact(contact);
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void addContactInGroup() {

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        var contact = new ContactData()
                .withFirstName(RandomStringGenerator.randomString(10))
                .withLastName(RandomStringGenerator.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));

        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "gl", "footer", "gn"));
        }

        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getContactInGroups(group);

        app.contacts().addNewContactInGroup(contact, group);

        var newRelated = app.hbm().getContactInGroups(group);

        var expectedList = new ArrayList<ContactData>(oldRelated);

        expectedList.add(contact
                .withId(newRelated.get(newRelated.size() - 1).id()));
        expectedList.sort(compareById);

        Assertions.assertEquals(expectedList, newRelated);
    }

}
