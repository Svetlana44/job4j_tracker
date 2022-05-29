package ru.job4j.search;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        ArrayList<Person> persons = phones.find("Petr");
        Assert.assertEquals(persons.get(0).getSurname(), "Arsentev");
    }

    @Test
    public void whenEmpty() {
        PhoneDictionary phones = new PhoneDictionary();
        ArrayList<Person> persons = phones.find("Petr");
        Assert.assertTrue(persons.isEmpty());
    }
}