package com.example.employeeBook.service;

import com.example.employeeBook.exception.EmployeeAlreadyAddedException;
import com.example.employeeBook.exception.EmployeeNotFoundException;
import com.example.employeeBook.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employees;

    public EmployeeServiceImpl() {
        employees = new ArrayList<>();
    }

    @Override
    public Employee addEmployee(String name, String secondName) {
        Employee employee = new Employee(name, secondName);
        if (employees.contains(employee)) {
            String cause = String.format("Employee with name %s and second name %s is already added.", name, secondName);
            throw new EmployeeAlreadyAddedException(cause);
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String name, String secondName) {
        Employee employee = new Employee(name, secondName);
        if (employees.remove(employee)) {
            return employee;
        } else {
            String cause = String.format("Employee with name %s and second name %s isn't found.", name, secondName);
            throw new EmployeeNotFoundException(cause);
        }
    }

    @Override
    public Employee findEmployee(String name, String secondName) {
        Employee employee = new Employee(name, secondName);
        if (!employees.contains(employee)) {
            String cause = String.format("Employee with name %s and second name %s isn't found.", name, secondName);
            throw new EmployeeNotFoundException(cause);
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }
}
