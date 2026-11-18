package ru.kykhalova.company;
import java.util.ArrayList;

public class Department {
    String name;
    Employee manager;
    ArrayList<Employee> employees;

    public Department(String name) {
        this.name = name;
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employee.setDepartment(this);
    }

    public void addEmployee(Employee employee, String note) {
        employee.setDepartment(this);
        System.out.println(note);
    }

    // 1.11 и 8.1 начальник напрямую переводится в этот отдел
    public void setManager(Employee manager) {
        this.manager = manager;
        if (manager != null) {
            manager.setDepartment(this); // гарантирует, что начальник работает в этом же отделе
        }
    }

    public String getName() {
        return name;
    }

    public Employee getManager() {
        return manager;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    @Override
    public String toString() {
        String managerName = (manager != null) ? manager.name : "не назначен";
        return "Отдел: " + name + ", начальник: " + managerName;
    }
}