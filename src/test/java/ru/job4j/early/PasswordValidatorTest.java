package ru.job4j.early;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static ru.job4j.early.PasswordValidator.validate;

public class PasswordValidatorTest {

    @Test
    public void validateWhenValid() {
        String password = "321567aA@";
        String expected = "Valid password.";
        String actual = validate(password);
        assertEquals(expected, actual);
    }

    @Test
    public void validateWhenInvalidFlagUpper() {
        String password = "321567aa@";
        assertThrows("Invalid password.",
                IllegalArgumentException.class, () -> validate(password));
    }

    @Test
    public void validateWhenInvalidFlagLower() {
        String password = "321567AA@";
        assertThrows("Invalid password.",
                IllegalArgumentException.class, () -> validate(password));
    }

    @Test
    public void validateWhenInvalidflagDigit() {
        String password = "aaaaaaaAA@";
        assertThrows("Invalid password.",
                IllegalArgumentException.class, () -> validate(password));
    }

    @Test
    public void validateWhenInvalidFlagSymbol() {
        String password = "321567AAa";
        assertThrows("Invalid password.",
                IllegalArgumentException.class, () -> validate(password));
    }

    @Test
    public void validateWhenInvalidSubStr() {
        String password = "passwordAa@";
        assertThrows("Password can't to contein one from substrings.",
                IllegalArgumentException.class, () -> PasswordValidator.validate(password));
    }

    @Test
    public void validateWhenInvalidLength() {
        String password = "1dAa@";
        assertThrows("Invalid password. Length mast be less then 8 and more then 32.",
                IllegalArgumentException.class, () -> PasswordValidator.validate(password));
    }

    @Test
    public void validateWhenNull() {
        String password = null;
        assertThrows("Password can't to be is null",
                IllegalArgumentException.class, () -> PasswordValidator.validate(password));
    }

}
