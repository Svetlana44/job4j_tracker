package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int rsl = 0;
        int leftLenth = left.length();
        int rightLength = right.length();
        int length = Math.min(leftLenth, rightLength);
        for (int i = 0; i < length; i++) {
            rsl = Character.compare(left.charAt(i), right.charAt(i));
            if (rsl == 0) {
                if (i == length - 1) {
                    if (leftLenth == rightLength) {
                        return 0;
                    } else if (leftLenth < rightLength) {
                        return -1;
                    } else if (leftLenth > rightLength) {
                        return 1;
                    }
                }
                continue;
            } else {
                return rsl;
            }
        }
        return rsl;
    }
}
