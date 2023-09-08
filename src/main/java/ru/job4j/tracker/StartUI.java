package ru.job4j.tracker;

import ru.job4j.tracker.store.SqlTracker;

import java.util.Arrays;
import java.util.List;

public class StartUI {
    private final Output out;

    public StartUI(Output out) {
        this.out = out;
    }

    public void init(Input input, Store memTracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.size()) {
                out.println("Wrong input, you can select: 0 .. " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.execute(input, memTracker);
        }

    }

    private void showMenu(List<UserAction> actions) {
        out.println("Menu.");
        for (int i = 0; i < actions.size(); i++) {
            out.println(i + ". " + actions.get(i).name());
        }
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());
        Store sqlTracker = new SqlTracker();
        List<UserAction> actions = Arrays.asList(
                new CreateAction(output),
                new ShowAllItemAction(output),
                new DeleteAction(output),
                new FindItemByIdAction(output),
                new FindItemsByNameAction(output),
                new ReplaceAction(output),
                new ExitAction(output)
        );
        new StartUI(output).init(input, sqlTracker, actions);
    }
}