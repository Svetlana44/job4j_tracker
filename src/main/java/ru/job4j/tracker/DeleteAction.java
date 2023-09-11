package ru.job4j.tracker;

import java.util.Optional;

public class DeleteAction implements UserAction {
    private final Output out;

    public DeleteAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete item";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        out.println("=== Delete item ===");
        int id = input.askInt("Enter id: ");
        memTracker.delete(id);
        Optional<Item> optionalItem = Optional.ofNullable(memTracker.findById(id));
        if (optionalItem.isEmpty()) {
            System.out.println("Ошибка удаления заявки. <-SqlTrecker->");
            return false;
        }
        System.out.println("Заявка удалена успешно. <-SqlTrecker->");
        return true;
    }
}
