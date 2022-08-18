package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<Person>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        Predicate<Person> personPredicateName = p -> p.getName().equals(key);
        Predicate<Person> personPredicateSurnameName = p -> p.getSurname().equals(key);
        Predicate<Person> personPredicatePhone = p -> p.getPhone().equals(key);
        Predicate<Person> personPredicateAddress = p -> p.getAddress().equals(key);

        Predicate<Person> combine = personPredicateName.or(personPredicateSurnameName.or(personPredicatePhone.or(personPredicateAddress)));
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}