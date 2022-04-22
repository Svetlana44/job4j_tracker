package ru.job4j.oop;

public class Cat {
    private String food;
    private String name;

    public void show() {
        System.out.println("There are " + this.name + "'s food: " + this.food);
    }

    public void eat(String meat) {
        this.food = meat;
    }

    public void givNick(String nick) {
        this.name = nick;
    }

    public static void main(String[] args) {
        Cat gav = new Cat();
        gav.eat("kotleta");
        gav.name = "Gav";
        gav.show();
        Cat black = new Cat();
        black.eat("fish");
        black.name = "Black";
        black.show();
    }
}
