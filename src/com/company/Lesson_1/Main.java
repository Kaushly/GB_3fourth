package com.company.Lesson_1;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        task1();
        task2();
        task3();
    }

    private static void task1() {
        Integer[] arr = {1, 2, 3, 4, 5};
        String[] word = {"A", "B", "C"};
        swap(arr, 2, 4);
        swap(word, 2, 1);
    }

    private static void task2() {
        String[] word = {"A", "B", "C"};
        asList(word);
    }

    private static void task3() {
        Box<Orange> orange1 = new Box<>();
        Box<Orange> orange2 = new Box<>();
        Box<Apple> apple1 = new Box<>();
        Box<Apple> apple2 = new Box<>();
        orange1.add(new Orange(), 10);
        orange2.add(new Orange(), 12);
        apple1.add(new Apple(), 10);
        apple2.add(new Apple(), 10);
        System.out.println(apple1.compare(orange1));
        orange1.shiftFruitsIn(orange2);
        orange1.getWeight();
        orange2.getWeight();
    }

    private static void swap(Object[] array, int num1, int num2) {
        Object timeArray = array[num1];
        array[num1] = array[num2];
        array[num2] = timeArray;
        for (Object t : array) {
            System.out.print(t + " ");
        }
        System.out.println();
    }

    private static <T> void asList(T[] arr) {
        ArrayList<T> list = new ArrayList<>(Arrays.asList(arr));
    }
}
