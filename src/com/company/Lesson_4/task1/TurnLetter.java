package com.company.Lesson_4.task1;

public class TurnLetter {
    private boolean print = true;


    public synchronized void printLetter(String letter){
        System.out.print(letter);
        while (!print) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        print = false;
        notifyAll();

    }

    public synchronized boolean donePrint(){
        while (print) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        print = true;
        notifyAll();
        return true;
    }
}
