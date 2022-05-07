package ru.job4j.pojo;

import static org.hamcrest.Matchers.is;

import org.junit.Assert;
import org.junit.Test;

public class LicenseTest {

    @Test
    public void eqName() {
        License first = new License();
        first.setCode("audio");
        License second = new License();
        second.setCode("audio");
        Assert.assertEquals(first, second);
    }
}