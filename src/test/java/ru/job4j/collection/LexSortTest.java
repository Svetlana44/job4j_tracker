package ru.job4j.collection;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class LexSortTest {
    @Test
    public void sortNum1and2and10() {
        String[] input = {
                "10. Task.",
                "1. Task.",
                "2. Task."
        };
        String[] out = {
                "1. Task.",
                "2. Task.",
                "10. Task."
        };
        Arrays.sort(input, new LexSort());
        assertEquals(input, out);
    }
}