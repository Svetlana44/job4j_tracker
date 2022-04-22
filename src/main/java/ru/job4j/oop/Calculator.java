package ru.job4j.oop;

public class Calculator {
    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public int multiply(int a) {
        return x * a;
    }

    public static int minus(int minus) {
        return minus - x;
    }

    public int divide(int div) {
        return div / x;
    }

    public int sumAllOperation(int all) {
        return sum(all) + multiply(all) + minus(all) + divide(all);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int rsl = calculator.multiply(5);
        System.out.println(rsl);
        System.out.println(sum(3));
        System.out.println(minus(3));
        System.out.println(calculator.divide(3));
        System.out.println(calculator.sumAllOperation(3));
    }
}
