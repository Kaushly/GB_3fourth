package com.company.Lesson_5;

import java.util.concurrent.Semaphore;

public class Station {
    Semaphore semaphore = new Semaphore(2);

    public void refuel(String name){
        try {
            System.out.printf("Машина %s заехала на заправку%n", name);
            semaphore.acquire();
            Thread.sleep(5000);
            System.out.printf("%s заправилась и уезжает!%n", name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphore.release();
    }
}
