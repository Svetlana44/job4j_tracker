package ru.job4j.tracker;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StartUITest {
 /*   @Test
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"7", "0"}
        );
        MemTracker memTracker = new MemTracker();
        List<UserAction> actions = Arrays.asList(
                new ExitAction(out)
        );
        new StartUI(out).init(in, memTracker, actions);
        String ln = System.lineSeparator();
        assertEquals(out.toString(),
                "Menu." + ln
                        + "0. Exit Program" + ln
                        + "Wrong input, you can select: 0 .. 0" + ln
                        + "Menu." + ln
                        + "0. Exit Program" + ln
                        + "App is closed." + ln
        );
    }

    @Test
    public void whenReplaceItemTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item one = memTracker.add(new Item("test1"));
        String replaceName = "New Test Name";
        Input in = new StubInput(
                new String[]{"0", String.valueOf(one.getId()), replaceName, "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new ReplaceAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, memTracker, actions);
        String ln = System.lineSeparator();
        assertEquals(out.toString(),
                "Menu." + ln
                        + "0. Edit item" + ln
                        + "1. Exit Program" + ln
                        + "=== Edit item ===" + ln
                        + "Заявка изменена успешно." + ln
                        + "Menu." + ln
                        + "0. Edit item" + ln
                        + "1. Exit Program" + ln
                        + "App is closed." + ln);
    }

    @Test
    public void whenFindByNameTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item one = memTracker.add(new Item("test1"));
        Input in = new StubInput(
                new String[]{"0", "test1", "1"});
        List<UserAction> actions = Arrays.asList(
                new FindItemsByNameAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, memTracker, actions);
        String ln = System.lineSeparator();
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
        assertEquals(out.toString(), "Menu." + ln
                + "0. Find items by name" + ln
                + "1. Exit Program" + ln
                + "=== Find items by name ===" + ln
                + one + ln
                + "Menu." + ln
                + "0. Find items by name" + ln
                + "1. Exit Program" + ln
                + "App is closed." + ln
        );
    }

    @Test
    public void whenFindByIdTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item one = memTracker.add(new Item("test1"));
        Input in = new StubInput(
                new String[]{"0", String.valueOf(one.getId()), "1"});
        List<UserAction> actions = Arrays.asList(
                new FindItemByIdAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, memTracker, actions);
        String ln = System.lineSeparator();
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
        assertEquals(out.toString(), "Menu." + ln
                + "0. Find item by id" + ln
                + "1. Exit Program" + ln
                + "=== Find item by id ===" + ln
                + one + ln
                + "Menu." + ln
                + "0. Find item by id" + ln
                + "1. Exit Program" + ln
                + "App is closed." + ln

        );

    }

    @Test
    public void whenFindAllTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item one = memTracker.add(new Item("test1"));
        Input in = new StubInput(
                new String[]{"0", "1"});
        List<UserAction> actions = Arrays.asList(
                new ShowAllItemAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, memTracker, actions);
        String ln = System.lineSeparator();
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
        assertEquals(out.toString(), "Menu." + ln
                + "0. Show all items" + ln
                + "1. Exit Program" + ln
                + "=== Show all items ===" + ln
                + one + ln
                + "Menu." + ln
                + "0. Show all items" + ln
                + "1. Exit Program" + ln
                + "App is closed." + ln
        );
    }

    @Test
    public void whenEditItem() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("Replaced item"));
        String replaced = "Item name edited";
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), "Item name edited", "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new ReplaceAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, memTracker, actions);
        assertEquals(memTracker.findAll().get(0).getName(), replaced);
    }

    @Test
    public void whenDeleteItem() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new DeleteAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, memTracker, actions);
        assertNull(memTracker.findById(item.getId()));
    }

    @Test
    public void whenCreateItem() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0", "Item name", "1"}
        );
        MemTracker memTracker = new MemTracker();
        List<UserAction> actions = Arrays.asList(
                new CreateAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, memTracker, actions);
        assertEquals(memTracker.findAll().get(0).getName(), "Item name");
    }  */
}