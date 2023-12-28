package ru.stqa.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionTest {

    //Массивы
    //Нельзя изменить элемент массива
    @Test
    void arrayTest() {
        //создали массив
        var array = new String[]{"a", "b", "c"};

        //создать массив с явно обозначенным размером
        var array1 = new String[3];

        assertEquals("a", array[0]);

        //присваиваем новое значение элементу массива
        array[0] = "d";
        assertEquals("d", array[0]);

        //Проверка длины массива
        assertEquals(3, array1.length);
    }

    //Списки
    @Test
    void listTest() {
        //Создали список для хранения строк, в <> указывается тип хранящихся элементов
        var list = new ArrayList<String>();

        //Получить данные о длинне списка
        assertEquals(0, list.size());

        //добавить элементы в список
        list.add("a");
        list.add("b");

        //Получить элемент списка по его индексу в списке
        assertEquals("b", list.get(1));

        //Изменить элемент списка
        list.set(0, "d");
        assertEquals("d", list.get(0));

        //Создать список сразу проинициализированный набором элементов
        //Но элементы такого списка МЕНЯТЬ НЕЛЬЗЯ
        var list1 = List.of("a", "b", "c");

        //Чтобы можно было менять:
        var list2 = new ArrayList<String>(List.of("a", "b", "c"));
    }

    @Test
    public void setTests(){
        var set = Set.of("a", "b", "c");
        assertEquals(3, set.size());
    }

    @Test
    public void setCopyTests(){
        var set = Set.copyOf(List.of("a","b", "c", "a"));
        assertEquals(3, set.size());
    }
    @Test
    public void getSetElementTests(){
        var set = Set.copyOf(List.of("a","b", "c", "a"));
        assertEquals(3, set.size());
        //1 метод полчения данных - ЕСТЬ ВОПРОС
//        var set2 = set.toArray();
//        set2.??

        //2 метод полчения данных
        //set.iterator().next();

        //3 метод полчения данных
        var element = set.stream().findAny().get();
        System.out.println(element);
    }

    @Test
    public void modifySetElementTests() {
        var set = new HashSet<>(List.of("a", "b", "c", "a"));
        assertEquals(3, set.size());


        //Не изменится
        set.add("a");
        System.out.println(set);

        set.add("v");
        System.out.println(set);
    }

    @Test
    public void testMap(){
        var digits = new HashMap<Character, String>();
        digits.put('1', "one");
        digits.put('2', "two");
        digits.put('3', "three");

        System.out.println(digits);
        assertEquals("one", digits.get('1'));

        digits.put('1', "один");
        System.out.println(digits);
        assertEquals("один", digits.get('1'));
    }

}
