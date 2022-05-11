package ru.job4j.tracker;

public class StubOutput implements Output {
    private final StringBuilder buffer = new StringBuilder();

    @Override
    public void println(Object obj) {

    }

    @Override
    public String toString() {
        return buffer.toString();
    }
}
