package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangleTest {
    @Test
    public void cannotCreateRectangleWithNegativeSide() {
        try {
            new Rectangle(-5.0, 5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }
    @Test
    public void assertRectangleEquals() {
       var r1 = new Rectangle(5, 4);
       var r2 = new Rectangle(5, 4);
       Assertions.assertTrue(r1.equals(r2));
    }
    @Test
    public void assertDiffRectangleEquals() {
        var r1 = new Rectangle(5, 4);
        var r2 = new Rectangle(4, 5);
        Assertions.assertTrue(r1.equals(r2));
    }
}
