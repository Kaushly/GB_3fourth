package com.company.Lesson_4.task1;

public class Main {
    public static void main(String[] args) {
        TurnLetter turnLetter = new TurnLetter();
        Thread a = new Thread(new Letter('A', turnLetter));
        Thread b = new Thread(new Letter('B', turnLetter));
        Thread c = new Thread(new Letter('C', turnLetter));
        a.start();
        b.start();
        c.start();
    }
}
