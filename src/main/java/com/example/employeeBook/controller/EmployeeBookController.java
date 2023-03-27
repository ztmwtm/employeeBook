package com.example.employeeBook.controller;

import com.example.employeeBook.model.Employee;
import com.example.employeeBook.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeBookController {

    private final EmployeeService employeeService;

    public EmployeeBookController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam(required = true, name = "firstName") String name,
                              @RequestParam(required = true, name = "lastName") String secondName) {
        return employeeService.addEmployee(name, secondName);
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam(required = true, name = "firstName") String name,
                              @RequestParam(required = true, name = "lastName") String secondName) {
        return employeeService.removeEmployee(name, secondName);
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam(required = true, name = "firstName") String name,
                              @RequestParam(required = true, name = "lastName") String secondName) {
        return employeeService.findEmployee(name, secondName);
    }

    @GetMapping(path = "/getAll")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
