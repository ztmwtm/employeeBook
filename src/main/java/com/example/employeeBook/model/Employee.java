package com.example.employeeBook.model;

import java.util.Objects;

public class Employee {

    private String name;
    private String secondName;

    public Employee(String name, String secondName) {
        this.name = name;
        this.secondName = secondName;
    }


    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && Objects.equals(secondName, employee.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, secondName);
    }

    @Override
    public String toString() {
        return String.format("{ \"firstName\": \"%s\", \"lastName\": \"%s\" }",
                name, secondName);
    }
}
