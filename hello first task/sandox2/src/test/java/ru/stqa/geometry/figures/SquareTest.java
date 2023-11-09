package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTest {

    @Test
    public void canCalculateArea(){
        var result = Square.squareArea(5.0);
        Assertions.assertEquals(25.0, result);
    }
}
