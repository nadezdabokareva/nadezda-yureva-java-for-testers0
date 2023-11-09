package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {

    @Test
    public void trianglePerimeterTest(){
        var result = Triangle.perimeter(1, 1, 1);
        Assertions.assertEquals(3, result);
    }

    @Test
    public void triangleSquareTest(){
        var expected = 11.61895003862225;
        var result = Triangle.square(4, 6,8);
        Assertions.assertEquals(expected, result);
    }
}
