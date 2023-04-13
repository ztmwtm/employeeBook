package com.example.employeebook.controller;


import com.example.employeebook.model.Employee;
import com.example.employeebook.service.DepartmentEmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentsController {

    private final DepartmentEmployeeService departmentEmployeeService;

    public DepartmentsController(DepartmentEmployeeService departmentEmployeeService) {
        this.departmentEmployeeService = departmentEmployeeService;
    }

    @GetMapping(path = "/max-salary")
    public Employee getMaxSalaryEmployeeByDepartmentId(@RequestParam("departmentId") int departmentId) {
        return departmentEmployeeService.getMaxSalaryEmployeeByDepartmentId(departmentId);
    }

    @GetMapping(path = "/min-salary")
    public Employee getMinSalaryEmployeeByDepartmentId(@RequestParam("departmentId") int departmentId) {
        return departmentEmployeeService.getMinSalaryEmployeeByDepartmentId(departmentId);
    }

    @GetMapping(path = "/all", params = "departmentId")
    public Collection<Employee> getAllEmployeesByDepartmentId(@RequestParam("departmentId") int departmentId) {
        return departmentEmployeeService.getAllEmployeesByDepartmentId(departmentId);
    }

    @GetMapping(path = "/all")
    public Map<Integer, List<Employee>> getAllEmployeesGroupByDepartmentId() {
        return departmentEmployeeService.getAllEmployeesGroupByDepartmentId();
    }
}
