package com.example.employeebook.service;

import com.example.employeebook.exception.EmployeeAlreadyAddedException;
import com.example.employeebook.exception.EmployeeNotFoundException;
import com.example.employeebook.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee addEmployee(String name, String secondName, long salary, int departmentID) {
        Employee employee = new Employee(EmployeeUtils.validateString(name),
                                        EmployeeUtils.validateString(secondName), salary, departmentID);
        if (employees.containsKey(employee.getStringKey())) {
            String cause = String.format("Employee with name %s and second name %s is already added.", name, secondName);
            throw new EmployeeAlreadyAddedException(cause);
        }
        employees.put(employee.getStringKey(), employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String name, String secondName) {
        Employee employee = findEmployee(EmployeeUtils.validateString(name),
                EmployeeUtils.validateString(secondName));
        employees.remove(employee.getStringKey());
        return employee;
    }

    @Override
    public Employee findEmployee(String name, String secondName) {
        Employee employee = employees.get(EmployeeUtils.validateString(name)
                                            .concat(EmployeeUtils.validateString(secondName)));
        if (Objects.isNull(employee)) {
            String cause = String.format("Employee with name %s and second name %s isn't found.", name, secondName);
            throw new EmployeeNotFoundException(cause);
        }
        return employee;
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return employees.values();
    }
}
