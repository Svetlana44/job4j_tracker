package ru.job4j.tracker;

public class StartUI {
    public static void showAllItem(Input input, Tracker tracker) {
        System.out.println("=== Show all items ===");
        Item[] items = tracker.findAll();
        if (items.length > 0) {
            for (Item item : items) {
                System.out.println(item);
            }
        } else {
            System.out.println("Хранилище еще не содержит заявок");
        }
    }

    public static void findItemById(Input input, Tracker tracker) {
        System.out.println("=== Find item by id ===");
        int id = input.askInt("Enter id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println(item);
        } else {
            System.out.println("Заявка с введенным id: " + id + " не найдена.");
        }
    }

    public static void findItemsByName(Input input, Tracker tracker) {
        System.out.println("=== Find items by name ===");
        String name = input.askStr("Enter name: ");
        Item[] items = tracker.findByName(name);
        if (items.length > 0) {
            for (Item item : items) {
                System.out.println(item);
            }
        } else {
            System.out.println("Заявки с именем: " + name + " не найдены.");
        }
    }

    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            showMenu();
            int select = input.askInt("Select: ");
            if (select == 0) {
                CreateAction createAction = new CreateAction();
                createAction.execute(input, tracker);
            } else if (select == 1) {
                showAllItem(input, tracker);
            } else if (select == 2) {
                ReplaceAction replaceAction = new ReplaceAction();
                replaceAction.execute(input, tracker);
            } else if (select == 3) {
                DeleteAction deleteAction = new DeleteAction();
                deleteAction.execute(input, tracker);
            } else if (select == 4) {
                findItemById(input, tracker);
            } else if (select == 5) {
                findItemsByName(input, tracker);
            } else if (select == 6) {
                run = false;
            }
        }

    }

    private void showMenu() {
        String[] menu = {
                "Add new Item", "Show all items", "Edit item",
                "Delete item", "Find item by id", "Find items by name",
                "Exit Program"
        };
        for (int i = 0; i < menu.length; i++) {
            System.out.println(i + ". " + menu[i]);
        }
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);
    }
}