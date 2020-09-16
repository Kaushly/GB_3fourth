package com.company.Lesson_7;

public class Main {
    public static void main(String[] args) {
        Car car = new Car("BMW");
        Car car1 = new Car("BMW 525");
        Testing.start(car.getClass());
        Testing.start(car1.getClass());
    }
}
