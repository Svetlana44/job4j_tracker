package ru.job4j.tracker;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item findById(int id) {
        Item rsl = null;
        for (int index = 0; index < size; index++) {
            Item item = items[index];
            if (item.getId() == id) {
                rsl = item;
                break;
            }
        }
        return rsl;
    }

    public Item[] findAll() {
        Item[] temp = new Item[items.length];
        int size = 0;
        for (Item item : items) {
            if (item != null) {
                temp[size] = item;
                size++;
            }
        }
        return Arrays.copyOf(temp, size);
    }

    public Item[] findByName(String key) {
        int index = 0;
        Item[] temp = new Item[100];
        for (int i = 0; i < size; i++) {
            Item item = items[i];
            if (key.equals(item.getName())) {
                temp[i] = items[i];
                index++;
            }
        }
        return Arrays.copyOf(temp, index);
    }
}