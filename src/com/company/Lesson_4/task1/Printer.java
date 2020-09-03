package com.company.Lesson_4.task1;

public class Printer {
    private String name = "A";
    private final int REPLAY = 5;


    public synchronized void letterOne() {
        for (int i = 0; i < REPLAY; i++) {
            while (!name.equals("A")) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(name);
            name = "B";
            notifyAll();
        }
    }

    public synchronized void letterTwo() {
        for (int i = 0; i < REPLAY; i++) {
            while (!name.equals("B")) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(name);
            name = "C";
            notifyAll();
        }
    }

    public synchronized void letterThree() {
        for (int i = 0; i < REPLAY; i++) {
            while (!name.equals("C")) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(name);
            name = "A";
            notifyAll();
        }
    }

}
