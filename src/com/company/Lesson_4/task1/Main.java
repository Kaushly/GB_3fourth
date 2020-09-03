package com.company.Lesson_4.task1;

public class Main {
    public static void main(String[] args) {
//        TurnLetter turnLetter = new TurnLetter();
        Printer printer = new Printer();
        new Thread(() -> printer.letterOne()).start();
        new Thread(() -> printer.letterTwo()).start();
        new Thread(() -> printer.letterThree()).start();
    }
}
