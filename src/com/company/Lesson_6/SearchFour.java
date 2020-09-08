package com.company.Lesson_6;

public class SearchFour {
    private static int[] list;

    public static int[] newArray(int[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == 4) {
                int size = array.length - i - 1;
                list = new int[size];
                for (int j = (i + 1), k = 0; j < array.length; j++, k++) {
                    list[k] = array[j];
                }
                break;
            }else {
                throw new RuntimeException("No number four in the array");
            }
        }
        return list;
    }

    public static int[] getList() {
        return list;
    }
}
