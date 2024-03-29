package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.stream.tracker.*;

import static org.junit.Assert.*;

public class ValidateInputTest {
    @Test
    public void whenInvalidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"one", "1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertEquals(selected, 1);
    }

    @Test
    public void whenValidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(new String[]{"3"});
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertEquals(selected, 3);
    }

    @Test
    public void whenSomeValid() {
        Output out = new StubOutput();
        Input in = new StubInput(new String[]{"1", "2", "3"});
        ValidateInput input = new ValidateInput(out, in);
        int selected1 = input.askInt("Enter menu:");
        assertEquals(selected1, 1);
        int selected2 = input.askInt("Enter menu:");
        assertEquals(selected2, 2);
        int selected3 = input.askInt("Enter menu:");
        assertEquals(selected3, 3);
    }

    @Test
    public void whenMinus() {
        Output out = new StubOutput();
        Input in = new StubInput(new String[]{"-1"});
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertEquals(selected, -1);
    }
}