package com.company.Lesson_6;

public class CheckOneEndFour {

    public static boolean oneEndFour(int[] arr) {
        boolean one = false;
        boolean four = false;
        for (int num : arr) {
            if (num == 1) {
                one = true;
            }
            if (num == 4) {
                four = true;
            }
            if(num != 1 && num != 4){
                return false;
            }
        }
        return one && four;
    }
}
