package ru.job4j.ex;

public class FindEl {
    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        int rsl = -1;
        for (int i = 0; i < value.length; i++) {
            if (key.equals(value[i])) {
                return i;
            }
        }
        throw new ElementNotFoundException("Not found");
    }

    public static void main(String[] args) {
        try {
            indexOf(new String[]{"a", "b", "c"}, "x");
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }
}
