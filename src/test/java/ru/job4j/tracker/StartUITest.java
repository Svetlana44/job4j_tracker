package ru.job4j.tracker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StartUITest {

    @Test
    public void whenAddItem() {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        StartUI.createItem(input, tracker);
        Item created = tracker.findAll()[0];
        Item expected = new Item("Fix PC");
        assertEquals(created.getName(), expected.getName());
    }
}