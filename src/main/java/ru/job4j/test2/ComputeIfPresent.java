package ru.job4j.test2;

import java.util.Map;

public class ComputeIfPresent {
    public static Map<Integer, String> collectData(
            Map<Integer, String> name, Map<Integer, String> surname) {
        name.keySet().forEach(i -> name.computeIfPresent(i, (n, s) -> name.get(i) + " " + surname.get(i)));

        return name;
    }
}
