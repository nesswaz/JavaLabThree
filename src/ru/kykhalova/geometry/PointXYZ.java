package ru.kykhalova.geometry;

// 3.5 трехмерная точка наследует обычную Точку
public class PointXYZ extends Point {
    private int z;

    public PointXYZ(int x, int y, int z) {
        super(x, y); // вызываем конструктор суперкласса Point
        this.z = z;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        PointXYZ other = (PointXYZ) obj;
        return this.z == other.z;
    }

    @Override
    public String toString() {
        return "{" + x + ";" + y + ";" + z + "}";
    }
}