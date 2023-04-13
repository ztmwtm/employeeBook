package com.example.employeebook.model;

import java.util.Objects;

public class Employee {

    private String name;
    private String secondName;

    private long salary;

    private int departmentID;

    private String stringKey;


    public Employee(String name, String secondName, long salary, int departmentID) {
        this.name = name;
        this.secondName = secondName;
        this.salary = salary;
        this.departmentID = departmentID;
        this.stringKey = name.concat(secondName);
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public long getSalary() {
        return salary;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public String getStringKey() {
        return stringKey;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return salary == employee.salary && departmentID == employee.departmentID && Objects.equals(name, employee.name) && Objects.equals(secondName, employee.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, secondName, salary, departmentID);
    }

    @Override
    public String toString() {
        return String.format("Employee: |%20s|%20s|  Salary |%10.2f|  DepartmentID |%3d|",
                 secondName, name, (double) salary / 100, departmentID);
    }
}
