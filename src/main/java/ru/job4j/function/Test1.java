package ru.job4j.function;

import java.util.Arrays;
import java.util.Comparator;

public class Test1<names> {
    public static void main(String[] args) {
        String[] names = {
                "Ivan"
        };
        Comparator<String> comparator = (left, right) -> {
            System.out.println("Execute run:");
            return left.compareTo(right);
        };
        Arrays.sort(names,
                comparator);
        for (String s : names) {
            System.out.println(s);
        }
    }
}
