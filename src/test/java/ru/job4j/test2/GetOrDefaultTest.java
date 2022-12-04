package ru.job4j.test2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GetOrDefaultTest {
    @Test
    public void whenGetList() {
        List<String> list = List.of("one", "two", "three");
        List<String> second = List.of("four", "five", "six");
        GetOrDefault get = new GetOrDefault();
        get.addValue(1, list);
        get.addValue(2, second);
        List<String> value = get.getValue(2);
        Assertions.assertEquals(value, second);
    }

    @Test
    public void whenGetListEmpty() {
        List<String> list = List.of("one", "two", "three");
        List<String> second = List.of("four", "five", "six");
        GetOrDefault get = new GetOrDefault();
        get.addValue(1, list);
        get.addValue(2, second);
        List<String> value = get.getValue(10);
        Assertions.assertEquals(value, new ArrayList<>());
    }

    @Test
    public void whenAddListWithDuplicateKey() {
        List<String> list = List.of("one", "two", "three");
        List<String> second = List.of("four", "five", "six");
        List<String> third = List.of("seven", "eight", "nine");
        GetOrDefault get = new GetOrDefault();
        get.addValue(1, list);
        get.addValue(2, second);
        get.addValue(1, third);
        List<String> value = get.getValue(1);
        Assertions.assertEquals(value, list);
    }
}