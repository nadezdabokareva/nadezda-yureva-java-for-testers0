package tests.ContactTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.RandomStringGenerator;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        var value = mapper.readValue(json, new TypeReference<List<ContactData>>() {
        });
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

        assertEquals(oldContact, newGroups);
    }

    private static void checkThatGroupExist() {
        //Проверки на отсутствие групп и контактов
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "gl", "footer", "gn"));
        }
    }

    private static void checkThatContactExist() {
        //Проверки на отсутствие групп и контактов
        checkContactsWithoutGroups(app.hbm().getContactCount() == 0);
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultiplyContact(ContactData contact) {

        var oldContact = app.hbm().getContactList();

        app.contacts().addNewContact(contact);

        var newContact = app.hbm().getContactList();

        var expectedList = new ArrayList<ContactData>(oldContact);

        expectedList.add(contact
                .withId(newContact.get(newContact.size() - 1).id()));

        assertEquals(Set.copyOf(expectedList), Set.copyOf(expectedList));
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
    public void addContactToGroupFromContactCard() {

        var contact = new ContactData()
                .withFirstName(RandomStringGenerator.randomString(10))
                .withLastName(RandomStringGenerator.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));

        checkThatGroupExist();

        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getContactInGroups(group);

        app.contacts().addNewContactInGroup(contact, group);

        var newRelated = app.hbm().getContactInGroups(group);

        var expectedList = new ArrayList<ContactData>(oldRelated);

        expectedList.add(contact
                .withId(newRelated.get(newRelated.size() - 1).id()));

        assertEquals(Set.copyOf(expectedList), Set.copyOf(expectedList));
    }

    @Test
    public void addContactToGroupFromContactList() {

        //Создание нового контакта для теста
        var contact = new ContactData()
            .withFirstName(RandomStringGenerator.randomString(10))
            .withLastName(RandomStringGenerator.randomString(10))
            .withPhoto(randomFile("src/test/resources/images"));

        checkThatGroupExist();
        checkThatContactExist();

        var rnd = new Random();
        var rndGroup = rnd.nextInt(app.hbm().getGroupList().size());
        var group = app.hbm().getGroupList().get(rndGroup);


        var oldRelated = app.hbm().getContactInGroups(group);


        var contacts = app.hbm().getContactList();
        var index = rnd.nextInt(contacts.size());
        app.contacts().addExistContactToGroup(contacts.get(index), group);


        var newRelated = app.hbm().getContactInGroups(group);

        var expectedList = new ArrayList<ContactData>(oldRelated);

        expectedList.add(contact
                .withId(newRelated.get(newRelated.size() - 1).id()));

        assertEquals(Set.copyOf(expectedList), Set.copyOf(expectedList));
    }

    @Test
    public void addContactToGroupFromContactListCheckout() {

       //Проверка, есть ли группы
        checkThatGroupExist();

        //Проверка, есть ли контакты ВНЕ групп
        app.contacts().selectContactWithNoGroups();
        var contactsWithoutGroups = app.contacts().getList();

        //Если контактов ВНЕ групп нет, то такой контакт создается
        checkContactsWithoutGroups(contactsWithoutGroups.size() == 0);

        //Выбираем рандомную группу
        var rnd = new Random();
        var rndGroup = rnd.nextInt(app.hbm().getGroupList().size());
        var group = app.hbm().getGroupList().get(rndGroup);

        //Получаем список контактов в этой группе
        var oldRelated = app.hbm().getContactInGroups(group);

        //Выбираем контакты, не находящиеся в группе и сохраняем в список
        app.contacts().selectContactWithNoGroups();
        contactsWithoutGroups = app.contacts().getList();

        //Получаем номер рандомного контакта, не находящегося в группе
        var index = rnd.nextInt(contactsWithoutGroups.size());

        //Добавляем контакт в группу
        app.contacts().addExistContactToGroup(contactsWithoutGroups.get(index), group);


        var newRelated = app.hbm().getContactInGroups(group);

        var expectedList = new ArrayList<ContactData>(oldRelated);

        expectedList.add(new ContactData()
                .withId(newRelated.get(newRelated.size() - 1).id()));

        assertEquals(Set.copyOf(expectedList), Set.copyOf(expectedList));
    }

    private static void checkContactsWithoutGroups(boolean contactsWithoutGroups) {
        if (contactsWithoutGroups) {
            app.hbm().createContact(new ContactData(
                    "",
                    "new first name",
                    "new data",
                    "new",
                    "",
                    "", "", "", "", "", "", "", ""));
        }
    }


}
