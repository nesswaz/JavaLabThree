package ru.kykhalova.birds;

import java.util.Random;

public class Cuckoo extends Birds {
    public Cuckoo() {
        super("Кукушка");
    }

    @Override
    public void sing() {
        Random random = new Random();
        int count = random.nextInt(10) + 1;
        String result = "";
        for (int i = 0; i < count; i++) {
            result += "ку-ку ";
        }
        System.out.println(result.trim());
    }
}