package com.example.employeebook.controller;

import com.example.employeebook.model.Employee;
import com.example.employeebook.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/{id}/employees")
    public List<Employee> getEmployeesByDepartmentId(@PathVariable("id") int departmentId) {
        return departmentService.getEmployeesByDepartmentId(departmentId);
    }

    @GetMapping(path = "/{id}/salary/sum")
    public double getSalarySumByDepartmentId(@PathVariable("id") int departmentId) {
        return departmentService.getSalarySumByDepartmentId(departmentId);
    }

    @GetMapping(path = "{id}/salary/max")
    public double getMaxSalaryByDepartmentId(@PathVariable("id") int departmentId) {
        return departmentService.getMaxSalaryByDepartmentId(departmentId);
    }

    @GetMapping(path = "{id}/salary/min")
    public double getMinSalaryByDepartmentId(@PathVariable("id") int departmentId) {
        return departmentService.getMinSalaryByDepartmentId(departmentId);
    }

    @GetMapping(path = "/employees")
    public Map<Integer, List<Employee>> getAllEmployees() {
        return departmentService.getAllEmployeesOrderedById();
    }

}
