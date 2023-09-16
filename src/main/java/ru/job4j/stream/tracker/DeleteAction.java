package ru.job4j.stream.tracker;

import java.util.List;
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
        Optional<List<Item>> optionalAll = Optional.ofNullable(memTracker.findAll());
        if (!optionalAll.get().isEmpty()) {
            out.println("=== Delete item ===");
            int id = input.askInt("Enter id: ");

            Optional<String> optionalItem = Optional.ofNullable(memTracker.findById(id).getName());
            if (optionalItem.isEmpty()) {
                out.println("Ошибка удаления заявки. <-SqlTrecker->");
            } else {
                memTracker.delete(id);
                out.println("Заявка удалена успешно. <-SqlTrecker->");
            }
        } else {
            out.println("Заявок ещё нет. <-SqlTrecker->");
        }
        return true;
    }
}
