package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConvertListTest {

    @Test
    public void whenTwoList() {
        List<int[]> in = new ArrayList<>();
        in.add(new int[]{1});
        in.add(new int[]{2, 3});
        List<Integer> expect = Arrays.asList(1, 2, 3);
        assertEquals(ConvertList.convert(in), expect);
    }
}