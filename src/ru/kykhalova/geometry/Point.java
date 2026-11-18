package ru.kykhalova.geometry;

public class Point {
    protected int x; // protected, чтобы подкласс PointXYZ видел координаты напрямую
    protected int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // сравнение точек по координатам
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point other = (Point) obj;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public String toString() {
        return "{" + x + ";" + y + "}";
    }
}
