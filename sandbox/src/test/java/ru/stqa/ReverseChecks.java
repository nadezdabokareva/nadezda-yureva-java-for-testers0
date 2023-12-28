package ru.stqa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReverseChecks {
    @Test
    void testSqrt(){
        var input = 4.0;
        var result = Math.sqrt(input);
        Assertions.assertEquals(2, result);
    }

    @Test
    void testReverseSqrt(){
        var input = 4.0;
        var result = Math.sqrt(input);
        var reverse = result* result;
        Assertions.assertEquals(input, reverse, 0.00001);
    }

    @Test
    void sortList(){
        var input = new ArrayList<>(List.of(3, 7, 4, 9, 0));
        input.sort(Integer::compareTo);

        Assertions.assertEquals(List.of(0, 3, 4,7,9), input);

        for (int i = 0; i < input.size()-1; i++) {
            assertTrue(input.get(i) <= input.get(i+1));
        }
    }
}
