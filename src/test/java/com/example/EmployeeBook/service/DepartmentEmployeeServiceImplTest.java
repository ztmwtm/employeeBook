package com.example.EmployeeBook.service;

import com.example.employeebook.model.Employee;
import com.example.employeebook.service.DepartmentEmployeeServiceImpl;
import com.example.employeebook.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class DepartmentEmployeeServiceImplTest {

    DepartmentEmployeeServiceImpl service = new DepartmentEmployeeServiceImpl();
    EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    @BeforeEach
    void setUp() {
        service.setEmployeeService(employeeService);
        employeeService.addEmployee("Ivan", "Ivanov", 10000, 1);
        employeeService.addEmployee("Petr", "Ivanov", 1000, 1);
        employeeService.addEmployee("Sergey", "Ivanov", 10, 2);
        employeeService.addEmployee("Michail", "Ivanov", 1, 2);
    }

    @Test
    void getMaxSalaryEmployeeByDepartmentId() {
        assertThat(service.getMaxSalaryEmployeeByDepartmentId(2)).isEqualTo(new Employee("Sergey", "Ivanov", 10, 2));
    }

    @Test
    void getMinSalaryEmployeeByDepartmentId() {
        assertThat(service.getMinSalaryEmployeeByDepartmentId(1)).isEqualTo(new Employee("Petr", "Ivanov", 1000, 1));
        assertThat(service.getMinSalaryEmployeeByDepartmentId(2)).isEqualTo(new Employee("Michail", "Ivanov", 1, 2));
    }

    @Test
    void getAllEmployeesByDepartmentId() {
        assertThat(service.getAllEmployeesByDepartmentId(1)).containsOnly(
                new Employee("Ivan", "Ivanov", 10000, 1),
                new Employee("Petr", "Ivanov", 1000, 1));
        assertThat(service.getAllEmployeesByDepartmentId(2)).containsOnly(
                new Employee("Sergey", "Ivanov", 10, 2),
                new Employee("Michail", "Ivanov", 1, 2));
    }

    @Test
    void getAllEmployeesGroupByDepartmentId() {
        Employee first = new Employee("Ivan", "Ivanov", 10000, 1);
        Employee second = new Employee("Petr", "Ivanov", 1000, 1);
        Employee third = new Employee("Sergey", "Ivanov", 10, 2);
        Employee fourth = new Employee("Michail", "Ivanov", 1, 2);
        assertThat(service.getAllEmployeesGroupByDepartmentId()).containsEntry(2, List.of(fourth, third)).containsEntry(1, List.of(second, first));
    }
}