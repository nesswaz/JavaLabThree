package ru.kykhalova.birds;

public abstract class Birds {
    protected String name;

    public Birds(String name) {
        this.name = name;
    }

    public abstract void sing();

    @Override
    public String toString() {
        return "Птица: " + name;
    }
}


