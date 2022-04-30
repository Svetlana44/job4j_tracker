package ru.job4j.io;

import java.util.Scanner;

public class Matches {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Игра 11.");
        boolean turn = true;
        int count = 11;
        while (count > 0) {
            String player = turn ? "Первый игрок" : "Второй игрок";
            System.out.println(player + " введите число от 1 до 3:");
            int matches = Integer.parseInt(input.nextLine());
            if (matches > 0 && matches < 4) {
                if (count - matches >= 0) {
                    count -= matches;
                    System.out.println(count + " спичек осталось на столе");
                    turn = !turn;
                } else {
                    System.out.println("введите количество не более " + (count));
                }
            } else {
                System.out.println("Неправильно введено количество спичек, попробуйте ещё раз.");
            }
        }
        if (!turn) {
            System.out.println("Выиграл первый игрок");
        } else {
            System.out.println("Выиграл второй игрок");
        }
    }
}
