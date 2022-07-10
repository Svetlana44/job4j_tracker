package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        String[] tmp1 = o1.split("/");
        String[] tmp2 = o2.split("/");
        String st1 = tmp1[0];
        String st2 = tmp2[0];
        int rsl = st2.compareTo(st1);
        if (rsl == 0) {
            return o1.compareTo(o2);
        }
        return rsl;
    }
}
