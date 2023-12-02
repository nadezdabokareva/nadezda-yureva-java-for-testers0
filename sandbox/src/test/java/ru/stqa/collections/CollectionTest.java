package ru.stqa.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CollectionTest {

    //Массивы
    //Нельзя изменить элемент массива
    @Test
    void arrayTest() {
        //создали массив
        var array = new String[]{"a", "b", "c"};

        //создать массив с явно обозначенным размером
        var array1 = new String[3];

        Assertions.assertEquals("a", array[0]);

        //присваиваем новое значение элементу массива
        array[0] = "d";
        Assertions.assertEquals("d", array[0]);

        //Проверка длины массива
        Assertions.assertEquals(3, array1.length);
    }

    //Списки
    @Test
    void listTest() {
        //Создали список для хранения строк, в <> указывается тип хранящихся элементов
        var list = new ArrayList<String>();

        //Получить данные о длинне списка
        Assertions.assertEquals(0, list.size());

        //добавить элементы в список
        list.add("a");
        list.add("b");

        //Получить элемент списка по его индексу в списке
        Assertions.assertEquals("b", list.get(1));

        //Изменить элемент списка
        list.set(0, "d");
        Assertions.assertEquals("d", list.get(0));

        //Создать список сразу проинициализированный набором элементов
        //Но элементы такого списка МЕНЯТЬ НЕЛЬЗЯ
        var list1 = List.of("a", "b", "c");

        //Чтобы можно было менять:
        var list2 = new ArrayList<String>(List.of("a", "b", "c"));
    }
}
