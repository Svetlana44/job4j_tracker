package ru.job4j.oop;

import static org.hamcrest.Matchers.closeTo;

import org.junit.Assert;
import org.junit.Test;

public class TriangleTest {

    @Test
    public void when00and40and04Then8() {
        Point a = new Point(0, 0);
        Point b = new Point(4, 0);
        Point c = new Point(0, 4);
        Triangle triangle = new Triangle(a, b, c);
        double rsl = triangle.area();
        Assert.assertThat(rsl, closeTo(8, 0.001));
    }

    @Test
    public void when01and41and21ThenMinus1() {
        Point a = new Point(0, 1);
        Point b = new Point(4, 1);
        Point c = new Point(2, 1);
        Triangle triangle = new Triangle(a, b, c);
        double rsl = triangle.area();
        Assert.assertEquals(rsl, -1, 0.01);
    }
}