package ru.job4j.ex;

import org.junit.Test;

import static org.junit.Assert.*;

public class FactTest {
    @Test(expected = IllegalArgumentException.class)
    public void calcShouldThrowIllegalArgumentExeption_whenInputLessNull() {
        Fact fact = new Fact();
        fact.calc(-1);
    }

    @Test
    public void calcShouldThrowExeption_whenInputLessNull() {
        Fact fact = new Fact();
        assertThrows(IllegalArgumentException.class, () -> fact.calc(-1));
    }
}