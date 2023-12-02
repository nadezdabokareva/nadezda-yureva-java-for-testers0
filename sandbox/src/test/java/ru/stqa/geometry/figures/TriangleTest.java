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

    @Test
    public void checkEqualsDiffTriangle2() {
        var a = 2;
        var b = 3;
        var c = 4;
        var triangle = new Triangle(2, 3,4);
        var triangle1 = new Triangle(2, 4, 3);
        Assertions.assertEquals(triangle, triangle1);
    }

    @Test
    public void checkEqualsDiffTriangle3() {
        var t1 = new Triangle(2, 3, 4);
        var t2 = new Triangle(2, 4, 3);
        Assertions.assertTrue(t1.equals(t2));
    }

    @Test
    public void checkEqualsDiffTriangle4() {
        var a = 2;
        var b = 3;
        var c = 4;
        var triangle = new Triangle(a, b, c);
        var triangle1 = new Triangle(b, a, c);
        Assertions.assertEquals(triangle, triangle1);
    }
    @Test
    public void checkEqualsDiffTriangle5() {
        var a = 2;
        var b = 3;
        var c = 4;
        var triangle = new Triangle(c, b, a);
        var triangle1 = new Triangle(b, a, c);
        Assertions.assertEquals(triangle, triangle1);
    }
    @Test
    public void checkEqualsDiffTriangle6() {
        var a = 3;
        var b = 4;
        var c = 5;
        var triangle = new Triangle(a, b, c);
        var triangle1 = new Triangle(b, c, a);
        Assertions.assertEquals(triangle, triangle1);
    }

    @Test
    public void checkEqualsDiffTriangle7() {
        var a = 2;
        var b = 3;
        var c = 4;
        var triangle = new Triangle(a, b, c);
        var triangle1 = new Triangle(c, b, a);
        Assertions.assertEquals(triangle, triangle1);
    }


}

