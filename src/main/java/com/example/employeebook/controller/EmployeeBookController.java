package com.example.employeebook.controller;

import com.example.employeebook.model.Employee;
import com.example.employeebook.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeBookController {

    private final EmployeeService employeeService;

    public EmployeeBookController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam("firstName") String name,
                              @RequestParam("lastName") String secondName,
                                @RequestParam("salary") long salary,
                                @RequestParam("departmentID") int departmentID) {
        return employeeService.addEmployee(name, secondName, salary, departmentID);
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam("firstName") String name,
                              @RequestParam("lastName") String secondName) {
        return employeeService.removeEmployee(name, secondName);
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam("firstName") String name,
                              @RequestParam("lastName") String secondName) {
        return employeeService.findEmployee(name, secondName);
    }

    @GetMapping(path = "/getAll")
    public Collection<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
