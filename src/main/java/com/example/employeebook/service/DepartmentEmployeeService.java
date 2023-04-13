package com.example.employeebook.service;

import com.example.employeebook.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentEmployeeService {
    Employee getMaxSalaryEmployeeByDepartmentId(int departmentID);

    Employee getMinSalaryEmployeeByDepartmentId(int departmentID);

    Collection<Employee>  getAllEmployeesByDepartmentId(int departmentID);

    Map<Integer, List<Employee>> getAllEmployeesGroupByDepartmentId();
}
