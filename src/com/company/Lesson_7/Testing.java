package com.company.Lesson_7;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Testing {
    public static void start(Class aClass){
        Class[] classes = {TestCar.class};
        for(Class c : classes){
            Method[] methods = c.getMethods();

            List<Method> star = new ArrayList<>();
            List<Method> test = new ArrayList<>();
            List<Method> stop = new ArrayList<>();

            for(Method m : methods){
                if(m.isAnnotationPresent(BeforeSuite.class)){
                    star.add(m);
                }else if(m.isAnnotationPresent(Test.class)){
                    test.add(m);
                }else if(m.isAnnotationPresent(AfterSuite.class)){
                    stop.add(m);
                }
                if(star.size() > 1){
                    throw new RuntimeException();
                }

                sortMethod(star);
            }
        }
    }

    public static void sortMethod(List<Method> methods){
        methods.sort((m1, m2) -> {
            int m1Prior = m1.getAnnotation(Priority.class).priority();
            int m2Prior = m2.getAnnotation(Priority.class).priority();
            return Integer.compare(m1Prior, m2Prior);
        });
    }


}
