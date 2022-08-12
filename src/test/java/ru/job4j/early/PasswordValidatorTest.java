package ru.job4j.early;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
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
        String expected = "Invalid password.";
        String actual = validate(password);
        assertEquals(expected, actual);
    }

    @Test
    public void validateWhenInvalidFlagLower() {
        String password = "321567AA@";
        String expected = "Invalid password.";
        String actual = validate(password);
        assertEquals(expected, actual);
    }

    @Test
    public void validateWhenInvalidflagDigit() {
        String password = "aaaaaaaAA@";
        String expected = "Invalid password.";
        String actual = validate(password);
        assertEquals(expected, actual);
    }

    @Test
    public void validateWhenInvalidFlagSymbol() {
        String password = "321567AAa";
        String expected = "Invalid password.";
        String actual = validate(password);
        assertEquals(expected, actual);
    }

    @Test
    public void validateWhenInvalidFlagSubStr() {
        String password = "passwordAa@";
        String expected = "Invalid password.";
        String actual = validate(password);
        assertEquals(expected, actual);
    }

    @Test
    public void validateWhenInvalidLength() {
        String password = "1dAa@";
        String expected = "Invalid password.";
        String actual = validate(password);
        assertEquals(expected, actual);
    }

    @Test
    public void validateWhenNull() {
        String password = null;
        Assert.assertThrows("Password can't to be is null", IllegalArgumentException.class, () -> PasswordValidator.validate(password));
    }

}
