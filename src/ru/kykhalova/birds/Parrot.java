package ru.kykhalova.birds;

import java.util.Random;

public class Parrot extends Birds {
    private String text;

    public Parrot(String text) {
        super("Попугай");
        this.text = text;
    }

    @Override
    public void sing() {
        Random random = new Random();
        int n = random.nextInt(text.length()) + 1; // от 1 до всей длины текста
        System.out.println(text.substring(0, n)); // выводим первые n символов
    }
}