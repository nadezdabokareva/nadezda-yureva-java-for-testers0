package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {

    @Test
    public void trianglePerimeterTest(){
        var p = (new Triangle(1, 1, 1)).perimeter();
        Assertions.assertEquals(3, p);
    }

    @Test
    public void triangleSquareTest(){
        var expected = 11.61895003862225;
        var result = (new Triangle(4, 6,8).square());
        Assertions.assertEquals(expected, result);
    }
    @Test
    public void cannotCreateTriangleWithNegativeSide() {
        try {
            new Triangle(-5.0, 5.0, 5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }

    @Test
    public void checkSunOfTwoSideOfTriangle() {
        try {
            new Triangle(1.0, 2.0, 5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }

    @Test
    public void checkEqualsTriangle() {
        var t1 = new Triangle(2, 3, 5);
        var t2 = new Triangle(2, 3, 5);
        Assertions.assertTrue(t1.equals(t2));
    }

    @Test
    public void checkEqualsDiffTriangle() {
        var t1 = new Triangle(3, 4, 5);
        var t2 = new Triangle(4, 5, 3);
        Assertions.assertTrue(t1.equals(t2));
    }
}
