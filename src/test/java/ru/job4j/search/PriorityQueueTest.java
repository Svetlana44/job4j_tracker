package ru.job4j.search;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PriorityQueueTest {

    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        Task result = queue.take();
        assertEquals(result.getDesc(), "urgent");
    }
}