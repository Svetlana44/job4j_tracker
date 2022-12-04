package ru.job4j.test2;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PutIfAbsentTest {
    @Test
    public void addNewElement() {
        Map<Integer, PutIfAbsent.User> map = new HashMap<>(
                Map.of(
                        1, new PutIfAbsent.User(1, "Name1"),
                        2, new PutIfAbsent.User(2, "Name2")
                )
        );
        PutIfAbsent put = new PutIfAbsent(map);
        PutIfAbsent.User user = new PutIfAbsent.User(1, "Name3");
        boolean rsl = put.addNewElement(user);
        assertEquals(rsl, false);
    }

    @Test
    public void addNewElementTrue() {
        Map<Integer, PutIfAbsent.User> map = new HashMap<>(
                Map.of(
                        1, new PutIfAbsent.User(1, "Name1"),
                        2, new PutIfAbsent.User(2, "Name2")
                )
        );
        PutIfAbsent put = new PutIfAbsent(map);
        PutIfAbsent.User user = new PutIfAbsent.User(3, "Name3");
        boolean rsl = put.addNewElement(user);
        assertEquals(rsl, true);
    }
}