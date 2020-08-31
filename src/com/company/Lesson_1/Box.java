package com.company.Lesson_1;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruits> {
    private List<T> box = new ArrayList<>();

    public void add(T fruit, int count) {
        for (int i = 0; i < count; i++) {
            box.add(fruit);
        }
    }

    public float getWeight() {
        float weight = 0.0f;
//        for (T fruit : box) {
//            weight += fruit.getWeight();
//        }
        if (box.size() > 0) {

            weight = box.size() * box.get(0).getWeight();
        }
        return weight;
    }

    public boolean compare(Box<? extends Fruits> anotherBox) {
        return getWeight() == anotherBox.getWeight();
    }

    public void shiftFruitsIn(Box<T> anotherBox) {
        anotherBox.box.addAll(box);
        box.clear();
    }
}
