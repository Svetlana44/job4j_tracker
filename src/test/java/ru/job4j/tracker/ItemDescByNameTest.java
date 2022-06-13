package ru.job4j.tracker;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemDescByNameTest {

    @Test
    public void whenCompareDesc() {
        List<Item> items = Arrays.asList(
                new Item(5, "Item5"),
                new Item(2, "Item7"),
                new Item(7, "Item2"),
                new Item(1, "Item0")
        );
        String expected = "Item0"
                + "Item2"
                + "Item5"
                + "Item7";
        String actual = "";
        Collections.sort(items, new ItemDescByName());
        for (Item item : items) {
            actual += item.getName();
        }
        Assert.assertEquals(expected, actual);
    }
}