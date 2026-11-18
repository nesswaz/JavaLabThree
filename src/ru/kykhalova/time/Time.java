package ru.kykhalova.time;

public class Time {
    private int seconds;

    public Time(int seconds) {
        setTime(seconds); // вызываем сеттер, чтобы сработала проверка и обрезались сутки
    }

    public Time(int hours, int minutes, int seconds) {
        this(hours * 3600 + minutes * 60 + seconds);
    }

    // сеттеры с проверкой и отбрасыванием лишних суток
    public void setTime(int seconds) {
        if (seconds < 0) {
            throw new RuntimeException("Количество секунд не может быть отрицательным!");
        }
        this.seconds = seconds % (24 * 60 * 60); // сразу оставляем только текущие сутки
    }

    public void setTime(int hours, int minutes, int seconds) {
        setTime(hours * 3600 + minutes * 60 + seconds);
    }


    public int getHours() {
        return seconds / (60 * 60);
    }

    public int getMinutes() {
        return (seconds % (60 * 60)) / 60;
    }

    public int getSeconds() {
        return seconds % 60;
    }

    @Override
    public String toString() {
        int hours = getHours();
        int minutes = getMinutes();
        int remainingSeconds = getSeconds();

        String h = "" + hours;
        String m = "" + minutes;
        String s = "" + remainingSeconds;

        // приводим к красивому виду
        if (hours < 10) { h = "0" + h; }
        if (minutes < 10) { m = "0" + m; }
        if (remainingSeconds < 10) { s = "0" + s; }

        return h + ":" + m + ":" + s;
    }
}