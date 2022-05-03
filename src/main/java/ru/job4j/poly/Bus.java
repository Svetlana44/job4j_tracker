package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void ride() {
        System.out.println("Go");
    }

    @Override
    public void passengers() {
        System.out.println("Passenger");
    }

    @Override
    public double fill(int fuel) {
        double price = 0;
        return price;
    }
}
