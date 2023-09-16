package ru.job4j.tracker;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.stream.tracker.Item;
import ru.job4j.stream.tracker.ItemAscByName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemAscByNameTest {

    @Test
    public void whenCompareDesc() {
        List<Item> actual = Arrays.asList(
                new Item(5, "Item5"),
                new Item(2, "Item7"),
                new Item(7, "Item2"),
                new Item(1, "Item0")
        );
        List<Item> expected = new ArrayList<>();
        expected.add(new Item(1, "Item0"));
        expected.add(new Item(7, "Item2"));
        expected.add(new Item(5, "Item5"));
        expected.add(new Item(2, "Item7"));
        Collections.sort(actual, new ItemAscByName());
        Assert.assertEquals(expected, actual);
    }
}