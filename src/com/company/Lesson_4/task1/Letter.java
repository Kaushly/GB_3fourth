package com.company.Lesson_4.task1;

public class Letter implements Runnable {
    private final int REPLAY = 5;
    private final char name;

    private TurnLetter turnLetter;

    public Letter(char name, TurnLetter turnLetter) {
        this.name = name;
        this.turnLetter = turnLetter;
    }

    @Override
    public void run() {
        for(int i = 0; i < REPLAY;  i++){
            try {
                Thread.sleep(300);
                System.out.print(this.name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Letter{" +
                "name=" + name +
                '}';
    }
}
