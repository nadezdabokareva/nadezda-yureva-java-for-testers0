package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTest {

    @Test
    public void canCalculateArea(){
        var s = new Square(5.0);
        var result = s.squareArea();
        Assertions.assertEquals(25.0, result);
    }

    @Test
    public void canCalculatePerimeter(){
        Assertions.assertEquals(20, new Square(5.0).perimeter());
    }
}
