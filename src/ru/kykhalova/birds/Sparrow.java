package ru.kykhalova.birds;

public class Sparrow extends Birds {
    public Sparrow() {
        super("Воробей");
    }

    @Override
    public void sing() {
        System.out.println("чырык");
    }
}