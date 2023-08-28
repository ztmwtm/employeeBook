package com.example.employeebook.service;

import com.example.employeebook.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface DepartmentService {
    List<Employee> getEmployeesByDepartmentId(int departmentId);

    Double getSalarySumByDepartmentId(int departmentId);

    Double getMaxSalaryByDepartmentId(int departmentId);

    Double getMinSalaryByDepartmentId(int departmentId);

    Map<Integer, List<Employee>> getAllEmployeesOrderedById();
}
