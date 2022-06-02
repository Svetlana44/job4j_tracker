package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class Tracker {
    private final List<Item> items = new ArrayList<>();
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items.add(size++, item);
        return item;
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }

    public List<Item> findAll() {
        return (List<Item>) ((ArrayList<Item>) items).clone();
    }

    public List<Item> findByName(String key) {
        int index = 0;
        List<Item> temp = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Item item = items.get(i);
            if (key.equals(item.getName())) {
                temp.add(index, items.get(i));
                index++;
            }
        }
        return (List<Item>) ((ArrayList<Item>) temp).clone();
    }

    private int indexOf(int id) {
        for (int i = 0; i < size; i++) {
            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        boolean result = index != -1;
        if (result) {
            item.setId(id);
            items.add(index, item);
        }
        return index != -1;
    }

    public boolean delete(int id) {
        int index = indexOf(id);
        boolean result = index != -1;
        if (result) {
            items.remove(index);
            size--;
        }
        return result;
    }
}