package com.example.employeebook.service;

import com.example.employeebook.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface EmployeeService {

    Employee addEmployee(String name, String secondName, long salary, int departmentID);

    Employee removeEmployee(String name, String secondName);

    Employee findEmployee(String name, String secondName);

    Collection<Employee> getAllEmployees();
}
