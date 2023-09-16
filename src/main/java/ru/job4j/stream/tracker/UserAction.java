package ru.job4j.stream.tracker;

public interface UserAction {
    String name();

    boolean execute(Input input, Store memTracker);
}
