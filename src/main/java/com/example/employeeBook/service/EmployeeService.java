package com.example.employeeBook.service;

import com.example.employeeBook.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    Employee addEmployee(String name, String secondName);

    Employee removeEmployee(String name, String secondName);

    Employee findEmployee(String name, String secondName);

    List<Employee> getAllEmployees();
}
