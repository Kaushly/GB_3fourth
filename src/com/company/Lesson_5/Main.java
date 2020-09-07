package com.company.Lesson_5;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Machines[] machines = {
                new Car("car1"),
                new Car("car2"),
                new Car("car3"),
                new Bus("Bus1"),
                new Bus("Bus2"),
                new Bus("Bus3"),
                new Bus("Bus4"),
                new Truck("Truck1"),
                new Truck("Truck2"),
                new Truck("Truck3")
        };
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Station station = new Station();
        for (Machines auto : machines){
            executorService.submit(() -> auto.drive(station));
        }
        executorService.shutdown();
    }
}
