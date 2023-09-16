package ru.job4j.tracker;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.stream.tracker.Item;
import ru.job4j.stream.tracker.ItemDescByName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemDescByNameTest {

    @Test
    public void whenCompareAsc() {
        List<Item> actual = Arrays.asList(
                new Item(5, "Item5"),
                new Item(2, "Item7"),
                new Item(7, "Item2"),
                new Item(1, "Item0")
        );
        List<Item> expected = new ArrayList<>();
        expected.add(new Item(2, "Item7"));
        expected.add(new Item(5, "Item5"));
        expected.add(new Item(7, "Item2"));
        expected.add(new Item(1, "Item0"));
        Collections.sort(actual, new ItemDescByName());
        Assert.assertEquals(expected, actual);
    }
}