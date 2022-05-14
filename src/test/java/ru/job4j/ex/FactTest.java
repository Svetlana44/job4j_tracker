package ru.job4j.ex;

import org.junit.Test;

import static org.junit.Assert.*;

public class FactTest {
    @Test(expected = IllegalArgumentException.class)
    public void calcShouldThrowIllegalArgumentExeptionWhenInputLessNull() {
        Fact fact = new Fact();
        fact.calc(-1);
    }

    @Test
    public void calcShouldThrowExeptionWhenInputLessNull() {
        Fact fact = new Fact();
        assertThrows(IllegalArgumentException.class, () -> fact.calc(-1));
    }
}