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
        int index = indexOf(id);
        return index != -1 ? items[index] : null;
    }

    public Item[] findAll() {
        return Arrays.copyOf(items, size);
    }

    public Item[] findByName(String key) {
        int index = 0;
        Item[] temp = new Item[size];
        for (int i = 0; i < size; i++) {
            Item item = items[i];
            if (key.equals(item.getName())) {
                temp[index] = items[i];
                index++;
            }
        }
        return Arrays.copyOf(temp, index);
    }

    private int indexOf(int id) {
        for (int i = 0; i < size; i++) {
            if (items[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        boolean bo = index != -1;
        if (bo) {
            item.setId(id);
            items[index] = item;
        }
        return indexOf(id) != -1;
    }

    public boolean delete(int id) {
        int index = indexOf(id);
        boolean bo = index != -1;
        if (bo) {
            int length = size - 1 - index;
            System.arraycopy(items, index + 1, items, index, length);
            items[size - 1] = null;
            size--;
        }
        return bo;
    }
}