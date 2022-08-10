package ru.job4j.pojo;

public class Book {
    public class PrintNTo0 {
        public static void main(String[] args) {
            out(10);
        }

        public static void out(int n) {
            for (int i = n - 1; i > -1; i--) {
                System.out.println(i);
            }
        }
    }
}
