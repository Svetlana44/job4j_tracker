package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        String[] tmp1 = o1.split("/");
        String[] tmp2 = o2.split("/");
        int rsl = tmp2[0].compareTo(tmp1[0]);
        return rsl == 0 ? o1.compareTo(o2) : rsl;
    }
}
