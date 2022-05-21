package ru.job4j.tracker;

public final class SingleTracker {
    private static SingleTracker singleTracker = null;
    private Tracker tracker = new Tracker();
    private String[] massages = new String[1000];
    private int index;

    private SingleTracker() {
    }

    public static SingleTracker getSingleTracker() {
        if (singleTracker == null) {
            singleTracker = new SingleTracker();
        }
        return singleTracker;
    }

    public Item add(Item item) {
        return tracker.add(item);
    }

    public Item findById(int id) {
        return null;
    }

    public Item[] findAll() {
        return null;
    }

    public Item[] findByName(String key) {
        return null;
    }

    private int indexOf(int id) {
        return -1;
    }

    public boolean replace(int id, Item item) {
        return index != -1;
    }

    public boolean delete(int id) {
        boolean result = index != -1;
        return result;
    }
}