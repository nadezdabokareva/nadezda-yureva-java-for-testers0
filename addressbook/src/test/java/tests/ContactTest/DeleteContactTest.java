package tests.ContactTest;

import common.RandomStringGenerator;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class DeleteContactTest extends TestBase {
    private static void checkThatGroupExist() {
        //Проверки на отсутствие групп и контактов
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "gl", "footer", "gn"));
        }
    }

    private static void checkThatContactExist() {
        //Проверки на отсутствие групп и контактов
        if (app.hbm().getContactCount() == 0) {
            app.contacts().addNewContact(new ContactData(
                    "",
                    "new first name",
                    "new data",
                    "new",
                    "",
                    ""));
        }
    }

    @Test
    public void canDeleteContact() {
        //Проверка, есть ли контакты
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData(
                    " ", "first name",
                    "middle name",
                    "last name",
                    "",
                    ""));
        }

        Comparator<ContactData> comparatorById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        //Получение и сортировка спсика контактов ДО удаления
        var oldContacts = app.hbm().getContactList();
        oldContacts.sort(comparatorById);

        //Блок удаления контакта из списка по рандомному числу (по размеру спсика "старых" контактов)
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().deleteContact(oldContacts.get(index));
        app.contacts().returnToHomePage();

        //Получение списка контактов После удаления
        var newContacts = app.hbm().getContactList();
        newContacts.sort(comparatorById);

        //Создание ожидаемого списка
        var expectedContactsList = new ArrayList<>(oldContacts);
        expectedContactsList.remove(index);
        expectedContactsList.sort(comparatorById);

        Assertions.assertEquals(newContacts, expectedContactsList);
    }

    @Test
    public void canDeleteAllContact() {
        app.contacts().deleteAllContact();
    }

   @Test
    public void deleteContactInGroup() {

       var contact = new ContactData()
               .withFirstName(RandomStringGenerator.randomString(10))
               .withLastName(RandomStringGenerator.randomString(10))
               .withPhoto(randomFile("src/test/resources/images"));


       checkThatGroupExist();
       checkThatContactExist();

       Comparator<ContactData> compareById = (o1, o2) -> {
           return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
       };

       var rnd = new Random();
       var rndGroup = rnd.nextInt(app.hbm().getGroupList().size());
       var group = app.hbm().getGroupList().get(rndGroup);


       var oldRelated = app.hbm().getContactInGroups(group);


       var contacts = app.hbm().getContactInGroups(group);
       var index = rnd.nextInt(contacts.size());
       app.contacts().selectGroupFromList(group);
       app.contacts().deleteContactFromGroup(contacts.get(index));


       var newRelated = app.hbm().getContactInGroups(group);

       var expectedList = new ArrayList<ContactData>(oldRelated);

       expectedList.add(contact
               .withId(newRelated.get(newRelated.size() - 1).id()));
       expectedList.sort(compareById);

       Assertions.assertEquals(expectedList, expectedList);
    }

}
