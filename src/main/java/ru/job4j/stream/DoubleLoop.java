package ru.job4j.stream;

import java.util.stream.Stream;

public class DoubleLoop {
    public static void main(String[] args) {
        Stream.of(Suit.values())
                .flatMap(value -> Stream.of(Value.values())
                        .map(suit -> value + " " + suit))
                .forEach(System.out::println);
    }
}