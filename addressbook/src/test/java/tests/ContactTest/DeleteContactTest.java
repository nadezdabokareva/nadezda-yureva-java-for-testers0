package tests.ContactTest;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class DeleteContactTest extends TestBase {

    @Test
    public void canDeleteContact() {
        //Проверка, есть ли контакты
        if (app.contacts().getCount() == 0) {
            app.contacts().addNewContact(new ContactData(
                    " ", "first name",
                    "middle name",
                    "last name",
                    "", ));
        }

        Comparator<ContactData> comparatorById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        //Получение и сортировка спсика контактов ДО удаления
        var oldContacts = app.contacts().getList();
        oldContacts.sort(comparatorById);

        //Блок удаления контакта из списка по рандомному числу (по размеру спсика "старых" контактов)
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().deleteContact(oldContacts.get(index));
        app.contacts().returnToHomePage();

        //Получение списка контактов После удаления
        var newContacts = app.contacts().getList();
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
}
