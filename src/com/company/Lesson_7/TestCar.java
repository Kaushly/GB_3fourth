package com.company.Lesson_7;

import java.lang.reflect.Field;

public class TestCar {
    private Class<Car> cClass;
    private boolean isCar;

    @BeforeSuite
    public boolean checkClassIsCar(Class<Car> cClass) {
        this.cClass = cClass;
        if (cClass == null) {
            isCar = false;
            System.out.println(false);
            return false;
        } else if (cClass.getSimpleName().equals("Car")) {
            isCar = true;
            System.out.println(true);
            return true;
        } else {
            isCar = false;
            return false;
        }
    }

    @Test
    @Priority()
    public boolean checkIfCarHasPublicFields() {
        if (!isCar) {
            return false;
        }
        Field[] fields = cClass.getFields();
        return fields.length == 2;
    }

    public boolean isCar() {
        return isCar;
    }

    @AfterSuite
    public void ending() {
        System.out.println("TestCar Ending");
    }
}
