package ru.job4j.oop;

public class PolyUsage {
    public static void main(String[] args) {
        Vehicle plane = new Plane();
        Vehicle trane = new Trane();
        Vehicle bus = new Bus();
        Vehicle [] vehicles = {plane,trane,bus};
        for (Vehicle vehicle:vehicles) {
            vehicle.move();
        }
    }
}
