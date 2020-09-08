package com.company.Lesson_6;

import java.util.Arrays;

public class Main {
    private static int[] taskOneArray = {1, 2, 4, 4, 2, 3, 4, 1, 7};
    private static int[] taskOneArray1 = {1, 2, 2, 3, 1, 7};
    private static int[] taskTwoArr = {1, 1, 1, 1, 4, 4, 4, 1};
    private static int[] taskTwoArr1 = {1, 1, 1, 1, 1};
    private static int[] taskTwoArr2 = {4, 4, 4, 4, 4};

    public static void main(String[] args) {
//      Task 1:
        System.out.println(Arrays.toString(SearchFour.newArray(taskOneArray)));
//        System.out.println(Arrays.toString(SearchFour.newArray(taskOneArray1)));
//      Task 2:
        System.out.println(CheckOneEndFour.oneEndFour(taskTwoArr));
        System.out.println(CheckOneEndFour.oneEndFour(taskTwoArr1));
        System.out.println(CheckOneEndFour.oneEndFour(taskTwoArr2));
    }

    public static int[] getTaskOneArray() {
        return taskOneArray;
    }

    public static int[] getTaskTwoArr() {
        return taskTwoArr;
    }

    public static int[] getTaskTwoArr1() {
        return taskTwoArr1;
    }

    public static int[] getTaskTwoArr2() {
        return taskTwoArr2;
    }
}
