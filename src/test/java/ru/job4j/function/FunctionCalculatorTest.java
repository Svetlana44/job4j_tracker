package ru.job4j.function;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FunctionCalculatorTest {
    @Test
    public void whenLinearFunctionThenLinearResults() {
        FunctionCalculator function = new FunctionCalculator();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result).containsAll(expected);
    }

    @Test
    public void whenQwadrThenQwadrResults() {
        FunctionCalculator functionCalculator = new FunctionCalculator();
        List<Double> expected = Arrays.asList(9D, 16D, 25D);
        List<Double> actual = functionCalculator.diapason(3, 6, x -> x * x);
        assertEquals(expected, actual);
    }

    @Test
    public void whenPowThenPowResults() {
        FunctionCalculator functionCalculator = new FunctionCalculator();
        List<Double> expected = Arrays.asList(2D, 4D, 8D);
        List<Double> actual = functionCalculator.diapason(1, 4, x -> Math.pow(2, x));
        assertEquals(expected, actual);
    }
}