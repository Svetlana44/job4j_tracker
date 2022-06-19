package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String[] strLeft = left.split(". ");
        String[] strRight = right.split(". ");
        int rsl = 0;
        int intLeft = Integer.parseInt(strLeft[0]);
        int intRight = Integer.parseInt(strRight[0]);
        rsl = Integer.compare(intLeft, intRight);
        if (rsl < 0) {
            return -1;
        } else if (rsl > 0) {
            return 1;
        }
        return rsl;
    }
}
