package com.example.employeebook.service;

import com.example.employeebook.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DepartmentEmployeeServiceImpl implements DepartmentEmployeeService {


    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getMaxSalaryEmployeeByDepartmentId(int departmentID) {
        return getAllEmployeesByDepartmentId(departmentID).stream()
                .max(Comparator.comparingLong(Employee::getSalary)).orElse(null);
    }

    @Override
    public Employee getMinSalaryEmployeeByDepartmentId(int departmentID) {
        return getAllEmployeesByDepartmentId(departmentID).stream()
                .min(Comparator.comparingLong(Employee::getSalary)).orElse(null);
    }

    @Override
    public Collection<Employee> getAllEmployeesByDepartmentId(int departmentID) {
        return getEmployeeByPredicate(e -> e.getDepartmentID() == departmentID).toList();
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployeesGroupByDepartmentId() {
        return employeeService.getAllEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentID));
    }

    private Stream<Employee> getEmployeeByPredicate(Predicate<Employee> predicate) {
        return employeeService.getAllEmployees().stream().filter(predicate);
    }
}
