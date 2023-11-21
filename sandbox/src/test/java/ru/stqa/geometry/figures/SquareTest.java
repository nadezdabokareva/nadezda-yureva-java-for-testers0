package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTest {

    @Test
    public void canCalculateArea() {
        var s = new Square(5.0);
        var result = s.squareArea();
        Assertions.assertEquals(24.0, result);
        if (result != 25.0) {
            throw new AssertionError(String.format("Expected %f, actual", 25.0, result));
        }
    }

    @Test
    public void canCalculatePerimeter() {
        Assertions.assertEquals(20, new Square(5.0).perimeter());
    }

    @Test
    public void cannotCreateSquareWithNegativeSide() {
        try {
            new Square(-5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }

    @Test
    public void testEquality() {
       var s1 = new Square(5.0);
       var s2 = new Square(5.0);
       Assertions.assertEquals(s1, s2);
    }

    //Потому что через true объекты не сравнивают
    @Test
    public void testEqualityFails() {
        var s1 = new Square(5.0);
        var s2 = new Square(5.0);
        Assertions.assertTrue(s1 == s2);
    }

    //А вот как надо
    @Test
    public void testEqualityTrue() {
        var s1 = new Square(5.0);
        var s2 = new Square(5.0);
        Assertions.assertTrue(s1.equals(s2));
    }

}


