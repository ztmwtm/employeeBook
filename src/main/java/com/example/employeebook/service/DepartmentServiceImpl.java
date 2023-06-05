package com.example.employeebook.service;

import com.example.employeebook.exception.DepartmentNotFoundException;
import com.example.employeebook.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;


    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public List<Employee> getEmployeesByDepartmentId(int departmentId) {
        return getEmployeesByDepartment(departmentId);
    }

    @Override
    public Double getSalarySumByDepartmentId(int departmentId) {
        return getEmployeesByDepartmentId(departmentId)
                .stream()
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    @Override
    public Double getMaxSalaryByDepartmentId(int departmentId) {
        return getEmployeesByDepartmentId(departmentId)
                .stream()
                .mapToDouble(Employee::getSalary)
                .max()
                .orElseThrow(() -> new DepartmentNotFoundException(String.format("Department with %s id not found", departmentId)));
    }

    @Override
    public Double getMinSalaryByDepartmentId(int departmentId) {
        return getEmployeesByDepartmentId(departmentId)
                .stream()
                .mapToDouble(Employee::getSalary)
                .min()
                .orElseThrow(() -> new DepartmentNotFoundException(String.format("Department with %s id not found", departmentId)));
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployeesOrderedById() {
        HashMap<Integer, List<Employee>> departments = new HashMap<>();
        Collection<Employee> employees = employeeService.getAllEmployees();
        for (Employee value : employees) {
            List<Employee> currentList = departments.get(value.getDepartmentID());
            if (currentList == null) {
                currentList = new ArrayList<>();
                currentList.add(value);
                departments.put(value.getDepartmentID(), currentList);
            } else {
                departments.get(value.getDepartmentID()).add(value);
            }
        }

        return departments;
    }

    private List<Employee> getEmployeesByDepartment (int departmentId) {
        return   employeeService
                .getAllEmployees()
                .stream()
                .filter(employee -> employee.getDepartmentID() == departmentId)
                .toList();
    }
}
