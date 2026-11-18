package ru.kykhalova.company;

public class Employee {
    String name;
    Department department;

    public Employee(String name) {
        this.name = name;
    }

    public void setDepartment(Department department) {
        if (this.department == department) return;
        // если сотрудник уже был в каком-то отделе, убираем его из старого списка
        if (this.department != null) {
            this.department.employees.remove(this);
            // если он был там начальником, убираем его с должности
            if (this.department.manager == this) {
                this.department.manager = null;
            }
        }

        this.department = department;

        // напрямую добавляем в список нового отдела
        if (department != null && !department.employees.contains(this)) {
            department.employees.add(this);
        }
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        if (department == null) {
            return name + " не прикреплен к отделу";
        }
        if (department.manager == this) {
            return name + " начальник отдела " + department.name;
        }
        String managerName = (department.manager != null) ? department.manager.name : "не назначен";
        return name + " работает в отделе " + department.name + ", начальник которого " + managerName;
    }
}