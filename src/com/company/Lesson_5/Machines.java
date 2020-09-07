package com.company.Lesson_5;

import java.sql.SQLOutput;

public abstract class Machines {
    public String name;
    public float TANK;
    public final float EXECUTE;

    public Machines(String name, float TANK, float EXECUTE) {
        this.name = name;
        this.TANK = TANK;
        this.EXECUTE = EXECUTE;
    }

    public void drive(Station station) {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                Раскоментировать while если необходимо, что бы после заправки машина вновь двигалась и заправлялась
//                while (true) {
                    float fuel = TANK;
                    while (fuel >= EXECUTE) {
                        try {
                            Thread.sleep(3000);
                            fuel -= EXECUTE;
                            System.out.printf("У %s осталось в баке %s бензина%n", name, fuel);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.printf("Бензин у %s закончился%n", name);
                    station.refuel(name);
                }
//            }
        }).start();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTANK() {
        return TANK;
    }

    public void setTANK(float TANK) {
        this.TANK = TANK;
    }

    public float getEXECUTE() {
        return EXECUTE;
    }
}
